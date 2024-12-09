public class cognitiveScience09ExhaustiveSearch {

    public static void main(String[] args) {
        int[] node = new int[10];
        long subtractn1fromn2 = (node[0] - node[1]) * (node[0] - node[1]);
        long sutractn1fromn3 = (node[0] - node[2]) * (node[0] - node[2]);
        long subtractn1fromn6 = (node[0] - node[5]) * (node[0] - node[5]);
        long subtractn1fromn7 = (node[0] - node[6]) * (node[0] - node[6]);
        long subtractn1fromn8 = (node[0] - node[7]) * (node[0] - node[7]);

        long subtractn2fromn3 = (node[1] - node[2]) * (node[1] - node[2]);
        long subtractn2fromn4 = (node[1] - node[3]) * (node[1] - node[3]);
        long subtractn1fromn9 = (node[0] - node[8]) * (node[0] - node[8]);

        long subtractn3fromn4 = (node[2] - node[3]) * (node[2] - node[3]);
        long subtractn3fromn5 = (node[2] - node[4]) * (node[2] - node[4]);

        long subtractn4fromn5 = (node[3] - node[4]) * (node[3] - node[4]);
        long subtractn4fromn10 = (node[3] - node[9]) * (node[3] - node[9]);

        long subtractn5fromn6 = (node[4] - node[5]) * (node[4] - node[5]);

        long subtractn6fromn7 = (node[5] - node[6]) * (node[5] - node[6]);

        long subtractn7fromn8 = (node[6] - node[7]) * (node[6] - node[7]);

        long subtractn8fromn1 = (node[7] - node[0]) * (node[7] - node[0]);
        long subtractn8fromn9 = (node[7] - node[8]) * (node[7] - node[8]);

        long subtractn9fromn10 = (node[8] - node[9]) * (node[8] - node[9]);

        long sum = subtractn1fromn2 + 
        sutractn1fromn3 + 
        subtractn1fromn6 + 
        subtractn1fromn7 + 
        subtractn1fromn8 + 
        subtractn2fromn3 + 
        subtractn2fromn4 + 
        subtractn1fromn9 + 
        subtractn3fromn4 + 
        subtractn3fromn5 + 
        subtractn4fromn5 + 
        subtractn4fromn10 + 
        subtractn5fromn6 + 
        subtractn6fromn7 + 
        subtractn7fromn8 + 
        subtractn8fromn1 + 
        subtractn8fromn9 + 
        subtractn9fromn10;
    }

    pulic long 
}