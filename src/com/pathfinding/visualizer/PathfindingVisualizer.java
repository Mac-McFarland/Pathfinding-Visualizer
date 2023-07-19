package com.pathfinding.visualizer;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PathfindingVisualizer<T extends Cell<T>> extends Application {

    private static final Logger LOGGER = Logger.getLogger(PathfindingVisualizer.class.getName());
    





    public void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static void main(String[] args) {
        // Enable logging
        LOGGER.setLevel(Level.ALL);

        launch(args);
    }
}




