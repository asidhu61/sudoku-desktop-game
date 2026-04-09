package solver;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import sudoku.model.exceptions.FillingBoardSudokuException;
import sudoku.model.models.SudokuBoard;
import sudoku.model.solver.BacktrackingSudokuSolver;
import sudoku.model.solver.SudokuSolver;

public class SudokuSolverTest {

    @Test
    void testSolveValidBoard() throws FillingBoardSudokuException {
        int[][] grid = {
            {5,3,0, 0,7,0, 0,0,0},
            {6,0,0, 1,9,5, 0,0,0},
            {0,9,8, 0,0,0, 0,6,0},

            {8,0,0, 0,6,0, 0,0,3},
            {4,0,0, 8,0,3, 0,0,1},
            {7,0,0, 0,2,0, 0,0,6},

            {0,6,0, 0,0,0, 2,8,0},
            {0,0,0, 4,1,9, 0,0,5},
            {0,0,0, 0,8,0, 0,7,9}
        };

        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);

        // Load the puzzle — grid[row][col], getField(col, row)
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (grid[row][col] != 0) {
                    board.getField(col, row).setValue(grid[row][col]);
                }
            }
        }

        solver.solve(board);

        assertTrue(board.isValidSudoku(), "Solver should produce a valid board");

        // Check known pre-filled values are preserved (grid[row][col] → getField(col, row))
        assertEquals(5, board.getField(0, 0).getValue()); 
        assertEquals(9, board.getField(4, 1).getValue()); 
    }
}
