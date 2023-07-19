//Class to store the indices for rows and columns in the grid, distance to
//calculate the shortest path, gScore(cost of moving from the starting cell along the path)
//hScore(estimated cost of moving from this cell to the target cell in a straight line(heuristic)),
//and fScore(sum of the gScore and hScore to determine best path.)
//Hashcode is to generate a hashcode for the row and column indices, and 
//getTypedCell is to convert cell for the generic type T.
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
    //Getter for row index of cell
    public int getRow() {
        return row;
    }
    //Getter for col index of cell
    public int getCol() {
        return col;
    }
    //Getter for G score of cell
    public int getGScore() {
        return gScore;
    }
    //Setter for G score of cell
    public void setGScore(int gScore) {
        this.gScore = gScore;
    }
    //Getter for h score of cell
    public int getHScore() {
        return hScore;
    }
    //Setter for h score of cell
    public void setHScore(int hScore) {
        this.hScore = hScore;
    }
    //Getter for f score of cell
    public int getFScore() {
        return fScore;
    }
    //Setter for f score of cell
    public void setFScore(int fScore) {
        this.fScore = fScore;
    }
    //Getter for previous cell in the path
    public Cell<T> getPrevious() {
        return previous;
    }
    //Setter for previous cell in the path
    public void setPrevious(Cell<T> previous) {
        this.previous = previous;
    }
    //Getter for distance of cell
        public int getDistance() {
        return distance;
    }
    //Setter for distance of cell
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



