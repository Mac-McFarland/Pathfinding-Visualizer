package com.pathfinding.visualizer;
import java.util.Objects;

public class Cell<T extends Cell<T>> {
    private int row;
    private int col;
    private int distance;
    private int gScore;
    private int hScore;
    private int fScore;
    private Cell<T> previous;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getGScore() {
        return gScore;
    }

    public void setGScore(int gScore) {
        this.gScore = gScore;
    }

    public int getHScore() {
        return hScore;
    }

    public void setHScore(int hScore) {
        this.hScore = hScore;
    }

    public int getFScore() {
        return fScore;
    }

    public void setFScore(int fScore) {
        this.fScore = fScore;
    }

    public Cell<T> getPrevious() {
        return previous;
    }

    public void setPrevious(Cell<T> previous) {
        this.previous = previous;
    }

        public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Cell<?> other = (Cell<?>) obj;
        return row == other.row && col == other.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @SuppressWarnings("unchecked")
    public T getTypedCell() {
        return (T) this;
    }

}



