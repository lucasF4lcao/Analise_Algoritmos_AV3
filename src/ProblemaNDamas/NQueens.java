package ProblemaNDamas;

public class NQueens {
    static final int N = 8;

    static void printBoard(int board[][]) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print((board[i][j] == 1 ? "Q " : ". "));
            }
            System.out.println();
        }
        System.out.println();
    }

    static boolean isSafe(int board[][], int row, int col) {
        for (int i = 0; i < col; i++)
            if (board[row][i] == 1)
                return false;

        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        for (int i = row, j = col; j >= 0 && i < N; i++, j--)
            if (board[i][j] == 1)
                return false;

        return true;
    }

    static boolean solveNQueens(int board[][], int col) {
        if (col >= N) {
            printBoard(board);
            return true;
        }

        boolean foundSolution = false;
        for (int i = 0; i < N; i++) {
            if (isSafe(board, i, col)) {
                board[i][col] = 1;
                foundSolution = solveNQueens(board, col + 1) || foundSolution;
                board[i][col] = 0;
            }
        }
        return foundSolution;
    }

    public static void main(String[] args) {
        int board[][] = new int[N][N];
        if (!solveNQueens(board, 0)) {
            System.out.println("Solução não existe.");
        }
    }
}
