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
    private float coeff = 1;
    private float exponent = 1; 
    private boolean isExpNeg = false;
    private boolean isCoeffNeg = false;
    
    
    
    public Term(String term){
        this.term = term;
        term.toLowerCase();
        System.out.println("Term: " + this.term);
        checkNegative();
        System.out.println("Term after neg: " + this.term);
        hasVariable = (regexChecker("[A-Za-z]", this.term).size() > 0); //if there are letters then this term has variable
        if(hasVariable){
            storeNums();
            System.out.println("uncomment later and fix");
//            if(exponent == 0){
//                coeff = 1;
//                hasVariable = false;
//            }
        }
        
    }
    
    public void refreshTerm(){
        term = "";
        if(isCoeffNeg){
            term = term.concat("-");
        }
        term = term.concat(String.valueOf(coeff));
        if(hasVariable){
            term = term.concat("x");
        }
        if(exponent != 0 && exponent != 1){
            term = term.concat("^" + exponent);
        }
    }
    
    @Override
    public String toString(){
        refreshTerm();
        System.out.println("ceoff9 " + coeff);
        System.out.println("hasVariable " + hasVariable);
        System.out.println("exponenet " + exponent);

        return term;
    }
    
    //Problems: parenthesis, user error, brackets, etc TODObugfix
    //      Term: 2x2 problematic
    /**
     * Takes a term and stores coefficient and exponent when a variable is present
     */
    private void storeNums(){
        //Pattern varReg = Pattern.compile("[^0-9-.]|[\\s]");//tried to do this to avoid whitespace
        Pattern varReg = Pattern.compile("[^0-9-.]"); //store all nums by avoid anything that's not a number
        Scanner scan = new Scanner(term).useDelimiter(varReg); 
        
//        while(scan.hasNext()){
//            //currently having issues scsanning coefficient "2.0" 
//            System.out.println("SCAN" + scan.next());
//        }
        
        String[] tempNums = new String[2];
        //if there's null string then coefficient is 1, otherwise store the coefficient
        if(scan.hasNext()){
            tempNums[0] = scan.next();
            System.out.println("tempNum0: " + tempNums[0]);
            if(tempNums[0] == null || tempNums[0].length() == 0){
                //coeff = 1; DELETE taking this out because coeff used to be -999 and this was necessary
            }else{
                coeff = coeff * Float.parseFloat(tempNums[0]);
            }
        } else {
            //coeff = 1;
        }
        if(scan.hasNext()){
            tempNums[1] = scan.next();
            System.out.println("tempNum1: " + tempNums[1]);
            if(tempNums[1] == null || tempNums[1].length() == 0){
                //I believe that I can avoid this odd code if I get regex to remove the "" 
                if(scan.hasNext()){//this specific condition is whenever decimal was causing a 3 scans, the middle one empty
                    tempNums[1] = scan.next();
                    System.out.println("tempNum1: " + tempNums[1]);
                    if(tempNums[1] == null || tempNums[1].length() == 0){
                        //exponent = 1;
                    }else{
                        exponent = exponent * Float.parseFloat(tempNums[1]);
                    }
                } else { //I don't really know why the empty scan was happening. Research? TODO
                    //exponent = 1;
                }
            }else{
                exponent = exponent * Float.parseFloat(tempNums[1]);
            }
        } else { 
            //exponent = 1;
        }
        
        System.out.println("coeff: " + coeff);
        System.out.println("exp: " + exponent);
        if(scan.hasNext()){
            System.out.println("ERR Term: saveNums() - invalid term");
            System.out.println("ERR Invalid Term: " + scan.next());
        }
    }
    
    public void checkNegative(){
        String tempString = term;
        int temp = 0; //Tracks number of times '-' appears
        // if the exponent is negative, mark term and check if coeff is also negative (2 negative signs) 
        if(tempString.contains("^(-)")){
            isExpNeg = true; //might be unneccesary line below is better.
            exponent = -1;
            for(int i = 0; i < tempString.length(); i++){
                if(tempString.charAt(i)=='-'){
                    temp++;
                }
            }
            if (temp >= 2){
                isExpNeg = true;
                isCoeffNeg = true;
                exponent = -1;
                coeff = -1;
            }
        //otherwise if there is no negative exp you can just look for one single '-'
        } else { 
            for(int i = 0; i < tempString.length(); i++){
                temp = 0;
                if(tempString.charAt(i)=='-'){
                    isCoeffNeg = true;
                    coeff = -1;
                    i = tempString.length();
                }
            }
        }
        //make sure to remove negs so that the other method can parse correctly
        StringBuffer buff = new StringBuffer(tempString);
        for(int i = 0; i < buff.length(); i++){
            if(buff.charAt(i) == '-' || buff.charAt(i) == '(' || buff.charAt(i) == ')' ){
                buff.deleteCharAt(i);
                    i--; //because we removed a character, we should back up one step in index
            }
        }
        //System.out.println("buff " + buff.toString());
        this.term = buff.toString();
        //System.out.println("buffterm? " + this.term);
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

    public Term plus(Term otherTerm) {
        Term newTerm = this;
        newTerm.coeff = this.coeff + otherTerm.coeff;
        return newTerm;
    }
    
    public void multiply(Term otherTerm){
//        Term newTerm = this;
//        newTerm.coeff = newTerm.coeff * otherTerm.coeff;
//        newTerm.exponent = newTerm.exponent + otherTerm.exponent;
//        return newTerm;
        System.out.println("COEFF1" + coeff);
        System.out.println("CEOFF2" + otherTerm.coeff);
        coeff = coeff * otherTerm.coeff;
        System.out.println("COEFF3" + coeff);
        exponent = exponent * otherTerm.exponent;
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

    public boolean isExpNeg() {
        return isExpNeg;
    }

    public boolean isCoeffNeg() {
        return isCoeffNeg;
    }
    
}
