/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ddd;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;

/**
 *
 * @author David
 */
public class Term {
    private String term;
    private boolean hasVariable;
    private float coeff = -999;
    private float exponent = -999; 
    
    
    
    public Term(String term){
        this.term = term;
        term.toLowerCase();
        hasVariable = (regexChecker("[A-Za-z]", term).size() > 0); //if there are letters then this term has variable
        if(hasVariable){
            storeNums();
        }
        System.out.println("Term: " + term);
    }
    
    @Override
    public String toString(){
        return term;
    }
    
    //Problems: parenthesis, user error, brackets, etc TODObugfix
    //      Term: 2x2 problematic
    /**
     * Takes a term and stores coefficient and exponent when a variable is present
     */
    private void storeNums(){
        Pattern varReg = Pattern.compile("[^0-9.]"); //store all nums by avoid anything that's not a number
        Scanner scan = new Scanner(term).useDelimiter(varReg); 
        
//        while(scan.hasNext()){
//            //currently having issues scsanning coefficient "2.0" 
//            System.out.println("SCAN" + scan.next());
//        }
        
        String[] tempNums = new String[2];
        //if there's null string then coefficient is 1, otherwise store the coefficient
        if(scan.hasNext()){
            tempNums[0] = scan.next();
            if(tempNums[0] == null || tempNums[0].length() == 0){
                coeff = 1;
            }else{
                coeff = Float.parseFloat(tempNums[0]);
            }
        } else { 
            coeff = 1;
        }
        if(scan.hasNext()){
            tempNums[1] = scan.next();
            if(tempNums[1] == null || tempNums[1].length() == 0){
                if(scan.hasNext()){//this specific condition is whenever decimal was causing a 3 scans, the middle one empty
                    tempNums[1] = scan.next();
                    if(tempNums[1] == null || tempNums[1].length() == 0){
                        exponent = 1;
                    }else{
                        exponent = Float.parseFloat(tempNums[1]);
                    }
                } else { //I don't really know why the empty scan was happening. Research? TODO
                    exponent = 1;
                }
            }else{
                exponent = Float.parseFloat(tempNums[1]);
            }
        } else { 
            exponent = 1;
        }
        
        System.out.println("coeff: " + coeff);
        System.out.println("exp: " + exponent);
        if(scan.hasNext()){
            System.out.println("ERR Term: saveNums() - invalid term");
            System.out.println("ERR Invalid Term: " + scan.next());
        }
    }
    
    //automatically trims
    public static ArrayList<String>  regexChecker(String theRegex, String str2Check){
        // You define your regular expression (REGEX) using Pattern
        Pattern checkRegex = Pattern.compile(theRegex);
        // Creates a Matcher object that searches the String for
        // anything that matches the REGEX
        Matcher regexMatcher = checkRegex.matcher( str2Check );
        // Cycle through the positive matches and print them to screen
        // Make sure string isn't empty and trim off any whitespace
        
        ArrayList<String> regList = new ArrayList<>();
        
        while ( regexMatcher.find() ){
            if (regexMatcher.group().length() != 0){
                regList.add(regexMatcher.group().trim());

                // You can get the starting and ending indexs
                //System.out.println( "Start Index: " + regexMatcher.start());
                //System.out.println( "Start Index: " + regexMatcher.end());
            }
        }
        return regList;
    }

    public boolean hasVariable() {
        return hasVariable;
    }

    public float getCoeff() {
        return coeff;
    }

    public float getExponent() {
        return exponent;
    }
    
}
