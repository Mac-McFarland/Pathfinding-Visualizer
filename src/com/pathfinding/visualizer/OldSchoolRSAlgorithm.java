//Old School Runescape (OSRS) pathfinding algorithm for tile-based grids
//obstacle traversal, and movement restrictions(will be implemented later)

package com.pathfinding.visualizer;

import java.util.ArrayList;
import java.util.List;

public class OldSchoolRSAlgorithm<T extends Cell<T>> implements PathfindingInterface<T> {
    private Grid<T> grid;

    public OldSchoolRSAlgorithm(Grid<T> grid) {
        this.grid = grid;
    }

    @Override
    public List<Cell<T>> findPath(Cell<T> start, Cell<T> target, String algorithmName){
        //Implement OldSchoolRSAlgorithm logic here
        List<Cell<T>> path = new ArrayList<>();
        
        //Find the path from start to target
        int startRow = start.getRow();
        int startCol = start.getColumn();
        int targetRow = target.getRow();
        int targetCol = target.getColumn();

        //Add cells in a straight line from start to target
        if(startRow == targetRow){
            int minCol = Math.min(startCol, targetCol);
            int maxCol = Math.max(startCol, targetCol);
            for(int col = minCol; col <= maxCol; col++){
                path.add(grid.getCell(startRow, col));
            }
        }else if (startCol == targetCol){
            int minRow = Math.min(startRow, targetRow);
            int maxRow = Math.max(startRow, targetRow);
            for(int row = minRow; row <= maxRow; row++){
                path.add(grid.getCell(row, startCol));
            }
    }else{
        //Pathfinding for diagonal movement

    }return path;
}
}
