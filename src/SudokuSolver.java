
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

    /* TODO: I might rewrite this function by making local variables row[] = new int[1] and 
    the same goes for column, using array as pointer
     */
    public static void solveSudoku(Sudoku s) {
        initVariables(s);

        boolean forwardDirection = true;
        while (true) {
            // TODO: getters for row, column, and possNumsList; Mabey not - makes code less understandable
            List<Integer> possNums = possNumsList.get(possNumsList.size() - 1);
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
                if (possNums.isEmpty()) {
                    forwardDirection = false;
                    s.removeNum(row, column);
                    previousSquare();
                } else {
                    int num = possNums.get(0);
                    possNums.remove(0);
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

    private static List<List<Integer>> possNumsList;
    private static int row;
    private static int column;

    /**
     * Procedure that 
     * @param s 
     */
    private static void initVariables(Sudoku s) {
        possNumsList = new ArrayList<>(); //TODO: what does the <> in ArrayList<>() reprezents
        row = 0;
        column = -1; // so the nextSuare(s) add firt element to possNumsList
        nextSquare(s);
    }

    /**
     * Procedure changes global variables int row, int column and List<List<Integer>> possNumsList.
     * int row and int column are changed to coordinates of next square in sudoku. 
     * ArrayList containing numbers that can be filled to the current square of 
     * sudoku (acording to rules of sudoku including already filled up numbers).
     * @param s sudoku being solved
     */
    private static void nextSquare(Sudoku s) {
        row = nextRow(row, column);
        column = nextColumn(column);
        possNumsList.add(s.getPossibleNums(row, column));
    }

    /**
     * Procedure changes global variables int row, int column and List<List<Integer>> possNumsList.
     * int row and int column are changed to coordinates of previous square in sudoku. 
     * Last element of possNumsList is removed.
     */
    private static void previousSquare() {
        row = previousRow(row, column);
        column = previousColumn(column);
        possNumsList.remove(possNumsList.size() - 1);
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
