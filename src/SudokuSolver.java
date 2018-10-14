
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

    /*
    static void solveSudoku(Sudoku s) {
        ArrayList<ArrayList<Integer>> possNums = new ArrayList<ArrayList<Integer>>(9 * 9);
        int initNums[][] = s.getSudokuInitNums();
        int p = 0; // pointer to possNums
        int p2 = -1; // second pointer to possNums
        int row;
        int column;

        while (true) {
            row = p / 9;
            column = p % 9;

            if (initNums[row][column] != 0) {
                //the is initial number in sudoku
                if (row == 8 && column == 8) {
                    break;
                } else if (p >= p2) {
                    p2 = p;
                    p++;
                } else {
                    p2 = p;
                    p--;
                }
            } else {
                // place in sudoku for trying out filling it
                if (row == 8 && column == 8 && possNums.get(p).isEmpty()) {
                    break;
                } else if (p >= p2){
                    // pointer is going forward therefore possibleNums are added
                    possNums.add(p, s.getPossibleNums(row, column));
                    // TODO - code for trying out (inserting) some number
                    p2 = p;
                    p++;
                } else {
                     if(possNums.get(p).isEmpty()){
                         p2 = p;
                         p--;
                     } else {
                         // TODO -  code for trying out (inserting) some number
                         p2 = p;
                         p++;
                     }
                    
                }
            }

        }
    }
     */
    public static void solveSudoku(Sudoku s) {
        possNumsList = new ArrayList<>();
        row = 0;
        column = -1; // so the nextSuare(s) add firt element to possNumsList
        nextSquare(s);

        boolean forwardDirection = true;
        while (true) {
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

                        //debug start
//                        System.out.println("");
//                        s.print();
                        // debug end

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

    //TODO: remove unused function
    // returns ArayList<Integer> containing numbers which are possible to be 
    // filled into specified row and column of sudoku
    private static List<Integer> getPossNumsList(Sudoku s, int row, int column) {
        if (possNumsList == null) { // inicialization when function solveSudoku is called
            possNumsList = new ArrayList<>();
        }
        int pointer = row * 9 + column + 1;
        if (possNumsList.size() < pointer) {
            int diff = pointer - possNumsList.size(); //TODO: I am always counting on that diff will be 1
            for (int i = 0; i < diff; i++) {
                // TODO: problem with statement below - for diff > 1 it is incorrect -> correction here, ore in function solve sudoku.
                possNumsList.add(s.getPossibleNums(row, column));
            }
        }
        return possNumsList.get(pointer - 1);
    }

    // initialize PossNumsArray[][][] with nulls
//    private static void initPossNumsArray(Integer a[][][]) {
//        for (int i = 0; i < a.length; i++) {
//            for (int j = 0; j < a[i].length; j++) {
//                a[i][j] = null;
//            }
//        }
//    }
// ends up in stack overflow
// returns true if sudoku was solved
    private static boolean solveSudokuRecursive(Sudoku s, int row, int column) {
        if (row == 8 && column == 8) {
            return lastSquare(s);
        }

        if (s.getSudokuInitNums()[row][column] != 0) {
            return solveSudokuRecursive(s, nextRow(row, column), nextColumn(column));
        } else {
            List<Integer> possNums = s.getPossibleNums(row, column);
            if (possNums.isEmpty()) {
                return false;
            } else {
                try {
                    boolean sudokuIsSolved;
                    for (Iterator<Integer> i = possNums.iterator(); i.hasNext();) {
                        Integer num = i.next();
                        s.insertNum(row, column, num);
                        sudokuIsSolved = solveSudokuRecursive(s, nextRow(row, column), nextColumn(column));
                        if (sudokuIsSolved) {
                            return true;
                        }
                    }
                    return false; // neither possible number solved sudoku
                } catch (Exception e) {
                    System.out.println(e);
                    return true; // TODO: probably bad way to handle exception, thow this exception should not ever occure.
                }
            }
        }

    }

    // returns true in case sudoku is solved, false in case it isn't
    private static boolean lastSquare(Sudoku s) {
        if (s.getSudokuInitNums()[8][8] != 0) {
            return true;
        } else {
            List<Integer> possNums = s.getPossibleNums(8, 8);
            if (possNums.isEmpty()) {
                return false;
            } else {
                try {
                    s.insertNum(8, 8, (int) possNums.get(0));
                } catch (Exception e) {
                    System.out.println(e);
                }
                return true;
            }
        }
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
