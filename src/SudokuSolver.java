
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

    public static void solveSudoku(Sudoku s) throws RemovingInitNumException, InvalidNumException {
        initVariables(s);

        boolean forwardDirection = true;
        while (true) {
            List<Integer> validNums = validNumsLists.get(validNumsLists.size() - 1);
            if (s.getSudokuInitNums()[row][column] != 0) {
                if (row == 8 && column == 8) {
                    break;
                }
                if (forwardDirection) {
                    nextSquare(s);
                } else {
                    previousSquare();
                }
            } else {
                if (validNums.isEmpty()) {
                    forwardDirection = false;
                    s.removeNum(row, column);
                    previousSquare();
                } else {
                    int num = validNums.get(0);
                    validNums.remove(0);
                    s.insertNum(row, column, num);

                    if (row == 8 && column == 8) {
                        break;
                    }

                    forwardDirection = true;
                    nextSquare(s);
                }
            }

        }
    }

    private static List<List<Integer>> validNumsLists;
    private static int row;
    private static int column;

    /**
     * Procedure that initiate global variables
     * @param s 
     */
    private static void initVariables(Sudoku s) {
        validNumsLists = new ArrayList<>();
        row = 0;
        column = -1; // -1 so the nextSuare(s) add firt element to validNumsList
        nextSquare(s);
    }

    /**
     * Procedure changes global variables int row, int column and List<List<Integer>> validNumsList.
     * int row and int column are changed to coordinates of next square in sudoku. 
     * ArrayList containing numbers that can be filled to the current square of 
     * sudoku (acording to rules of sudoku including already filled up numbers).
     * @param s sudoku being solved
     */
    private static void nextSquare(Sudoku s) {
        row = nextRow(row, column);
        column = nextColumn(column);
        validNumsLists.add(s.getValidNums(row, column));
    }

    /**
     * Procedure changes global variables int row, int column and List<List<Integer>> validNumsList.
     * int row and int column are changed to coordinates of previous square in sudoku. 
     * Last element of validNumsList is removed.
     */
    private static void previousSquare() {
        row = previousRow(row, column);
        column = previousColumn(column);
        validNumsLists.remove(validNumsLists.size() - 1);
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
