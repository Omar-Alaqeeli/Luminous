//Evaluate variable inquired by user

import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

class evaluator {
    public static double[] evaluator(String variableToBeEvaluated, double result, int time, int space, Map<String,String> operand1, Map<String,String> operator, Map<String,String> operand2, Map<String,String> operand3, Map<String,Double> cache) {
        String testInquiredVariable = "";
        boolean variableFoundInCache = false;
        String testOperator = "";
        String currentInquiredVariable = "";
        double firstOpd = 0;
        double secondOpd = 0;
        double thirdOpd = 0;
        double returnedValues[] = new double[2];
        double returnedValueOfOpd1[] = new double[2];
        double returnedValueOfOpd2[] = new double[2];
        double returnedValueOfOpd3[] = new double[2];
        double inquiredVariableValue = 0;
        String completeVariableToAddInCache = "";
        String variableInProcess = "";
        String colon1 = "";
        String colon2 = "";
        double valueOfVariableInCache = 0;
        double numberOfelementsInCache = 0;
        
        testInquiredVariable = variableToBeEvaluated+" : "+Integer.toString(time)+" : "+Integer.toString(space);
        
        for(String u:cache.keySet()) {
            if(u.equals(testInquiredVariable)) {
                
                Scanner processVariable = new Scanner(u);
                variableInProcess = processVariable.next();
                colon1 = processVariable.next();
                time = Integer.parseInt(processVariable.next());
                colon2 = processVariable.next();
                space = Integer.parseInt(processVariable.next());
                currentInquiredVariable = variableInProcess;
                valueOfVariableInCache = cache.get(u);
                variableFoundInCache = true;
                
                result = valueOfVariableInCache;
                completeVariableToAddInCache = currentInquiredVariable+" : "+Integer.toString(time)+" : "+Integer.toString(space);
                cache.put(completeVariableToAddInCache, result);
                
            }
        }
        if(variableFoundInCache == false) {
            currentInquiredVariable = variableToBeEvaluated;
            testOperator = operator.get(currentInquiredVariable);
            
            switch (testOperator) {
                case "int":
                    firstOpd = Double.parseDouble(operand1.get(currentInquiredVariable));
                    completeVariableToAddInCache = currentInquiredVariable+" : "+Integer.toString(time)+" : "+Integer.toString(space);
                    result = firstOpd;
                    cache.put(completeVariableToAddInCache, result);
                    break;
                
                case "flo":
                    firstOpd = Double.parseDouble(operand1.get(currentInquiredVariable));
                    completeVariableToAddInCache = currentInquiredVariable+" : "+Integer.toString(time)+" : "+Integer.toString(space);
                    result = firstOpd;
                    cache.put(completeVariableToAddInCache, result);
                    break;
                
                case "+":
                    returnedValueOfOpd1 = evaluator(operand1.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    firstOpd = returnedValueOfOpd1[0];
                    returnedValueOfOpd2 = evaluator(operand2.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    secondOpd = returnedValueOfOpd2[0];
                    result = firstOpd + secondOpd;
                    completeVariableToAddInCache = currentInquiredVariable+" : "+Integer.toString(time)+" : "+Integer.toString(space);
                    cache.put(completeVariableToAddInCache, result);
                    break;
                    
                case "-":
                    returnedValueOfOpd1 = evaluator(operand1.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    firstOpd = returnedValueOfOpd1[0];
                    returnedValueOfOpd2 = evaluator(operand2.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    secondOpd = returnedValueOfOpd2[0];
                    result = firstOpd - secondOpd;
                    completeVariableToAddInCache = currentInquiredVariable+" : "+Integer.toString(time)+" : "+Integer.toString(space);
                    cache.put(completeVariableToAddInCache, result);
                    break;
                    
                case "*":
                    returnedValueOfOpd1 = evaluator(operand1.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    firstOpd = returnedValueOfOpd1[0];
                    returnedValueOfOpd2 = evaluator(operand2.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    secondOpd = returnedValueOfOpd2[0];
                    result = firstOpd * secondOpd;
                    completeVariableToAddInCache = currentInquiredVariable+" : "+Integer.toString(time)+" : "+Integer.toString(space);
                    cache.put(completeVariableToAddInCache, result);
                    break;
                    
                case "/":
                    returnedValueOfOpd1 = evaluator(operand1.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    firstOpd = returnedValueOfOpd1[0];
                    returnedValueOfOpd2 = evaluator(operand2.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    secondOpd = returnedValueOfOpd2[0];
                    result = firstOpd / secondOpd;
                    completeVariableToAddInCache = currentInquiredVariable+" : "+Integer.toString(time)+" : "+Integer.toString(space);
                    cache.put(completeVariableToAddInCache, result);
                    break;
                
                case "%":
                    returnedValueOfOpd1 = evaluator(operand1.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    firstOpd = returnedValueOfOpd1[0];
                    returnedValueOfOpd2 = evaluator(operand2.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    secondOpd = returnedValueOfOpd2[0];
                    result = firstOpd % secondOpd;
                    completeVariableToAddInCache = currentInquiredVariable+" : "+Integer.toString(time)+" : "+Integer.toString(space);
                    cache.put(completeVariableToAddInCache, result);
                    break;
                    
                case "**":
                    returnedValueOfOpd1 = evaluator(operand1.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    firstOpd = returnedValueOfOpd1[0];
                    returnedValueOfOpd2 = evaluator(operand2.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    secondOpd = returnedValueOfOpd2[0];
                    result = Math.pow(firstOpd, secondOpd);
                    completeVariableToAddInCache = currentInquiredVariable+" : "+Integer.toString(time)+" : "+Integer.toString(space);
                    cache.put(completeVariableToAddInCache, result);
                    break;
                    
                case "first":
                    returnedValueOfOpd1 = evaluator(operand1.get(currentInquiredVariable), result, time=0, space, operand1, operator, operand2, operand3, cache);
                    firstOpd = returnedValueOfOpd1[0];
                    result = firstOpd;
                    completeVariableToAddInCache = currentInquiredVariable+" : "+Integer.toString(time)+" : "+Integer.toString(space);
                    cache.put(completeVariableToAddInCache, result);
                    break;
                    
                case "init":
                    returnedValueOfOpd1 = evaluator(operand1.get(currentInquiredVariable), result, time, space=0, operand1, operator, operand2, operand3, cache);
                    firstOpd = returnedValueOfOpd1[0];
                    result = firstOpd;
                    completeVariableToAddInCache = currentInquiredVariable+" : "+Integer.toString(time)+" : "+Integer.toString(space);
                    cache.put(completeVariableToAddInCache, result);
                    break;
                    
                case "next":
                    returnedValueOfOpd1 = evaluator(operand1.get(currentInquiredVariable), result, time+1, space, operand1, operator, operand2, operand3, cache);
                    firstOpd = returnedValueOfOpd1[0];
                    result = firstOpd;
                    completeVariableToAddInCache = currentInquiredVariable+" : "+Integer.toString(time)+" : "+Integer.toString(space);
                    cache.put(completeVariableToAddInCache, result);
                    break;
                    
                case "right":
                    returnedValueOfOpd1 = evaluator(operand1.get(currentInquiredVariable), result, time, space+1, operand1, operator, operand2, operand3, cache);
                    firstOpd = returnedValueOfOpd1[0];
                    result = firstOpd;
                    completeVariableToAddInCache = currentInquiredVariable+" : "+Integer.toString(time)+" : "+Integer.toString(space);
                    cache.put(completeVariableToAddInCache, result);
                    break;
                    
                case "succ":
                    returnedValueOfOpd1 = evaluator(operand1.get(currentInquiredVariable), result, time, space+1, operand1, operator, operand2, operand3, cache);
                    firstOpd = returnedValueOfOpd1[0];
                    result = firstOpd;
                    completeVariableToAddInCache = currentInquiredVariable+" : "+Integer.toString(time)+" : "+Integer.toString(space);
                    cache.put(completeVariableToAddInCache, result);
                    break;

                case "all":
                    returnedValueOfOpd1 = evaluator(operand1.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    firstOpd = returnedValueOfOpd1[0];
                    result = firstOpd;
                    completeVariableToAddInCache = currentInquiredVariable+" : "+Integer.toString(time)+" : "+Integer.toString(space);
                    cache.put(completeVariableToAddInCache, result);
                    time++;
                    break;
                    
                case "elements":
                    returnedValueOfOpd1 = evaluator(operand1.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    firstOpd = returnedValueOfOpd1[0];
                    result = firstOpd;
                    completeVariableToAddInCache = currentInquiredVariable+" : "+Integer.toString(time)+" : "+Integer.toString(space);
                    cache.put(completeVariableToAddInCache, result);
                    space++;
                    break;

                case "fby":
                    if(time==0) {
                        returnedValueOfOpd1 = evaluator(operand1.get(currentInquiredVariable), result, time=0, space, operand1, operator, operand2, operand3, cache);
                        firstOpd = returnedValueOfOpd1[0];
                        result = firstOpd;
                        completeVariableToAddInCache = currentInquiredVariable+" : "+Integer.toString(time)+" : "+Integer.toString(space);
                        cache.put(completeVariableToAddInCache, result);
                    }
                    else {
                        returnedValueOfOpd2 = evaluator(operand2.get(currentInquiredVariable), result, time-1, space, operand1, operator, operand2, operand3, cache);
                        secondOpd = returnedValueOfOpd2[0];
                        result = secondOpd;
                        completeVariableToAddInCache = currentInquiredVariable+" : "+Integer.toString(time)+" : "+Integer.toString(space);
                        cache.put(completeVariableToAddInCache, result);
                    }
                    break;
    
                case "sby":
                    if(space==0) {
                        returnedValueOfOpd1 = evaluator(operand1.get(currentInquiredVariable), result, time, space=0, operand1, operator, operand2, operand3, cache);
                        firstOpd = returnedValueOfOpd1[0];
                        result = firstOpd;
                        completeVariableToAddInCache = currentInquiredVariable+" : "+Integer.toString(time)+" : "+Integer.toString(space);
                        cache.put(completeVariableToAddInCache, result);
                    }
                    else {
                        returnedValueOfOpd2 = evaluator(operand2.get(currentInquiredVariable), result, time, space-1, operand1, operator, operand2, operand3, cache);
                        secondOpd = returnedValueOfOpd2[0];
                        result = secondOpd;
                        completeVariableToAddInCache = currentInquiredVariable+" : "+Integer.toString(time)+" : "+Integer.toString(space);
                        cache.put(completeVariableToAddInCache, result);
                    }
                    break;
                    
                case ">":
                    returnedValueOfOpd1 = evaluator(operand1.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    firstOpd = returnedValueOfOpd1[0];
                    returnedValueOfOpd2 = evaluator(operand2.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    secondOpd = returnedValueOfOpd2[0];
                    if(firstOpd > secondOpd) {
                        result = 1;
                    }
                    else {
                        result = 0;
                    }
                    completeVariableToAddInCache = currentInquiredVariable+" : "+Integer.toString(time)+" : "+Integer.toString(space);
                    cache.put(completeVariableToAddInCache, result);
                    break;
                    
                case "<":
                    returnedValueOfOpd1 = evaluator(operand1.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    firstOpd = returnedValueOfOpd1[0];
                    returnedValueOfOpd2 = evaluator(operand2.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    secondOpd = returnedValueOfOpd2[0];
                    if(firstOpd < secondOpd) {
                        result = 1;
                    }
                    else {
                        result = 0;
                    }
                    completeVariableToAddInCache = currentInquiredVariable+" : "+Integer.toString(time)+" : "+Integer.toString(space);
                    cache.put(completeVariableToAddInCache, result);
                    break;
                    
                case ">=":
                    returnedValueOfOpd1 = evaluator(operand1.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    firstOpd = returnedValueOfOpd1[0];
                    returnedValueOfOpd2 = evaluator(operand2.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    secondOpd = returnedValueOfOpd2[0];
                    if(firstOpd >= secondOpd) {
                        result = 1;
                    }
                    else {
                        result = 0;
                    }
                    completeVariableToAddInCache = currentInquiredVariable+" : "+Integer.toString(time)+" : "+Integer.toString(space);
                    cache.put(completeVariableToAddInCache, result);
                    break;
                    
                case "<=":
                    returnedValueOfOpd1 = evaluator(operand1.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    firstOpd = returnedValueOfOpd1[0];
                    returnedValueOfOpd2 = evaluator(operand2.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    secondOpd = returnedValueOfOpd2[0];
                    if(firstOpd <= secondOpd) {
                        result = 1;
                    }
                    else {
                        result = 0;
                    }
                    completeVariableToAddInCache = currentInquiredVariable+" : "+Integer.toString(time)+" : "+Integer.toString(space);
                    cache.put(completeVariableToAddInCache, result);
                    break;
                    
                case "==":
                    returnedValueOfOpd1 = evaluator(operand1.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    firstOpd = returnedValueOfOpd1[0];
                    returnedValueOfOpd2 = evaluator(operand2.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    secondOpd = returnedValueOfOpd2[0];
                    if(firstOpd == secondOpd) {
                        result = 1;
                    }
                    else {
                        result = 0;
                    }
                    completeVariableToAddInCache = currentInquiredVariable+" : "+Integer.toString(time)+" : "+Integer.toString(space);
                    cache.put(completeVariableToAddInCache, result);
                    break;
                    
                case "!=":
                    returnedValueOfOpd1 = evaluator(operand1.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    firstOpd = returnedValueOfOpd1[0];
                    returnedValueOfOpd2 = evaluator(operand2.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    secondOpd = returnedValueOfOpd2[0];
                    if(firstOpd != secondOpd) {
                        result = 1;
                    }
                    else {
                        result = 0;
                    }
                    completeVariableToAddInCache = currentInquiredVariable+" : "+Integer.toString(time)+" : "+Integer.toString(space);
                    cache.put(completeVariableToAddInCache, result);
                    break;
                    
                case "eq":
                    returnedValueOfOpd1 = evaluator(operand1.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    firstOpd = returnedValueOfOpd1[0];
                    returnedValueOfOpd2 = evaluator(operand2.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    secondOpd = returnedValueOfOpd2[0];
                    if(firstOpd == secondOpd) {
                        result = 1;
                    }
                    else {
                        result = 0;
                    }
                    completeVariableToAddInCache = currentInquiredVariable+" : "+Integer.toString(time)+" : "+Integer.toString(space);
                    cache.put(completeVariableToAddInCache, result);
                    break;
                    
                case "ne":
                    returnedValueOfOpd1 = evaluator(operand1.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    firstOpd = returnedValueOfOpd1[0];
                    returnedValueOfOpd2 = evaluator(operand2.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    secondOpd = returnedValueOfOpd2[0];
                    if(firstOpd != secondOpd) {
                        result = 1;
                    }
                    else {
                        result = 0;
                    }
                    completeVariableToAddInCache = currentInquiredVariable+" : "+Integer.toString(time)+" : "+Integer.toString(space);
                    cache.put(completeVariableToAddInCache, result);
                    break;
                    
                case "and":
                    returnedValueOfOpd1 = evaluator(operand1.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    firstOpd = returnedValueOfOpd1[0];
                    if(firstOpd == 0) {
                        result = 0;
                    }
                    else {
                        returnedValueOfOpd2 = evaluator(operand2.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                        secondOpd = returnedValueOfOpd2[0];
                        result = secondOpd;
                    }
                    completeVariableToAddInCache = currentInquiredVariable+" : "+Integer.toString(time)+" : "+Integer.toString(space);
                    cache.put(completeVariableToAddInCache, result);
                    break;
                    
                case "or":
                    returnedValueOfOpd1 = evaluator(operand1.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    firstOpd = returnedValueOfOpd1[0];
                    if(firstOpd == 1) {
                        result = 1;
                    }
                    else {
                        returnedValueOfOpd2 = evaluator(operand2.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                        secondOpd = returnedValueOfOpd2[0];
                        result = secondOpd;
                    }
                    completeVariableToAddInCache = currentInquiredVariable+" : "+Integer.toString(time)+" : "+Integer.toString(space);
                    cache.put(completeVariableToAddInCache, result);
                    break;
                    
                case "not":
                    returnedValueOfOpd1 = evaluator(operand1.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    firstOpd = returnedValueOfOpd1[0];
                    if(firstOpd == 1) {
                        result = 0;
                    }
                    else {
                        result = 1;
                    }
                    completeVariableToAddInCache = currentInquiredVariable+" : "+Integer.toString(time)+" : "+Integer.toString(space);
                    cache.put(completeVariableToAddInCache, result);
                    break;
                    
                case "if":
                    returnedValueOfOpd1 = evaluator(operand1.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    firstOpd = returnedValueOfOpd1[0];
                    if(firstOpd == 1) {
                        returnedValueOfOpd2 = evaluator(operand2.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                        secondOpd = returnedValueOfOpd2[0];
                        result = secondOpd;
                    }
                    else {
                        returnedValueOfOpd3 = evaluator(operand3.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                        thirdOpd = returnedValueOfOpd3[0];
                        result = thirdOpd;
                    }
                    completeVariableToAddInCache = currentInquiredVariable+" : "+Integer.toString(time)+" : "+Integer.toString(space);
                    cache.put(completeVariableToAddInCache, result);
                    break;
                    
                case "asa":
                    for(String u:cache.keySet()) {
                        numberOfelementsInCache++;
                    }
                    time = 0;
                    while(numberOfelementsInCache>0) {
                        returnedValueOfOpd2 = evaluator(operand2.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                        secondOpd = returnedValueOfOpd2[0];
                        if(secondOpd == 1) {
                            break;
                        }
                        time = time + 1;
                        numberOfelementsInCache--;
                    }
                    returnedValueOfOpd1 = evaluator(operand1.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    firstOpd = returnedValueOfOpd1[0];
                    result = firstOpd;
                    completeVariableToAddInCache = currentInquiredVariable+" : "+Integer.toString(time)+" : "+Integer.toString(space);
                    cache.put(completeVariableToAddInCache, result);
                    break;
                    
                case "upon":
                    for(String u:cache.keySet()) {
                        numberOfelementsInCache++;
                    }
                    time = 0;
                    while(numberOfelementsInCache>0) {
                        returnedValueOfOpd2 = evaluator(operand2.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                        secondOpd = returnedValueOfOpd2[0];
                        if(secondOpd == 1) {
                            break;
                        }
                        time = time + 1;
                        numberOfelementsInCache--;
                    }
                    returnedValueOfOpd1 = evaluator(operand1.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    firstOpd = returnedValueOfOpd1[0];
                    result = firstOpd;
                    completeVariableToAddInCache = currentInquiredVariable+" : "+Integer.toString(time)+" : "+Integer.toString(space);
                    cache.put(completeVariableToAddInCache, result);
                    break;
                    
                case "whenever":
                    for(String u:cache.keySet()) {
                        numberOfelementsInCache++;
                    }
                    while(numberOfelementsInCache>0) {
                        returnedValueOfOpd2 = evaluator(operand2.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                        secondOpd = returnedValueOfOpd2[0];
                        if(secondOpd == 1) {
                            break;
                        }
                        time = time + 1;
                        numberOfelementsInCache--;
                    }
                    returnedValueOfOpd1 = evaluator(operand1.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    firstOpd = returnedValueOfOpd1[0];
                    result = firstOpd;
                    completeVariableToAddInCache = currentInquiredVariable+" : "+Integer.toString(time)+" : "+Integer.toString(space);
                    cache.put(completeVariableToAddInCache, result);
                    break;
                    
                case "wherever":
                    for(String u:cache.keySet()) {
                        numberOfelementsInCache++;
                    }
                    while(numberOfelementsInCache>0) {
                        returnedValueOfOpd2 = evaluator(operand2.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                        secondOpd = returnedValueOfOpd2[0];
                        if(secondOpd == 1) {
                            break;
                        }
                        space = space + 1;
                        numberOfelementsInCache--;
                    }
                    returnedValueOfOpd1 = evaluator(operand1.get(currentInquiredVariable), result, time, space, operand1, operator, operand2, operand3, cache);
                    firstOpd = returnedValueOfOpd1[0];
                    result = firstOpd;
                    completeVariableToAddInCache = currentInquiredVariable+" : "+Integer.toString(time)+" : "+Integer.toString(space);
                    cache.put(completeVariableToAddInCache, result);
                    break;
            }
        }
        variableFoundInCache = false;
        double returnables[] = new double[3];
        returnables[0] = result;
        returnables[1] = (double) time;
        returnables[2] = (double) space;
        
        return returnables;
    }
}
