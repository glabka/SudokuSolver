/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author glabka
 */
public class NumberOutOfBoundsException extends RuntimeException{

    NumberOutOfBoundsException(){
        super();
    }
    
    NumberOutOfBoundsException(String s){
        super(s);
    }
}
