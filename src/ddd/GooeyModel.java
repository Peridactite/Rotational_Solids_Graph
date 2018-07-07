/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ddd;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author David
 */
public class GooeyModel {
    private String function = ""; //user typed function
    private ArrayList<Term> terms; //function is seperated into terms by + - / * 
    
    public void storeTerms(){
        function = function.trim().replaceAll(" ",""); //remove whitespace
        //      \+|-+(?!\))|^(\(-\))|\/|\*      Here's a fun regex I think will work, but might need negative look behind
        String regDelim = "\\+|-+(?!\\))|^\\(-\\)]|\\/|\\*"; //seperate terms by + - / * //new
        //String regDelim = "\\+|-|\\/|\\*"; //seperate terms by + - / *  //old
        Scanner scan = new Scanner(function).useDelimiter(regDelim);
        
        //terms = null; //clean out old list
        terms = new ArrayList<>();
        while(scan.hasNext()){
            terms.add(new Term(scan.next()));
        }
    }

    public ArrayList<Term> getTerms() {
        return terms;
    }
    
    public String getFunction() {
        return function;
    }
    public void setFunction(String function) {
        this.function = function;
    }
}
