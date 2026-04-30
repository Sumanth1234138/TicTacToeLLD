package org.example;

import java.util.Scanner;

public class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private GameStatus status;

    // ✅ AI integration
    private final AIPlayer aiPlayer = new AIPlayer();
    private final boolean vsAI = true;

    public Game() {
        board = new Board(3);
        player1 = new Player("Player 1", 'X');
        player2 = new Player("AI", 'O'); // 👈 AI is player2
        currentPlayer = player1;
        status = GameStatus.IN_PROGRESS;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (status == GameStatus.IN_PROGRESS) {
            board.printBoard();

            System.out.println(currentPlayer.getName() +
                    " turn (" + currentPlayer.getSymbol() + ")");

            Move move;

            // 🤖 AI TURN
            if (vsAI && currentPlayer == player2) {
                move = aiPlayer.findBestMove(board, 'O', 'X');

                System.out.println("AI played: " +
                        move.getRow() + ", " + move.getCol());

            } else {
                // 👤 HUMAN TURN
                System.out.print("Enter row and column (0-2): ");
                int row = scanner.nextInt();
                int col = scanner.nextInt();

                move = new Move(row, col);
            }

            // 🎯 APPLY MOVE
            if (!board.makeMove(move, currentPlayer.getSymbol())) {
                System.out.println("Invalid move, try again!");
                continue;
            }

            // 🏆 CHECK WIN
            if (board.checkWinner(currentPlayer.getSymbol())) {
                status = GameStatus.WIN;
                board.printBoard();
                System.out.println(currentPlayer.getName() + " wins!");
            }
            // 🤝 CHECK DRAW
            else if (board.isFull()) {
                status = GameStatus.DRAW;
                board.printBoard();
                System.out.println("Game is Draw!");
            }
            // 🔄 SWITCH PLAYER
            else {
                switchPlayer();
            }
        }

        scanner.close();
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public static void main(String[] args) {
        new Game().start();
    }
}