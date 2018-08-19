package ddd;

import java.util.ArrayList;

/**
 *
 * @author David
 */
public class Function {
    ArrayList<Term> terms;
    Function volume;
    
    public Function(ArrayList<Term> terms){
        this.terms = terms;
    }
    //TODO eventual support for 
    public double solveVolume(double xStart, double xEnd){
        volume  = squareFunction();
//        increaseExponentByOne();
//        divideCoefficientByExponent();
//        solveUpper();
//        solveLower();
        
        return 0.0;
    }
    
    private Function squareFunction(){
        ArrayList<Term> squaredTerms = new ArrayList<Term>();
        for(int row = 0; row < terms.size(); row++){
            for(int col = 0; col < terms.size(); col++){
                Term rowTerm = terms.get(row);
                Term colTerm = terms.get(col);
                rowTerm.multiply(colTerm);
                squaredTerms.add(rowTerm);
            }
        }
        return new Function(squaredTerms);
    }
    
    @Override
    public String toString(){
        String functionPrint = "";
            functionPrint = functionPrint.concat("(" + terms.get(0).toString() + ")");
        for(int i = 1; i < terms.size(); i++){
            functionPrint = functionPrint.concat(" + (" + terms.get(i) + ")");
        }
        return functionPrint;
    }
    
    public static void main(String[] args){
        System.out.println("Testing Functions");
        Term term1 = new Term("2x^3");
        Term term2 = new Term("3x^5");
        ArrayList<Term> testTerms = new ArrayList<Term>();
        testTerms.add(term1);
        testTerms.add(term2);
        Function myFunc = new Function(testTerms);
        System.out.println("test print func1 " + myFunc);
        
        
        System.out.println(myFunc.squareFunction());
        
        
    }
}
