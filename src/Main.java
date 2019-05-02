
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author glabka
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int sudokuNums1[][] = {
            {0, 2, 0, 0, 8, 0, 0, 3, 0},
            {3, 0, 0, 0, 7, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 9, 0},
            {0, 0, 6, 2, 4, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1},
            {2, 0, 8, 0, 3, 6, 0, 0, 0},
            {4, 0, 0, 0, 6, 0, 0, 0, 0},
            {5, 0, 2, 4, 0, 0, 0, 0, 0},
            {0, 6, 0, 0, 9, 7, 8, 0, 4}};

        Sudoku sud1 = new Sudoku(sudokuNums1);
        System.out.println("unsolved sudoku:");
        sud1.print();
        SudokuSolver.solveSudoku(sud1);
        System.out.println("");
        System.out.println("solved sudoku:");
        sud1.print();
        

    }

}
