package com.pathfinding.visualizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PathfindingVisualizer<T extends Cell<T>> extends Application {

    private static final Logger LOGGER = Logger.getLogger(PathfindingVisualizer.class.getName());
    private static final int ROWS = 50;
    private static final int COLS = 50;
    private static final int CELL_SIZE = 10;
    private static final Color DEFAULT_COLOR = Color.WHITE;
    private static final Color OBSTACLE_COLOR = Color.BLACK;
    private static final Color START_COLOR = Color.GREEN;
    private static final Color TARGET_COLOR = Color.RED;
    private static final Color PATH_COLOR = Color.BLUE;
    private Rectangle[][] grid;
    private Cell<T> startCell;
    private Cell<T> targetCell;
    private List<Cell<T>> obstacles = new ArrayList<>();
    private static final String DIJKSTRA_ALGORITHM = "Dijkstra";
    private static final String A_STAR_ALGORITHM = "A*";

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        grid = new Rectangle[ROWS][COLS];

        // Create grid of rectangles and add to the root pane
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE, DEFAULT_COLOR);
                cell.setStroke(Color.GRAY);
                grid[row][col] = cell;
                root.add(cell, col, row);

                final int r = row;
                final int c = col;

                // Handle mouse click event on each cell
                cell.setOnMouseClicked(event -> handleCellClick(r, c));
            }
        }

        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, COLS * CELL_SIZE, ROWS * CELL_SIZE);
        primaryStage.setTitle("Pathfinding Visualizer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Handle cell click event
    private void handleCellClick(int row, int col) {
        Rectangle cell = grid[row][col];
        Color currentColor = (Color) cell.getFill();

        if (currentColor.equals(OBSTACLE_COLOR)) {
            // Remove obstacle if clicked on an obstacle cell
            cell.setFill(DEFAULT_COLOR);
            obstacles.remove(new Cell<>(row, col));
        } else if (!isStartCellSelected() && !isTargetCellSelected()) {
            // Set start cell if no start or target cell is selected
            cell.setFill(START_COLOR);
            startCell = new Cell<>(row, col);
        } else if (!isTargetCellSelected()) {
            // Set target cell if start cell is already selected
            cell.setFill(TARGET_COLOR);
            targetCell = new Cell<>(row, col);
        } else {
            // Add obstacles if start and target cells are already selected
            cell.setFill(OBSTACLE_COLOR);
            obstacles.add(new Cell<>(row, col));
        }

        if (isStartCellSelected() && isTargetCellSelected()) {
            // Define the algorithm choices
            List<String> algorithmChoices = Arrays.asList(DIJKSTRA_ALGORITHM, A_STAR_ALGORITHM);

            // Create the choice dialog
            ChoiceDialog<String> dialog = new ChoiceDialog<>(DIJKSTRA_ALGORITHM, algorithmChoices);
            dialog.setTitle("Algorithm Selection");
            dialog.setHeaderText("Choose the algorithm");
            dialog.setContentText("Algorithm:");

            // Show the choice dialog and get the selected algorithm name
            dialog.showAndWait().ifPresent(algorithmName -> {
            // Print the selected algorithm for debugging
            LOGGER.log(Level.INFO, () -> "Selected algorithm: " + algorithmName);

                // Perform pathfinding using the selected algorithm
                performPathfindingAlgorithm(startCell, targetCell, algorithmName);
            });
        }
    }

private void performPathfindingAlgorithm(Cell<T> start, Cell<T> target, String algorithmName) {
    PathfindingAlgorithmHandler<T> algorithmHandler;
    if (algorithmName.equals(DIJKSTRA_ALGORITHM)) {
        algorithmHandler = new PathfindingAlgorithmHandler<>(new DijkstraAlgorithm<>(), grid);
    } else {
        algorithmHandler = new PathfindingAlgorithmHandler<>(new AStarAlgorithm<>(), grid);
    }

    algorithmHandler.performPathfinding(start, target, this);

    // Print the algorithm name for debugging
    LOGGER.log(Level.INFO, () -> "Performing pathfinding algorithm: " + algorithmName);

}


public int[][] createGrid() {
    int[][] gridValues = new int[ROWS][COLS];

    // Set the values of the grid cells accordingly
    for (int row = 0; row < ROWS; row++) {
        for (int col = 0; col < COLS; col++) {
            // Here, you can assign values to each grid cell based on your requirements.
            // For example, you can set obstacle cells to a specific value or leave them as 0.
            // You can also assign different values to represent different types of cells.

            // Example:
            // if (cellIsObstacle(row, col)) {
            //     gridValues[row][col] = OBSTACLE_VALUE;
            // } else {
            //     gridValues[row][col] = DEFAULT_VALUE;
            // }
        }
    }

    return gridValues;
}


    public void clearGrid() {
        for (Rectangle[] row : grid) {
            for (Rectangle rectangle : row) {
                rectangle.setFill(DEFAULT_COLOR);
            }
        }
    }

    // Clear the path on the grid
    public void clearPath() {
        for (Rectangle[] row : grid) {
            for (Rectangle rectangle : row) {
                Color cellColor = (Color) rectangle.getFill();
                if (cellColor.equals(PATH_COLOR)) {
                    rectangle.setFill(DEFAULT_COLOR);
                }
            }
        }
    }

     public void target(Cell<T> target) {
        // Perform any actions related to setting the target cell
    // For example, you can change the color of the target cell to indicate it visually
    int targetRow = targetCell.getRow();
    int targetCol = targetCell.getCol();
    Rectangle targetRectangle = grid[targetRow][targetCol];
    targetRectangle.setFill(TARGET_COLOR);
    
    // You can also store the target cell for future reference or perform any other logic
    this.targetCell = target;
    }

public void clearObstacles() {
    LOGGER.log(Level.INFO, "Cleared {0} obstacles.", obstacles.size());
    obstacles.clear();
    
    for (Cell<T> obstacle : obstacles) {
        int row = obstacle.getRow();
        int col = obstacle.getCol();
        Rectangle rectangle = grid[row][col];
        rectangle.setFill(DEFAULT_COLOR);
    }
}






    public void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Check if a start cell is selected
    private boolean isStartCellSelected() {
        return startCell != null;
    }

    // Check if a target cell is selected
    private boolean isTargetCellSelected() {
        return targetCell != null;
    }

    public static void main(String[] args) {
        // Enable logging
        LOGGER.setLevel(Level.ALL);

        launch(args);
    }
}




