
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

    private int sudokuNums[][]; // initial numbers of unsolved sudoku plus inserted numbers of potential solution
    private final int sudokuInitNums[][]; // initial numbers of unsolved sudoku

    /**
     * Constructor of Sudoku with variable int sudokuInitNums. sudokuInitNums 
     * represents initial numbers of completely empty sudoku.
     * @param sudokuInitNums initial numbers of empty sudoku
     * @throws WrongSizedArrayException wrong sized array sudokuInitNums
     * @throws NumberOutOfBounds number out of bounds 0 - 9 in  sudokuInitNums
     */
    public Sudoku(int sudokuInitNums[][]) throws WrongSizedArrayException, NumberOutOfBounds {
        // testing number of rows
        if (sudokuInitNums.length != 9) {
            throw new WrongSizedArrayException("wrong number of rows: " + sudokuInitNums.length);
        }
        // testing number of columns
        for (int i = 0; i < sudokuInitNums.length; i++) {
            if (sudokuInitNums[i].length != 9) {
                throw new WrongSizedArrayException("wrong number of columns: " + sudokuInitNums.length + " in row number: " + i);
            }
        }
        // testing values in sudokuInit Nums
        for (int i = 0; i < sudokuInitNums.length; i++) {
            for (int j = 0; j < sudokuInitNums[i].length; j++) {
                int val = sudokuInitNums[i][j];
                if (val < 0 || val > 9) {
                    throw new NumberOutOfBounds("invalid value in sudokuInitNums[" + i + "][" + j + "] = " + val);
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
    
    /**
     * Constructor with variable Sudoku s. Values filled up in the Sudoku s are
     * not present in new Sudoku - they are only present if they are initial numbers
     * @param s sudoku with values to be copied
     */
    public Sudoku(Sudoku s){
        sudokuNums = s.getSudokuInitNums();
        sudokuInitNums = s.getSudokuInitNums();
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
        ArrayList<Integer> validNums = getValidNums(row, column);     
        if(validNums.contains((Integer) num)){ // TODO test when initNum is selected
            sudokuNums[row][column] = num;
        } else {
            //TODO
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
            // TODO
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
     * in sudoku defined by row and column according to rules of sudoku 
     */
    public ArrayList<Integer> getValidNums(int row, int column) {
        if (sudokuInitNums[row][column] != 0) {
            return null;
        } else {
            ArrayList<Integer> validNums = new ArrayList<Integer>(9);
            for (int i = 1; i < 10; i++) {
                validNums.add(i);
            }
            // valid numbers according to the column
            for (int i = 0; i < 9; i++) {
                validNums.remove((Integer) sudokuNums[row][i]);
            }
            // valid numbers according to the row
            for (int i = 0; i < 9; i++) {
                validNums.remove((Integer) sudokuNums[i][column]);                
            }
            // valid numbers according to square
            int i = row - (row % 3); // substracting remainder sets value for iterator to beginning of 9x9 square
            int j = column - (column % 3); // substracting remainder sets value for iterator to beginning of 9x9 square
            for (int k = i; k < i+3; k++) {
                for (int l = j; l < j+3; l++) {
                    validNums.remove((Integer) sudokuNums[k][l]);
                }
            }
            return validNums;
        }
    }

    /**
     * Prints out sudoku with highlighted initial numbers
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
