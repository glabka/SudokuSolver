
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
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
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
        sud1.print();
        SudokuSolver.solveSudoku(sud1);
        sud1.print();

//        int sudArray[][] = sud1.getSudokuNums();
//        sudArray[0][0] = 1;
//        System.out.println("");
//        sud1.print();
//        ArrayList<Integer> a = sud1.getPossibleNums(7,1);
//        System.out.println(a);

//        List<List<Integer>> l = new ArrayList<>();
//        l.add(sud1.getPossibleNums(0, 0));
//        l.add(sud1.getPossibleNums(0, 1));
//        System.out.println("l.size = " + l.size());
//        System.out.println("l.get(0).size() = " + l.get(0).size());
//        System.out.println("");
//        System.out.println("l.get(0).get(0) = " + l.get(0).get(0));
//        System.out.println("l.get(0).get(1) = " + l.get(0).get(1));
//        System.out.println("l.get(0).get(2) = " + l.get(0).get(2));
//        System.out.println("l.get(0).get(3) = " + l.get(0).get(3));
//        System.out.println("");
//        System.out.println("l.get(1) = " + l.get(1));
//        System.out.println("l.get(0).remove(3);");
//        l.get(0).remove(3);
//        System.out.println("l.get(0).size() = " + l.get(0).size());
    }

}
