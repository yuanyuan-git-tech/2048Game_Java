package edu.virginia.cs;

import java.util.Random;

public class Board implements GameMode{
    int[][] board;
    int sumOfScore;
    int numOfTile;

    public Board() {
        board = new int[4][4];
        sumOfScore = 0;
        numOfTile = 0;
    }

    public boolean isGameOver() {
        if (getSize() != 16) {
            return true;
        }
        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board.length; j++) {
                if (board[i][j] == board[i - 1][j] || board[i][j] == board[i][j - 1]) return false;
            }
        }
        return true;
    }

    @Override
    public int getSize() {
        return numOfTile;
    }

    @Override
    public int getData(int x, int y) throws ArrayIndexOutOfBoundsException {
        if (x < 0 || x >= 4 || y < 0 || y >= 4) throw new ArrayIndexOutOfBoundsException();
        return board[x][y];
    }

    @Override
    public boolean move(MoveDirection direction) throws IllegalStateException {
        switch (direction) {
            case LEFT -> moveLeft();
            case UP -> moveUp();
            case DOWN -> moveDown();
            case RIGHT -> moveRight();
            default -> throw new IllegalStateException();
        }
        return true;
    }

    private void moveRight() {
        boolean canMove = false;

        if (canMove) generateNewCube();
    }

    private void moveDown() {
        boolean canMove = false;

        if (canMove) generateNewCube();
    }

    private void moveUp() {
        boolean canMove = false;

        if (canMove) generateNewCube();
    }

    private void moveLeft() {
        boolean canMove = false;
//        for (int row = 0; row < 4; row++) {
//            int col = board.length - 1;
//            while (col >= 0 && board[row][col] == 0) {
//                col--;
//            }
//            if (col < 0 || col - 1 < 0) continue;
//            if (board[row][col - 1] == board[row][col]) {
//                sumOfScore += 2 * board[row][col];
//                board[row][col - 1] = 2 * board[row][col];
//                board[row][col] = 0;
//                numOfTile--;
//                canMove = true;
//            } else if (board[row][col - 1] == 0) {
//                board[row][board.length - 1] = board[row][col];
//                board[row][col] = 0;
//                canMove = true;
//            }
//        }
        if (canMove) generateNewCube();
    }

    @Override
    public int getScore() {
        return sumOfScore;
    }

    @Override
    public int getMaxCubeNumber() {
        int max = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++){
                max = Math.max(max, board[i][j]);
            }
        }
        return max;
    }

    @Override
    public void generateNewCube() throws IllegalStateException {
        Random random = new Random();
        boolean valid = true;
        do {
            int x = random.nextInt(4), y = random.nextInt(4);
            if (board[x][y] != 0) continue;
            int p = random.nextInt();
            if (p < 0.5) {
                board[x][y] = 2;
            }
            else {
                board[x][y] = 4;
            }
            valid = false;
            numOfTile++;
        } while (valid);
    }
}
