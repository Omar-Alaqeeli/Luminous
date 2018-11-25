//Analyze variable inquired by user

import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.text.*;

class analyzer {
    public static void analyzer(String inquiredVariable, Map<String,String> dictionary)
    throws FileNotFoundException {
        Map<String,String> tempDictionary = new LinkedHashMap<>();
        Map<String,String> operand1 = new LinkedHashMap<>();
        Map<String,String> operator = new LinkedHashMap<>();
        Map<String,String> operand2 = new LinkedHashMap<>();
        Map<String,String> operand3 = new LinkedHashMap<>();
        Map<String,Double> cache = new LinkedHashMap<>();
        
        String valueInDictionary = "";
        String findOperator = "";
        String opd1 = "";
        String opr = "";
        String opd2 = "";
        String opd3 = "";
        String oprExtra = "";
        String completeOpr = "";
        String deleteKey = "";
        String skipped = "";
        
        for(String u:dictionary.keySet()) {
            tempDictionary.put(u,dictionary.get(u));
        }
        
        for(String u:tempDictionary.keySet()) {
            valueInDictionary = tempDictionary.get(u);
            if(valueInDictionary==null) {
                continue;
            }
            else {
                Scanner scannedValueInDictionary = new Scanner(valueInDictionary);
                findOperator = scannedValueInDictionary.next();
                
                if(findOperator.equalsIgnoreCase("int")||findOperator.equalsIgnoreCase("flo")||findOperator.equalsIgnoreCase("right")||findOperator.equalsIgnoreCase("succ")||findOperator.equalsIgnoreCase("init")||findOperator.equalsIgnoreCase("first")||findOperator.equalsIgnoreCase("next")||findOperator.equalsIgnoreCase("all")||findOperator.equalsIgnoreCase("elements")||findOperator.equalsIgnoreCase("NOT")) {
                    opr = findOperator.toLowerCase();
                    opd1 = scannedValueInDictionary.next();
                    operator.put(u,opr);
                    operand1.put(u,opd1);
                    tempDictionary.put(u,null);
                }
            }
        }
        
        for(String u:tempDictionary.keySet()) {
            valueInDictionary = tempDictionary.get(u);
            if(valueInDictionary==null) {
                continue;
            }
            else {
                Scanner scannedValueInDictionary = new Scanner(valueInDictionary);
                
                opd1 = scannedValueInDictionary.next();
                opr = scannedValueInDictionary.next();
                oprExtra = scannedValueInDictionary.next();
                
                if(oprExtra.equals("*")||oprExtra.equals("=")) {
                    opd2 = scannedValueInDictionary.next();
                    completeOpr = opr + oprExtra ;
                    
                    operand1.put(u,opd1);
                    operator.put(u,completeOpr);
                    operand2.put(u,opd2);
                    tempDictionary.put(u,null);
                }
            }
        }
        
        for(String u:tempDictionary.keySet()) {
            valueInDictionary = tempDictionary.get(u);
            if(valueInDictionary==null) {
                continue;
            }
            else {
                Scanner scannedValueInDictionary = new Scanner(valueInDictionary);
                opd1 = scannedValueInDictionary.next();
                opr = scannedValueInDictionary.next();
                
                if(opr.equalsIgnoreCase("sby")||opr.equalsIgnoreCase("fby")||opr.equalsIgnoreCase("asa")||opr.equalsIgnoreCase("upon")||opr.equalsIgnoreCase("whenever")||opr.equalsIgnoreCase("wherever")||opr.equalsIgnoreCase("AND")||opr.equalsIgnoreCase("OR")||opr.equals("*")||opr.equals("+")||opr.equals("-")||opr.equals("%")||opr.equals("/")||opr.equals(">")||opr.equals("<")||opr.equalsIgnoreCase("eq")||opr.equalsIgnoreCase("ne")) {
                    opd2 = scannedValueInDictionary.next();
                    operator.put(u,opr.toLowerCase());
                    operand1.put(u,opd1);
                    operand2.put(u,opd2);
                    tempDictionary.put(u,null);
                }
            }
        }
        for(String u:tempDictionary.keySet()) {
            valueInDictionary = tempDictionary.get(u);
            if(valueInDictionary==null) {
                continue;
            }
            else {
                Scanner scannedValueInDictionary = new Scanner(valueInDictionary);
                findOperator = scannedValueInDictionary.next();
                
                if(findOperator.equalsIgnoreCase("if")) {
                    opr = findOperator.toLowerCase();;
                    opd1 = scannedValueInDictionary.next();
                    skipped = scannedValueInDictionary.next();
                    opd2 = scannedValueInDictionary.next();
                    skipped = scannedValueInDictionary.next();
                    opd3 = scannedValueInDictionary.next();
                    skipped = scannedValueInDictionary.next();
                    operator.put(u,opr);
                    operand1.put(u,opd1);
                    operand2.put(u,opd2);
                    operand3.put(u,opd3);
                    tempDictionary.put(u,null);
                }
            }
        }
        
        String variableToBeEvaluated = inquiredVariable;
        int time = 0;
        int space = 0;
        double result = 0;
        double returnedValues[] = new double[2];
        
        evaluator evaluateEquation = new evaluator();
        
        boolean  timeDimension  = false;
        boolean  spaceDimension  = false;
        
        ArrayList<String> processedVals = new ArrayList<String>();
        timeDim timeDimStatus = new timeDim();
        timeDimension = timeDimStatus.timeDim(variableToBeEvaluated, dictionary,processedVals);
        
        processedVals.clear();
        spaceDim spaceDimStatus = new spaceDim();
        spaceDimension = spaceDimStatus.spaceDim(variableToBeEvaluated, dictionary, processedVals);
        
        if((timeDimension == true)&&(spaceDimension == true)) {
            while(true) {
                for(int i=0;i<5;i++) {
                    for(int j=0;j<5;j++) {
                        returnedValues = evaluateEquation.evaluator(variableToBeEvaluated, result, time, space, operand1, operator, operand2, operand3, cache);
                        
                        time = (int) returnedValues[1];
                        space = (int) returnedValues[2];
                        
                        if((returnedValues[0]%1)==0) {
                            System.out.print((int)returnedValues[0]+" ");
                        }
                        else {
                            System.out.print(new DecimalFormat("0.0").format(returnedValues[0])+" ");
                        }
                        
                        try {
                            Thread.sleep(1000);
                        }
                        catch(InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                        space++;
                    }
                    
                    System.out.print("...");
                    System.out.println("");
                    space=0;
                    time++;
                }
            }
        }
        else if(timeDimension == true) {
            while(true) {
                returnedValues = evaluateEquation.evaluator(variableToBeEvaluated, result, time, space, operand1, operator, operand2, operand3, cache);
                
                time = (int) returnedValues[1];
                space = (int) returnedValues[2];
                
                if((returnedValues[0]%1)==0) {
                    System.out.println((int)returnedValues[0]+" ");
                }
                else {
                    System.out.println(new DecimalFormat("0.0").format(returnedValues[0])+" ");
                }
                
                try {
                    Thread.sleep(1000);
                }
                catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                time++;
            }
        }
        else if(spaceDimension == true) {
            while(true) {
                returnedValues = evaluateEquation.evaluator(variableToBeEvaluated, result, time, space, operand1, operator, operand2, operand3, cache);
                
                time = (int) returnedValues[1];
                space = (int) returnedValues[2];
                
                if((returnedValues[0]%1)==0) {
                    System.out.print((int)returnedValues[0]+" ");
                }
                else {
                    System.out.print(new DecimalFormat("0.0").format(returnedValues[0])+" ");
                }
                
                try {
                    Thread.sleep(1000);
                }
                catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                space++;
            }
        }
        else {
            returnedValues = evaluateEquation.evaluator(variableToBeEvaluated, result, time, space, operand1, operator, operand2, operand3, cache);
            if((returnedValues[0]%1)==0) {
                System.out.println((int)returnedValues[0]+" ");
            }
            else {
                System.out.println(new DecimalFormat("0.0").format(returnedValues[0])+" ");
            }
        }
    }
}
