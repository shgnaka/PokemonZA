import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class tetris1 extends JFrame {
    private static final int BOARD_WIDTH = 10;
    private static final int BOARD_HEIGHT = 20;
    private static final int BLOCK_SIZE = 30;

    private boolean[][] board = new boolean[BOARD_HEIGHT][BOARD_WIDTH];
    private Shape currentShape;
    private Timer timer;

    public tetris1() {
        setTitle("Tetris");
        setSize(BOARD_WIDTH * BLOCK_SIZE, BOARD_HEIGHT * BLOCK_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        currentShape = new Shape();
        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveDown();
            }
        });
        timer.start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        moveLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        moveRight();
                        break;
                    case KeyEvent.VK_DOWN:
                        moveDown();
                        break;
                    case KeyEvent.VK_UP:
                        rotate();
                        break;
                }
            }
        });

        setFocusable(true);
    }

    private void moveLeft() {
        if (canMove(currentShape.x - 1, currentShape.y, currentShape.shape)) {
            currentShape.x--;
            repaint();
        }
    }

    private void moveRight() {
        if (canMove(currentShape.x + 1, currentShape.y, currentShape.shape)) {
            currentShape.x++;
            repaint();
        }
    }

    private void moveDown() {
        if (canMove(currentShape.x, currentShape.y + 1, currentShape.shape)) {
            currentShape.y++;
        } else {
            placeShape();
            clearLines();
            currentShape = new Shape();
            if (!canMove(currentShape.x, currentShape.y, currentShape.shape)) {
                gameOver();
            }
        }
        repaint();
    }

    private void rotate() {
        int[][] rotated = rotateShape(currentShape.shape);
        if (canMove(currentShape.x, currentShape.y, rotated)) {
            currentShape.shape = rotated;
            repaint();
        }
    }

    private boolean canMove(int x, int y, int[][] shape) {
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] == 1) {
                    int newX = x + j;
                    int newY = y + i;
                    if (newX < 0 || newX >= BOARD_WIDTH || newY >= BOARD_HEIGHT || (newY >= 0 && board[newY][newX])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void placeShape() {
        for (int i = 0; i < currentShape.shape.length; i++) {
            for (int j = 0; j < currentShape.shape[i].length; j++) {
                if (currentShape.shape[i][j] == 1) {
                    board[currentShape.y + i][currentShape.x + j] = true;
                }
            }
        }
    }

    private void clearLines() {
        for (int i = BOARD_HEIGHT - 1; i >= 0; i--) {
            boolean lineFull = true;
            for (int j = 0; j < BOARD_WIDTH; j++) {
                if (!board[i][j]) {
                    lineFull = false;
                    break;
                }
            }
            if (lineFull) {
                for (int k = i; k > 0; k--) {
                    System.arraycopy(board[k - 1], 0, board[k], 0, BOARD_WIDTH);
                }
                board[0] = new boolean[BOARD_WIDTH];
            }
        }
    }

    private void gameOver() {
        timer.stop();
        JOptionPane.showMessageDialog(this, "Game Over!", "Tetris", JOptionPane.INFORMATION_MESSAGE);
    }

    private int[][] rotateShape(int[][] shape) {
        int[][] rotated = new int[shape[0].length][shape.length];
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                rotated[j][shape.length - 1 - i] = shape[i][j];
            }
        }
        return rotated;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.GREEN);
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                if (board[i][j]) {
                    g.fillRect(j * BLOCK_SIZE, i * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        }

        g.setColor(Color.RED);
        for (int i = 0; i < currentShape.shape.length; i++) {
            for (int j = 0; j < currentShape.shape[i].length; j++) {
                if (currentShape.shape[i][j] == 1) {
                    g.fillRect((currentShape.x + j) * BLOCK_SIZE, (currentShape.y + i) * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        }
    }

    private class Shape {
        int[][] shape;
        int x, y;

        public Shape() {
            shape = getRandomShape();
            x = BOARD_WIDTH / 2 - shape[0].length / 2;
            y = 0;
        }

        private int[][] getRandomShape() {
            int[][][] shapes = {
                {{1, 1, 1, 1}},
                {{1, 1}, {1, 1}},
                {{1, 1, 1}, {0, 1, 0}},
                {{1, 1, 0}, {0, 1, 1}},
                {{0, 1, 1}, {1, 1, 0}},
                {{1, 1, 1}, {1, 0, 0}},
                {{1, 1, 1}, {0, 0, 1}}
            };
            return shapes[new Random().nextInt(shapes.length)];
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new tetris1().setVisible(true));
    }
}
