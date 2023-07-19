//Class that finds the shortest path from a source cell to all other cells in the
//grid, doesn't consider any estimates for the distance to the target cell, and
//is more suitable for graphs without considering a specific target cell.

package com.pathfinding.visualizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraAlgorithm<T extends Cell<T>> implements PathfindingInterface<T> {

    private int[][] grid;
    private int rows;
    private int cols;

    @Override
    public List<Cell<T>> findPath(Cell<T> start, Cell<T> target, String algorithmName) {
        // Initialize distances and visited array
        int[][] distance = new int[rows][cols];
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
            Arrays.fill(visited[i], false);
        }

        // Initialize distance of start cell as 0
        distance[start.getRow()][start.getCol()] = 0;

        // Priority queue to store cells based on their distance
        PriorityQueue<Cell<T>> pq = new PriorityQueue<>(Comparator.comparingInt(cell -> cell.getDistance()));
        pq.offer(start);

        while (!pq.isEmpty()) {
            Cell<T> current = pq.poll();
            int row = current.getRow();
            int col = current.getCol();

            // Check if target cell is reached
            if (current.equals(target)) {
                return reconstructPath(target);
            }

            // Visit the current cell
            visited[row][col] = true;

            // Explore neighbors
            List<Cell<T>> neighbors = getNeighbors(row, col);
            for (Cell<T> neighbor : neighbors) {
                int neighborRow = neighbor.getRow();
                int neighborCol = neighbor.getCol();

                // Calculate the new distance to the neighbor
                int newDistance = distance[row][col] + grid[neighborRow][neighborCol];

                if (newDistance < distance[neighborRow][neighborCol]) {
                    // Update the distance and add neighbor to the priority queue
                    distance[neighborRow][neighborCol] = newDistance;
                    neighbor.setDistance(newDistance);
                    neighbor.setPrevious(current);
                    pq.offer(neighbor);
                }
            }
        }

        // No path found, return an empty list instead of null
        return new ArrayList<>();
    }

    private List<Cell<T>> reconstructPath(Cell<T> target) {
        List<Cell<T>> path = new ArrayList<>();
        Cell<T> current = target;

        while (current != null) {
            path.add(current);
            current = current.getPrevious();
        }
        Collections.reverse(path);
        return path;
    }

    private List<Cell<T>> getNeighbors(int row, int col) {
        List<Cell<T>> neighbors = new ArrayList<>();

        // Add the cells above, below, left, and right of the current cell
        if (isValidCell(row - 1, col)) {
            neighbors.add(new Cell<T>(row - 1, col).getTypedCell());
        }
        if (isValidCell(row + 1, col)) {
            neighbors.add(new Cell<T>(row + 1, col).getTypedCell());
        }
        if (isValidCell(row, col - 1)) {
            neighbors.add(new Cell<T>(row, col - 1).getTypedCell());
        }
        if (isValidCell(row, col + 1)) {
            neighbors.add(new Cell<T>(row, col + 1).getTypedCell());
        }

        return neighbors;
    }

    private boolean isValidCell(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols && grid[row][col] != -1;
    }
}




