//User Interface that lets the user select an algorithm, reset and generate
//random maps, has a drop down menu for starting and ending points, create
//walls or erase points, and store and show how many tiles it took to get 
//to the end point.

package com.pathfinding.visualizer;

import java.util.Random;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class UserInterface<T extends Cell<T>> implements PathfindingInterface<T>{

    private static final int ROWS = 50;
    private static final int COLS = 50;
    private static final int CELL_SIZE = 10;
    private static final Color DEFAULT_COLOR = Color.WHITE;
    private static final Color OBSTACLE_COLOR = Color.BLACK;
    private static final Color START_COLOR = Color.GREEN;
    private static final Color TARGET_COLOR = Color.RED;

    private Rectangle[][] grid;
    private Cell startCell;
    private Cell targetCell;

    private Button resetButton;
    private Button randomMapButton;
    private ComboBox<String> startPointComboBox;
    private ComboBox<String> endPointComboBox;
    private Button wallButton;
    private Button eraseButton;
    private Label stepsLabel;
    
    public void createUI(Stage primaryStage) {
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

     // Create UI controls
     resetButton = new Button("Reset");
     randomMapButton = new Button("Random Map");
     startPointComboBox = new ComboBox<>();
     endPointComboBox = new ComboBox<>();
     wallButton = new Button("Add Wall");
     eraseButton = new Button("Erase");
     stepsLabel = new Label("Steps: 0");

     // Populate start and end point combo boxes
     startPointComboBox.getItems().addAll("A", "B", "C", "D", "E");
     endPointComboBox.getItems().addAll("F", "G", "H", "I", "J");

     // Set event handlers for UI controls
     resetButton.setOnAction(event -> resetMap());
     randomMapButton.setOnAction(event -> generateRandomMap());
     wallButton.setOnAction(event -> toggleWallMode());
     eraseButton.setOnAction(event -> toggleEraseMode());

     // Create UI layout
     GridPane uiLayout = new GridPane();
     uiLayout.setAlignment(Pos.CENTER);
     uiLayout.setHgap(10);
     uiLayout.setVgap(10);
     uiLayout.addRow(0, resetButton, randomMapButton);
     uiLayout.addRow(1, new Label("Start Point:"), startPointComboBox);
     uiLayout.addRow(2, new Label("End Point:"), endPointComboBox);
     uiLayout.addRow(3, wallButton, eraseButton);
     uiLayout.addRow(4, stepsLabel);

     // Create the root layout
     GridPane rootLayout = new GridPane();
     rootLayout.setAlignment(Pos.CENTER);
     rootLayout.add(root, 0, 0);
     rootLayout.add(uiLayout, 0, 1);

     // Set the scene
     Scene scene = new Scene(rootLayout, COLS * CELL_SIZE, ROWS * CELL_SIZE + 100);
     primaryStage.setTitle("Pathfinding Visualizer");
     primaryStage.setScene(scene);
     primaryStage.show();
 }

    //Event handler for algorithm selection
    algorithmComboBox.setOnAction(event -> {
        String algorithmName = algorithmComboBox.getValue();
        if (algorithmName != null) {
            algorithm = algorithmMap.get(algorithmName);
        }
    });

    //

  //Handle the logic for click events, to toggle cell colors, and add/remove obstacles for now.
  private void handleCellClick(int row, int col) {
    Rectangle cell = grid[row][col];

    if (isWallModeEnabled()) {
        // Toggle cell between obstacle and default color
        if (cell.getFill().equals(OBSTACLE_COLOR)) {
            cell.setFill(DEFAULT_COLOR);
        } else {
            cell.setFill(OBSTACLE_COLOR);
        }
    } else if (isEraseModeEnabled()) {
        // Reset the cell to default color
        cell.setFill(DEFAULT_COLOR);
    } else {
        // Set start or end point cell
        if (startCell == null) {
            // Set as start point
            cell.setFill(START_COLOR);
            startCell = new Cell(row, col);
        } else if (targetCell == null) {
            // Set as end point
            cell.setFill(TARGET_COLOR);
            targetCell = new Cell(row, col);
        }
    }
}

//Method to prevent the start and end points from being cleared or overwritten
private boolean startOrEndCell(Rectangle cell){
    Color cellColor = (Color) cell.getFill();
    return cell.getFill().equals(START_COLOR) || cell.getFill().equals(TARGET_COLOR);
}

private void resetMap() {
    for (Rectangle[] row : grid) {
        for (Rectangle rectangle : row) {
            rectangle.setFill(DEFAULT_COLOR);
        }
    }
    startCell = null;
    targetCell = null;
}

private void generateRandomMap() {
    Random random = new Random();
    resetMap();

    for (Rectangle[] row : grid) {
        for (Rectangle rectangle : row) {
            if (random.nextDouble() < 0.3) {
                rectangle.setFill(OBSTACLE_COLOR);
            }
        }
    }
}

private boolean isWallModeEnabled() {
    // Implement logic to determine if wall mode is enabled
    
}

private boolean isEraseModeEnabled() {
    // Implement logic to determine if erase mode is enabled
}


}
