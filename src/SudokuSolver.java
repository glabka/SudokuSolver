
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

    public static void solveSudoku(Sudoku s) {
        initVariables(s);

        boolean forwardDirection = true;
        while (true) {
            // TODO: getters for row, column, and possNumsList
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
                    try {
                        s.removeNum(row, column);
                    } catch (Exception ex) {
                        Logger.getLogger(SudokuSolver.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    previousSquare();
                } else {
                    int num = possNums.get(0);
                    possNums.remove(0);
                    try {
                        s.insertNum(row, column, num);
                    } catch (Exception e) {
                        System.out.println(e);
                    }

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
    
    private static void initVariables(Sudoku s){
        possNumsList = new ArrayList<>();
        row = 0;
        column = -1; // so the nextSuare(s) add firt element to possNumsList
        nextSquare(s);
    }
    


    private static void nextSquare(Sudoku s) {
        row = nextRow(row, column);
        column = nextColumn(column);
        possNumsList.add(s.getPossibleNums(row, column));
    }

    private static void previousSquare() {
        row = previousRow(row, column);
        column = previousColumn(column);
        possNumsList.remove(possNumsList.size() - 1);
    }

    private static int nextRow(int row, int column) {
        if (column == 8) {
            return ++row;
        } else {
            return row;
        }
    }

    private static int previousRow(int row, int column) {
        if (column == 0) {
            return --row;
        } else {
            return row;
        }
    }

    private static int nextColumn(int column) {
        if (column == 8) {
            return 0;
        } else {
            return ++column;
        }
    }

    private static int previousColumn(int column) {
        if (column == 0) {
            return 8;
        } else {
            return --column;
        }
    }
}
