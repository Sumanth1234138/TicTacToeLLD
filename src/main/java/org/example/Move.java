package org.example;

public class Move {
    private final int row;
    private final int col;

    public Move(int row, int col) {
        if (row < 0 || col < 0) {
            throw new IllegalArgumentException("Row and Column must be >= 0");
        }
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}