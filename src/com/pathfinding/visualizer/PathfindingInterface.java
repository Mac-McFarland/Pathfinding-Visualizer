package com.pathfinding.visualizer;

import java.util.List;
//Generic interface implementation for each algorithm type
public interface PathfindingInterface<T extends Cell<T>> {
    List<Cell<T>> findPath(Cell<T> start, Cell<T> target, String algorithmName);
}






