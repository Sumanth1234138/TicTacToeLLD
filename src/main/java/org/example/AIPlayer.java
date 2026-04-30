package org.example;

public class AIPlayer {

    public Move findBestMove(Board board, char aiSymbol, char humanSymbol) {
        int bestVal = Integer.MIN_VALUE;
        Move bestMove = null;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (board.isEmpty(i, j)) {
                    board.setCell(i, j, aiSymbol);

                    int moveVal = minimax(board, 0, false, aiSymbol, humanSymbol);

                    board.setCell(i, j, '-');

                    if (moveVal > bestVal) {
                        bestVal = moveVal;
                        bestMove = new Move(i, j);
                    }
                }
            }
        }
        return bestMove;
    }

    private int minimax(Board board, int depth, boolean isMax,
                        char ai, char human) {

        if (board.checkWinner(ai)) return 10 - depth;
        if (board.checkWinner(human)) return depth - 10;
        if (board.isFull()) return 0;

        int best;

        if (isMax) {
            best = Integer.MIN_VALUE;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    if (board.isEmpty(i, j)) {
                        board.setCell(i, j, ai);

                        best = Math.max(best,
                                minimax(board, depth + 1, false, ai, human));

                        board.setCell(i, j, '-');
                    }
                }
            }

        } else {
            best = Integer.MAX_VALUE;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    if (board.isEmpty(i, j)) {
                        board.setCell(i, j, human);

                        best = Math.min(best,
                                minimax(board, depth + 1, true, ai, human));

                        board.setCell(i, j, '-');
                    }
                }
            }
        }

        return best;
    }
}