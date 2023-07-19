//Grid class to draw the grid and store the cells in the grid
//Sets cell colors, logic for cell events, and provides access to each cell

package com.pathfinding.visualizer;

public class Grid<T extends Cell<T>> {
    private Cell<T>[][] cells;
    private int rows;
    private int cols;
    private static final int CELL_SIZE = 10;

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        cells = (Cell<T>[][]) new Cell[rows][cols];
        createGrid();
    }

    private void createGrid() {
        // Create grid of cells
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                cells[row][col] = new Cell<>(row, col);
            }
        }
    }
}
