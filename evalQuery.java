//Find out if a variable can be evaluated (return true) otherwise (return false)

import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

class  evalQuery {
    public static boolean evalQuery(String evalVariable, Map<String,String> dictionary) {
        boolean varFound = false;
        String definedVariable = evalVariable;
        String firstElement = "";
        String secondElement = "";
        String thirdElement = "";
        String fourthElement = "";
        String fifthElement = "";
        String sixthElement = "";
        
        for(String i:dictionary.keySet()) {
            if(i.equals(definedVariable)) {
                Scanner varExprInDictionary = new Scanner(dictionary.get(i));
                firstElement = varExprInDictionary.next();
                if(firstElement.equalsIgnoreCase("int")) {
                    varFound = true;
                    break;
                }
                else if(firstElement.equalsIgnoreCase("first")||firstElement.equalsIgnoreCase("next")||firstElement.equalsIgnoreCase("init")||firstElement.equalsIgnoreCase("right")||firstElement.equalsIgnoreCase("succ")||firstElement.equalsIgnoreCase("all")||firstElement.equalsIgnoreCase("elements")||firstElement.equalsIgnoreCase("NOT")) {
                    secondElement = varExprInDictionary.next();
                    varFound = evalQuery(secondElement, dictionary);
                    if(varFound == false) {
                        break;
                    }
                }
                else if(firstElement.equalsIgnoreCase("if")) {
                    secondElement = varExprInDictionary.next();
                    varFound = evalQuery(secondElement, dictionary);
                    if(varFound == false) {
                        break;
                    }
                    thirdElement = varExprInDictionary.next();
                    fourthElement = varExprInDictionary.next();
                    varFound = evalQuery(fourthElement, dictionary);
                    if(varFound == false) {
                        break;
                    }
                    fifthElement = varExprInDictionary.next();
                    sixthElement = varExprInDictionary.next();
                    varFound = evalQuery(sixthElement, dictionary);
                    if(varFound == false) {
                        break;
                    }
                }
                else {
                    varFound = evalQuery(firstElement, dictionary);
                    if(varFound == false) {
                        break;
                    }
                    secondElement = varExprInDictionary.next();
                    thirdElement = varExprInDictionary.next();
                    if(thirdElement.equals("*")||thirdElement.equals("=")) {
                        fourthElement = varExprInDictionary.next();
                        varFound = evalQuery(fourthElement, dictionary);
                        if(varFound == false) {
                            break;
                        }
                    }
                    else {
                        varFound = evalQuery(thirdElement, dictionary);
                        if(varFound == false) {
                            break;
                        }
                    }
                }
            }
        }
        return varFound;
    }
}
