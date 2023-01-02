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
        while (!gameBoard.isGameOver()) {
            // Todo
            System.out.println("Enter: w/a/s/d for up/left/down/right");
            String direction = scanner.nextLine();
            MoveDirection moveDirection = null;
            switch (direction) {
                case "w" -> moveDirection = MoveDirection.UP;
                case "a" -> moveDirection = MoveDirection.LEFT;
                case "s" -> moveDirection = MoveDirection.DOWN;
                case "d" -> moveDirection = MoveDirection.RIGHT;
                default -> {
                    System.out.println("Enter a valid key!");
                    scanner.nextLine();
                    continue;
                }
            }
            gameBoard.move(moveDirection);
            System.out.println("Score: " + gameBoard.getScore());
        }
        System.out.println("Game is Over! You get the max number: " + gameBoard.getMaxCubeNumber());
    }
    private void initializeBoard() {
        gameBoard.generateNewCube();
        gameBoard.generateNewCube();
    }

}
