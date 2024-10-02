import tkinter as tk
import random

class tetris2:
    def __init__(self, master):
        self.master = master
        self.master.title("tetris2")
        self.canvas = tk.Canvas(self.master, width=300, height=600)
        self.canvas.pack()

        self.width = 10
        self.height = 20
        self.square_size = 30
        self.board = [[0 for _ in range(self.width)] for _ in range(self.height)]

        self.shapes = [
            [[1, 1, 1, 1]],
            [[1, 1], [1, 1]],
            [[1, 1, 1], [0, 1, 0]],
            [[1, 1, 1], [1, 0, 0]],
            [[1, 1, 1], [0, 0, 1]],
            [[1, 1, 0], [0, 1, 1]],
            [[0, 1, 1], [1, 1, 0]]
        ]
        self.colors = ['cyan', 'yellow', 'purple', 'blue', 'orange', 'green', 'red']

        self.current_shape = None
        self.current_color = None
        self.current_x = 0
        self.current_y = 0

        self.master.bind("<Left>", self.move_left)
        self.master.bind("<Right>", self.move_right)
        self.master.bind("<Down>", self.move_down)
        self.master.bind("<Up>", self.rotate)

        self.new_shape()
        self.update()

    def new_shape(self):
        self.current_shape = random.choice(self.shapes)
        self.current_color = random.choice(self.colors)
        self.current_x = self.width // 2 - len(self.current_shape[0]) // 2
        self.current_y = 0

    def draw_square(self, x, y, color):
        self.canvas.create_rectangle(
            x * self.square_size, y * self.square_size,
            (x + 1) * self.square_size, (y + 1) * self.square_size,
            fill=color, outline="white"
        )

    def draw_board(self):
        self.canvas.delete("all")
        for y, row in enumerate(self.board):
            for x, cell in enumerate(row):
                if cell:
                    self.draw_square(x, y, cell)

    def draw_shape(self):
        for y, row in enumerate(self.current_shape):
            for x, cell in enumerate(row):
                if cell:
                    self.draw_square(self.current_x + x, self.current_y + y, self.current_color)

    def check_collision(self, shape, x, y):
        for row_index, row in enumerate(shape):
            for col_index, cell in enumerate(row):
                if cell:
                    if (y + row_index >= self.height or
                        x + col_index < 0 or
                        x + col_index >= self.width or
                        self.board[y + row_index][x + col_index]):
                        return True
        return False

    def merge_shape(self):
        for y, row in enumerate(self.current_shape):
            for x, cell in enumerate(row):
                if cell:
                    self.board[self.current_y + y][self.current_x + x] = self.current_color

    def clear_lines(self):
        full_lines = [i for i, row in enumerate(self.board) if all(row)]
        for line in full_lines:
            del self.board[line]
            self.board.insert(0, [0 for _ in range(self.width)])

    def move_left(self, event):
        if not self.check_collision(self.current_shape, self.current_x - 1, self.current_y):
            self.current_x -= 1

    def move_right(self, event):
        if not self.check_collision(self.current_shape, self.current_x + 1, self.current_y):
            self.current_x += 1

    def move_down(self, event):
        if not self.check_collision(self.current_shape, self.current_x, self.current_y + 1):
            self.current_y += 1
        else:
            self.merge_shape()
            self.clear_lines()
            self.new_shape()
            if self.check_collision(self.current_shape, self.current_x, self.current_y):
                self.game_over()

    def rotate(self, event):
        rotated = list(zip(*reversed(self.current_shape)))
        if not self.check_collision(rotated, self.current_x, self.current_y):
            self.current_shape = rotated

    def game_over(self):
        self.canvas.create_text(150, 300, text="Game Over", font=("Arial", 24), fill="red")

    def update(self):
        self.move_down(None)
        self.draw_board()
        self.draw_shape()
        self.master.after(500, self.update)

if __name__ == "__main__":
    root = tk.Tk()
    game = tetris2(root)
    root.mainloop()