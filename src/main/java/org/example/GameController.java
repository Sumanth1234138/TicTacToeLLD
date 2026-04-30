package org.example;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
@CrossOrigin // allow React frontend
public class GameController {

    private Board board = new Board(3);
    private AIPlayer aiPlayer = new AIPlayer();

    private final char HUMAN = 'X';
    private final char AI = 'O';

    // 🎯 Get current board
    @GetMapping
    public String[][] getBoard() {
        return convertToString(board.getGrid());
    }

    // 🎮 Make move (Human + AI)
    @PostMapping("/move")
    public String[][] makeMove(@RequestParam int row, @RequestParam int col) {

        Move move = new Move(row, col);

        // Human move
        if (!board.makeMove(move, HUMAN)) {
            return convertToString(board.getGrid()); // invalid move, return same board
        }

        // AI move
        if (!board.checkWinner(HUMAN) && !board.isFull()) {
            Move aiMove = aiPlayer.findBestMove(board, AI, HUMAN);
            board.makeMove(aiMove, AI);
        }

        return convertToString(board.getGrid());
    }

    // 🔄 Reset game
    @PostMapping("/reset")
    public String[][] reset() {
        board = new Board(3);
        return convertToString(board.getGrid());
    }

    // 🔧 Helper: convert char[][] → String[][]
    private String[][] convertToString(char[][] grid) {
        String[][] result = new String[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                result[i][j] = String.valueOf(grid[i][j]);
            }
        }
        return result;
    }
}