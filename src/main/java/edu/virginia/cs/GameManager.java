package edu.virginia.cs;

import java.util.Scanner;

public class GameManager {
    Board gameBoard;
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        GameManager start = new GameManager();
        start.play();
    }

    public void play() {
        initializeBoard();
        MoveDirection moveDirection = null;
        boolean valid = true;
        while (!gameBoard.isGameOver()) {
            while (valid) {
                System.out.println("Enter: w/a/s/d for up/left/down/right");
                String direction = scanner.nextLine();
                switch (direction.toLowerCase()) {
                    case "w" -> moveDirection = MoveDirection.UP;
                    case "a" -> moveDirection = MoveDirection.LEFT;
                    case "s" -> moveDirection = MoveDirection.DOWN;
                    case "d" -> moveDirection = MoveDirection.RIGHT;
                    default -> {
                        System.out.println("Enter a valid key!");
                        valid = false;
                    }
                }
            }
            gameBoard.move(moveDirection);
            System.out.println("Score: " + gameBoard.getScore());
        }
        System.out.println("Game is Over! Your score is: " + gameBoard.getScore());
        System.out.println("The max number of tile is: " + gameBoard.getMaxCubeNumber());
        scanner.close();
    }
    private void initializeBoard() {
        gameBoard.generateNewCube();
        gameBoard.generateNewCube();
    }

}
