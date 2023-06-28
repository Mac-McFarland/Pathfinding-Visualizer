package com.pathfinding.visualizer;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PathfindingAlgorithmHandler<T extends Cell<T>> {

    private final PathfindingInterface<T> algorithm;
    private final Rectangle[][] grid;
    private static final Color DEFAULT_COLOR = Color.WHITE;
    private static final Color PATH_COLOR = Color.BLUE;

    public PathfindingAlgorithmHandler(PathfindingInterface<T> algorithm, Rectangle[][] grid) {
        this.algorithm = algorithm;
        this.grid = grid;
    }

    public void performPathfinding(Cell<T> start, Cell<T> target, PathfindingVisualizer<T> visualizer) {
        
        visualizer.target(target);
        List<Cell<T>> path = algorithm.findPath(start, target, "algorithmName");
        if (!path.isEmpty()) {
            drawPath(path, visualizer);
        } else {
            // Display message indicating no path was found
            visualizer.showAlert("No Path Found", "No path could be found between the selected cells.");
            // Log the failure
            Logger.getLogger(PathfindingAlgorithmHandler.class.getName()).log(Level.WARNING,
                    "No path found between the selected cells");
            // Reset grid to original state
            visualizer.clearGrid();
            visualizer.start(null);
            visualizer.clearObstacles();
        }
    }

    // Draw the path on the grid
    private void drawPath(List<Cell<T>> path, PathfindingVisualizer<T> visualizer) {
        visualizer.clearPath();
        for (Cell<T> cell : path) {
            int row = cell.getRow();
            int col = cell.getCol();
            Rectangle rectangle = grid[row][col];
            rectangle.setFill(PATH_COLOR);
        }
    }
    
    public void clearGrid() {
        for (Rectangle[] row : grid) {
            for (Rectangle rectangle : row) {
                rectangle.setFill(DEFAULT_COLOR);
            }
        }
    }
}








