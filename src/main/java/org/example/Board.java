package org.example;

public class Board {
    private final int size;
    private final char[][] grid;

    public Board(int size) {
        this.size = size;
        grid = new char[size][size];
        initialize();
    }

    private void initialize() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = '-';
            }
        }
    }

    public void printBoard() {
        for (char[] row : grid) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public boolean makeMove(Move move, char symbol) {
        int row = move.getRow();
        int col = move.getCol();

        if (row < 0 || row >= size || col < 0 || col >= size) {
            return false;
        }

        if (grid[row][col] != '-') {
            return false;
        }

        grid[row][col] = symbol;
        return true;
    }

    public boolean checkWinner(char symbol) {
        // Rows
        for (int i = 0; i < size; i++) {
            boolean win = true;
            for (int j = 0; j < size; j++) {
                if (grid[i][j] != symbol) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }

        // Columns
        for (int j = 0; j < size; j++) {
            boolean win = true;
            for (int i = 0; i < size; i++) {
                if (grid[i][j] != symbol) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }

        // Diagonals
        boolean win = true;
        for (int i = 0; i < size; i++) {
            if (grid[i][i] != symbol) {
                win = false;
                break;
            }
        }
        if (win) return true;

        win = true;
        for (int i = 0; i < size; i++) {
            if (grid[i][size - i - 1] != symbol) {
                win = false;
                break;
            }
        }
        return win;
    }

    public boolean isFull() {
        for (char[] row : grid) {
            for (char cell : row) {
                if (cell == '-') return false;
            }
        }
        return true;
    }

    public boolean isEmpty(int row, int col) {
        return grid[row][col] == '-';
    }

    public void setCell(int row, int col, char symbol) {
        grid[row][col] = symbol;
    }

    // ✅ FIXED: now inside class
    public char[][] getGrid() {
        return grid;
    }
}