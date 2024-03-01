import java.util.ArrayList;
import java.util.Collections;

public class Board {
    final private Cell[][] grid;
    final private char[][] axes;
    public Board(Cell[][] newGrid, char[][] newAxes,Settings settings) {  //Constructor Class for Board
        grid = newGrid;
        axes = newAxes;
        int gridSize = newGrid.length;
        for (int i = 0; i < gridSize; i++) {
            axes[0][i] = Main.numToChar(i + 1);
            axes[1][gridSize - i - 1] = (char) ((gridSize - i) + '0');
            for (int j = 0; j < gridSize; j++) {
                Cell cell = new Cell(settings.getDifficulty());
                grid[i][j] = cell;
                cell.setCoord(i, j);
            }
        }
        for (int i = 0; i < gridSize; i++) {
            ArrayList<Integer> test = new ArrayList<>();
            for (int j = 0; j < gridSize; j++) {
                if (grid[i][j].getType() != -1) {
                    ArrayList<int[]> cells = grid[i][j].surroundingCellTypes(grid, i, j);
                    ArrayList<Integer> cellTypes = new ArrayList<>();
                    for (int[] x : cells) {
                        cellTypes.add(grid[x[0]][x[1]].getType());
                    }
                    int type = Collections.frequency(cellTypes, -1);
                    grid[i][j].setType(type);
                }
                test.add(grid[i][j].getType());
            }
            System.out.println(test);
        }
    }
    public Cell[][] getGrid() {
        return grid;
    }
    public char[][] getAxes() {
        return axes;
    }
    public void createScreen(Cell[][] grid, char[][] axes) {
        int gridSize = grid.length;
        StringBuilder xAxis = new StringBuilder("   ");
        for (int i = 0; i < gridSize; i++) {

            StringBuilder line = new StringBuilder(axes[1][gridSize - i - 1] + "  ");
            for (int j = 0; j < gridSize; j++) {
                Cell cell =  grid[i][j];
                switch (cell.getStatus()) {
                    case 0:
                        line.append("X ");
                        break;
                    case 1:
                        line.append(grid[i][j].getType()).append(" ");
                        break;
                    case 2:
                        line.append("F ");
                        break;
                }
            }
            xAxis.append(axes[0][i]).append(" ");
            System.out.println(line);
        }
        System.out.println(xAxis);
    }
}
