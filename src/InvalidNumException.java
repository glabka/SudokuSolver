/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author glabka
 */
public class InvalidNumException extends RuntimeException{

    InvalidNumException(){
        super();
    }
    
    InvalidNumException(String s){
        super(s);
    }
}
