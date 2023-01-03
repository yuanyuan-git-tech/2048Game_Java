package edu.virginia.cs;

import java.util.Random;

public class Board implements GameMode {
    private int[][] board;
    private int sumOfScore;
    private int numOfTile;
    static final int BOARD_SIZE = 4;

    public Board() {
        board = new int[BOARD_SIZE][BOARD_SIZE];
        sumOfScore = 0;
        numOfTile = 0;
    }

    public boolean isGameOver() {
        // No free spaces remain
        if (getSize() != board.length * board.length) {
            return false;
        }
        // No shift would merge any tiles
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
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) throw new ArrayIndexOutOfBoundsException();
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

    private void moveDown() {
        boolean changed = false;
        for (int j = 0; j < board[0].length; j++) {
            // 1. merge the same tile
            int i = board.length - 1;
            while (i >= 0) {
                if (board[i][j] != 0) {
                    int next = i - 1;
                    while (next >= 0 && board[next][j] == 0) {
                        next++;
                    }
                    if (next >= 0 && board[i][j] == board[next][j]) {
                        board[i][j] += board[next][j];
                        sumOfScore += board[i][j];
                        board[next][j] = 0;
                        i = next - 1;
                        changed = true;
                    } else i--;
                } else {
                    i--;
                }
            }
            // 2. no empty space between two tiles
            int row = board.length - 1;
            while (row >= 0) {
                int nonEmptyTile = row;
                while (nonEmptyTile >= 0 && board[nonEmptyTile][j] == 0) nonEmptyTile++;
                if (nonEmptyTile >= 0 && board[nonEmptyTile][j] != 0) {
                    board[row][j] = board[nonEmptyTile][j];
                    board[nonEmptyTile][j] = 0;
                    changed = true;
                }
                row--;
            }
        }
        if (changed) generateNewCube();
    }

    private void moveUp() {
        boolean changed = false;
        for (int j = 0; j < board[0].length; j++) {
            // 1. merge the same tile
            int i = 0;
            while (i < board.length - 1) {
                if (board[i][j] != 0) {
                    int next = i + 1;
                    while (next < board.length && board[next][j] == 0) {
                        next++;
                    }
                    if (next < board.length && board[i][j] == board[next][j]) {
                        board[i][j] += board[next][j];
                        sumOfScore += board[i][j];
                        board[next][j] = 0;
                        i = next + 1;
                        changed = true;
                    } else i++;
                } else {
                    i++;
                }
            }
            // 2. no empty space between two tiles
            int row = 0;
            while (row < board.length) {
                int nonEmptyTile = row;
                while (nonEmptyTile <  board.length && board[nonEmptyTile][j] == 0) nonEmptyTile++;
                if (nonEmptyTile < board.length && board[nonEmptyTile][j] != 0) {
                    board[row][j] = board[nonEmptyTile][j];
                    board[nonEmptyTile][j] = 0;
                    changed = true;
                }
                row++;
            }
        }
        if (changed) generateNewCube();
    }

    private void moveRight() {
        boolean changed = false;
        for (int i = 0; i < board.length; i++) {
            int j = board[0].length - 1;
            while (j >= 0) {
                if (board[i][j] != 0) {
                    int next = j - 1;
                    while (next >= 0 && board[i][next] == 0) {
                        next--;
                    }
                    if (next >= 0 && board[i][j] == board[i][next]) {
                        board[i][j] += board[i][next];
                        sumOfScore += board[i][j];
                        board[i][next] = 0;
                        i = next - 1;
                        changed = true;
                    } else {
                        j--;
                    }
                } else j--;
            }
            int col = board[0].length - 1;
            while (col >= 0) {
                int nonEmptyTile = col;
                while (nonEmptyTile >= 0 && board[i][nonEmptyTile] == 0) nonEmptyTile--;
                if (nonEmptyTile >= 0 && board[i][nonEmptyTile] != 0) {
                    board[i][col] = board[i][nonEmptyTile];
                    board[i][nonEmptyTile] = 0;
                    changed = true;
                }
                col--;
            }
        }
        if (changed) generateNewCube();
    }
    private void moveLeft() {
        boolean changed = false;
        for (int i = 0; i < board.length; i++) {
            int j = 0;
            while (j < board[0].length) {
                if (board[i][j] != 0) {
                    int next = j + 1;
                    while (next < board[0].length && board[i][next] == 0) {
                        next++;
                    }
                    if (next < board[0].length && board[i][j] == board[i][next]) {
                        board[i][j] += board[i][next];
                        sumOfScore += board[i][j];
                        board[i][next] = 0;
                        i = next + 1;
                        changed = true;
                    } else {
                        j++;
                    }
                } else j++;
            }
            int col = 0;
            while (col < board[0].length) {
                int nonEmptyTile = col;
                while (nonEmptyTile < board[0].length && board[i][nonEmptyTile] == 0) nonEmptyTile++;
                if (nonEmptyTile < board[0].length && board[i][nonEmptyTile] != 0) {
                    board[i][col] = board[i][nonEmptyTile];
                    board[i][nonEmptyTile] = 0;
                    changed = true;
                }
                col++;
            }
        }
        if (changed) generateNewCube();
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
