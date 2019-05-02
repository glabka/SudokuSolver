
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author glabka
 */
public class SudokuSolver {

    /* TODO: UNDO ALL CHANGES IN SOLVESUDOKU TO VERSION WITH GLOBAL VARIABLE PROBABLY
      (because now I have variables in function which are not to be changed by this function)
     */
    public static void solveSudoku(Sudoku s) {
        //initialization of variables
        List<List<Integer>> validNumsList = new ArrayList<>();
        int pointerToRow[] = {0};
        int pointerToColumn[] = {-1};
        nextSquare(s, validNumsList, pointerToRow,  pointerToColumn); // 
                
        boolean forwardDirection = true;

        while (true) {
            List<Integer> validNums = validNumsList.get(validNumsList.size() - 1);
            int row = pointerToRow[0];
            int column = pointerToColumn[0];
            
            if (s.getSudokuInitNums()[row][column] != 0) {
                if (row == 8 && column == 8) {
                    break;
                }
                if (forwardDirection) {
                    nextSquare(s, validNumsList, pointerToRow,  pointerToColumn);
                } else {
                    previousSquare(validNumsList, pointerToRow,  pointerToColumn);
                }
            } else {
                if (validNums.isEmpty()) {
                    forwardDirection = false;
                    s.removeNum(row, column);
                    previousSquare(validNumsList, pointerToRow,  pointerToColumn);
                } else {
                    int num = validNums.get(0);
                    validNums.remove(0);
                    s.insertNum(row, column, num);

                    if (row == 8 && column == 8) {
                        break;
                    }

                    forwardDirection = true;
                    nextSquare(s, validNumsList, pointerToRow,  pointerToColumn);
                }
            }

        }
    }


    /**
     * Procedure changes global variables int row, int column and
     * List<List<Integer>> validNumsList. int row and int column are changed to
     * coordinates of next square in sudoku. ArrayList containing numbers that
     * can be filled to the current square of sudoku (acording to rules of
     * sudoku including already filled up numbers).
     * 
     * @param s sudoku being solved
     * @param validNumsList list of Lists containing Integers which are valid numbers to fill into specific empty sudoku cell
     * @param pointerToRow pointer to row of current cell
     * @param pointerToColumn pointer to row of current cell
     */
    private static void nextSquare(Sudoku s, List<List<Integer>> validNumsList, int[] pointerToRow, int[] pointerToColumn) {
        int row = pointerToRow[0];
        int column = pointerToColumn[0];
        
        pointerToRow[0] = nextRow(row, column);
        pointerToColumn[0] = nextColumn(column);
        validNumsList.add(s.getValidNums(pointerToRow[0], pointerToColumn[0]));
    }

    /**
     * Procedure changes global variables int row, int column and
     * List<List<Integer>> validNumsList. int row and int column are changed to
     * coordinates of previous square in sudoku. Last element of validNumsList
     * is removed.
     * 
     * @param validNumsList list of Lists containing Integers which are valid numbers to fill into specific empty sudoku cell
     * @param pointerToRow pointer to row of current cell
     * @param pointerToColumn pointer to row of current cell
     */
    private static void previousSquare(List<List<Integer>> validNumsList, int[] pointerToRow, int[] pointerToColumn) {
        int row = pointerToRow[0];
        int column = pointerToColumn[0];
        
        pointerToRow[0] = previousRow(row, column);
        pointerToColumn[0] = previousColumn(column);
        validNumsList.remove(validNumsList.size() - 1);
    }

    /**
     * Function returns row + 1 in case column is equal to 8 otherwise returns
     * number int row.
     *
     * @param row selected row
     * @param column selected column
     * @return Returns row + 1 in case column is equal to 8 otherwise returns
     * number int row
     */
    private static int nextRow(int row, int column) {
        if (column == 8) {
            return ++row;
        } else {
            return row;
        }
    }

    /**
     * Function returns row - 1 in case column is equal to 0 otherwise returns
     * number int row.
     *
     * @param row selected row
     * @param column selected column
     * @return Returns row - 1 in case column is equal to 0 otherwise returns
     * number int row.
     */
    private static int previousRow(int row, int column) {
        if (column == 0) {
            return --row;
        } else {
            return row;
        }
    }

    /**
     * Function returns column which comes after variable int column, i.e.
     * column + 1, for int column different from 8 and 0 for column equal to 8.
     *
     * @param column selected column
     * @return Returns column which comes after variable int column.
     */
    private static int nextColumn(int column) {
        if (column == 8) {
            return 0;
        } else {
            return ++column;
        }
    }

    /**
     * Function returns column previous to variable int column, i.e. column - 1,
     * for int column different from 0 and 8 for column equal to 0.
     *
     * @param column selected column
     * @return Returns column previous to variable int column.
     */
    private static int previousColumn(int column) {
        if (column == 0) {
            return 8;
        } else {
            return --column;
        }
    }
}
