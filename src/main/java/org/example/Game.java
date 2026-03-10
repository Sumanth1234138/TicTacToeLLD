package org.example;

import java.util.Scanner;

public class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private GameStatus status;

    public Game() {
        board = new Board(3);
        player1 = new Player("Player 1", 'X');
        player2 = new Player("Player 2", 'O');
        currentPlayer = player1;
        status = GameStatus.IN_PROGRESS;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (status == GameStatus.IN_PROGRESS) {
            board.printBoard();
            System.out.println(currentPlayer.getName() +
                    " turn (" + currentPlayer.getSymbol() + ")");
            System.out.print("Enter row and column (0-2): ");

            int row = scanner.nextInt();
            int col = scanner.nextInt();

            Move move = new Move(row, col);

            if (!board.makeMove(move, currentPlayer.getSymbol())) {
                System.out.println("Invalid move, try again!");
                continue;
            }

            if (board.checkWinner(currentPlayer.getSymbol())) {
                status = GameStatus.WIN;
                board.printBoard();
                System.out.println(currentPlayer.getName() + " wins!");
            } else if (board.isFull()) {
                status = GameStatus.DRAW;
                board.printBoard();
                System.out.println("Game is Draw!");
            } else {
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
