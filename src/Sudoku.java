
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author glabka
 */
public class Sudoku {

    private int sudokuNums[][];
    // TODO: maybay this variable should be final
    private int sudokuInitNums[][]; // initial numbers of unsolved sudoku

    public Sudoku(int sudokuInitNums[][]) throws Exception {
        // testing number of rows
        if (sudokuInitNums.length != 9) {
            throw new WrongSizedArrayException("wrong number of rows: " + sudokuInitNums.length);
        }
        // testing number of columns
        for (int i = 0; i < sudokuInitNums.length; i++) {
            if (sudokuInitNums[i].length != 9) {
                throw new WrongSizedArrayException("wron number of columns: " + sudokuInitNums.length + " in row number: " + i);
            }
        }
        // testing values in sudokuInit Nums
        for (int i = 0; i < sudokuInitNums.length; i++) {
            for (int j = 0; j < sudokuInitNums[i].length; j++) {
                int val = sudokuInitNums[i][j];
                if (val < 0 || val > 9) {
                    throw new Exception("invalid value in sudokuInitNums[" + i + "][" + j + "] = " + val);
                }
            }
        }

        // copying initial numbers to sudokuNums
        sudokuNums = new int[9][9];
        for (int i = 0; i < sudokuNums.length; i++) {
            for (int j = 0; j < sudokuNums[i].length; j++) {
                sudokuNums[i][j] = sudokuInitNums[i][j];
            }
 
        }
        
        this.sudokuInitNums = sudokuInitNums;
    }
    
    public Sudoku(Sudoku s){
        sudokuInitNums = s.getSudokuInitNums();
        sudokuNums = s.getSudokuNums();
    }

    /**
     * Function returns copy of array representing sudoku. The array contains
     * both initial numbers and inserted numbers.
     * @return Returns copy of array representing sudoku.
     */
    public int[][] getSudokuNums() {
        return copyArrays(sudokuNums);
    }

    /**
     * Function returns initial numbers of sudoku
     * @return Returns initial number of sudoku in array int[][]
     */
    public int[][] getSudokuInitNums() {
        return copyArrays(sudokuInitNums);
    }

    /**
     * Returns new array with values from array ar
     * @param ar the array holding values to be copied
     * @return Returns new array with values from array ar
     */
    private int[][] copyArrays(int ar[][]) {
        int newAr[][] = new int[ar.length][ar[0].length];
        for (int i = 0; i < ar.length; i++) {
            for (int j = 0; j < ar[i].length; j++) {
                newAr[i][j] = ar[i][j];
            }
        }
        return newAr;
    }

    /**
     * Inserts number num to square in sudoku defined by row and column
     * @param row the row of selected square
     * @param column the column of selected square
     * @param num the number to be inserted
     */
    public void insertNum(int row, int column, int num){
        ArrayList<Integer> possNums = getPossibleNums(row, column);     
        if(possNums.contains((Integer) num)){
            sudokuNums[row][column] = num;
        } else {
            // debug
            System.out.println("Invalid number for insertion, i.e. " + num);
        }
    }
    
    /**
     * Removes number inserted in sudoku in square of selected row and column.
     * In case an initial number is selected throws RuntimeException.
     * @param row the row of selected square 
     * @param column the column of selected square
     */
    public void removeNum(int row, int column){
        if(sudokuInitNums[row][column] == 0){
            sudokuNums[row][column] = 0;
        } else {
            // debug
            System.out.println("An attempt to remove number which is initial part of this sudoku");
        }
    }

    /**
     * Returns ArrayList containing numbers which can be inserted into a square
     * in sudoku defined by row and column. Returns null in case a square with
     * initial number is selected.
     * @param row the row of selected square
     * @param column the column of selected square
     * @return ArrayList containing numbers which can be inserted into a square
     * in sudoku defined by row and column
     */
    public ArrayList<Integer> getPossibleNums(int row, int column) {
        if (sudokuInitNums[row][column] != 0) {
            return null;
        } else {
            ArrayList<Integer> possNum = new ArrayList<Integer>(9);
            for (int i = 1; i < 10; i++) {
                possNum.add(i);
            }
            // possible numbers according to the row
            for (int i = 0; i < 9; i++) {
                possNum.remove((Integer) sudokuNums[row][i]);
            }
            // possible numbers according to the column
            for (int i = 0; i < 9; i++) {
                possNum.remove((Integer) sudokuNums[i][column]);                
            }
            // possible numbers according to square
            int i = row - (row % 3); // substracting remainder - it sets right value for iterator
            int j = column - (column % 3); // substracting remainder - it sets right value for iterator
            for (int k = i; k < i+3; k++) {
                for (int l = j; l < j+3; l++) {
                    possNum.remove((Integer) sudokuNums[k][l]);
                }
            }
            return possNum;
        }
    }

    /**
     * Prints out sudoku
     */
    public void print() {
        for (int i = 0; i < 9; i++) {
            System.out.print("|");
            for (int j = 0; j < 9; j++) {
                boolean isInitNum = sudokuInitNums[i][j] != 0;
                System.out.print(NumberToString(isInitNum, sudokuNums[i][j]) + "|");
                if (j == 2 || j == 5) {
                    System.out.print(" |");
                }
            }
            System.out.println("");
            if ((i + 1) % 3 == 0) { // prints out after every 3 rows
                System.out.println("-----------------------------------------");
            }
        }
    }
    
    /** 
     * Creates string from number in sudoku. It highlights initial numbers.
     * @param isInitNumber says wheter the number num is initial number
     * @param num specific number of some square in sudoku
     * @return 
     */

    private String NumberToString(boolean isInitNumber, int num){
        String s;
        if (isInitNumber) {
            s = "." + num + ".";
            return s;
        } else {
            s = " " + num + " ";
            return s;
        }
            
    }
}
