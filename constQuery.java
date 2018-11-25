//return true if there is no temporal operators (exception: first) involves in an expression that is linked directly or indirectly to the variable being evaluated, otherwise return false.

import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

class  constQuery {
    public static boolean constQuery(String constVariable, Map<String,String> dictionary) {
        boolean aConstant = false;
        String allConstVariable = constVariable;
        String firstElement = "";
        String secondElement = "";
        String thirdElement = "";
        String fourthElement = "";
        String fifthElement = "";
        String sixthElement = "";
        
        for(String i:dictionary.keySet()) {
            if(i.equals(allConstVariable)) {
                Scanner varExprInDictionary = new Scanner(dictionary.get(i));
                firstElement = varExprInDictionary.next();
                if(firstElement.equalsIgnoreCase("int")||firstElement.equalsIgnoreCase("first")||firstElement.equalsIgnoreCase("elements")||firstElement.equalsIgnoreCase("NOT")) {
                    aConstant = true;
                    break;
                }
                else if(firstElement.equalsIgnoreCase("next")||firstElement.equalsIgnoreCase("init")||firstElement.equalsIgnoreCase("right")||firstElement.equalsIgnoreCase("succ")||firstElement.equalsIgnoreCase("all")) {
                    secondElement = varExprInDictionary.next();
                    aConstant = constQuery(secondElement, dictionary);
                }
                else if(firstElement.equalsIgnoreCase("if")) {
                    secondElement = varExprInDictionary.next();
                    aConstant = constQuery(secondElement, dictionary);
                    if(aConstant == false) {
                        break;
                    }
                    else {
                        thirdElement = varExprInDictionary.next();
                        fourthElement = varExprInDictionary.next();
                        aConstant = constQuery(fourthElement, dictionary);
                        if(aConstant == false) {
                            break;
                        }
                        else {
                            fifthElement = varExprInDictionary.next();
                            sixthElement = varExprInDictionary.next();
                            aConstant = constQuery(sixthElement, dictionary);
                        }
                    }
                }
                else {
                    secondElement = varExprInDictionary.next();
                    if(secondElement.equalsIgnoreCase("asa")) {
                        aConstant = true;
                        break;
                    }
                    else if(secondElement.equalsIgnoreCase("fby")||secondElement.equalsIgnoreCase("upon")||secondElement.equalsIgnoreCase("whenever")) {
                        aConstant = false;
                        break;
                    }
                    else {
                        thirdElement = varExprInDictionary.next();
                        if(thirdElement.equalsIgnoreCase("*")||thirdElement.equalsIgnoreCase("=")) {
                            aConstant = constQuery(firstElement, dictionary);
                            if(aConstant == false) {
                                break;
                            }
                            else {
                                fourthElement = varExprInDictionary.next();
                                aConstant = constQuery(fourthElement, dictionary);
                            }
                        }
                        else {
                            aConstant = constQuery(firstElement, dictionary);
                            if(aConstant == false) {
                                break;
                            }
                            else {
                                aConstant = constQuery(thirdElement, dictionary);
                            }
                        }
                    }
                }
            }
        }
        return aConstant;
    }
}
