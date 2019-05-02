/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author glabka
 */
public class RemovingInitNumException extends RuntimeException{

    RemovingInitNumException(){
        super();
    }
    
    RemovingInitNumException(String s){
        super(s);
    }
}
