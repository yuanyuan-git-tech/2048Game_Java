package edu.virginia.cs;

public interface GameMode {
    boolean isGameOver();
    int getSize();
    int getData(int x, int y) throws ArrayIndexOutOfBoundsException;
    boolean move(MoveDirection direction) throws IllegalStateException;
    int getScore();
    int getMaxCubeNumber();
    void generateNewCube() throws IllegalStateException;

}
