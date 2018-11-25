//Find out if time dimension exists

import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

class  timeDim {
    public static boolean timeDim(String dimVariable, Map<String,String> dictionary, ArrayList<String> processedVals) {
        boolean timeDimFound = false;
        String timeDimVariable = dimVariable;
        
        String firstElement = "";
        String secondElement = "";
        String thirdElement = "";
        String fourthElement = "";
        String fifthElement = "";
        String sixthElement = "";
        
        if(!processedVals.contains(timeDimVariable)) {
            processedVals.add(timeDimVariable);
            
            for(String i:dictionary.keySet()) {
                if(i.equals(timeDimVariable)) {
                    Scanner varExprInDictionary = new Scanner(dictionary.get(i));
                    firstElement = varExprInDictionary.next();
                    if(firstElement.equalsIgnoreCase("int")||firstElement.equalsIgnoreCase("flo")||firstElement.equalsIgnoreCase("first")) {
                        timeDimFound = false;
                        break;
                    }
                    else if(firstElement.equalsIgnoreCase("next")||firstElement.equalsIgnoreCase("init")||firstElement.equalsIgnoreCase("right")||firstElement.equalsIgnoreCase("succ")||firstElement.equalsIgnoreCase("NOT")) {
                        secondElement = varExprInDictionary.next();
                        timeDimFound = timeDim(secondElement, dictionary, processedVals);
                        break;
                    }
                    else if(firstElement.equalsIgnoreCase("all")) {
                        timeDimFound = false;
                    }
                    else if(firstElement.equalsIgnoreCase("elements")) {
                        secondElement = varExprInDictionary.next();
                        spaceDim spaceDimStatus = new spaceDim();
                        timeDimFound = spaceDimStatus.spaceDim(secondElement, dictionary, processedVals);
                        if(timeDimFound == true) {
                            break;
                        }
                        processedVals.remove(secondElement);
                        timeDimFound = timeDim(secondElement, dictionary, processedVals);
                        if(timeDimFound == true) {
                            break;
                        }
                    }
                    else if(firstElement.equalsIgnoreCase("if")) {
                        secondElement = varExprInDictionary.next();
                        timeDimFound = timeDim(secondElement, dictionary, processedVals);
                        if(timeDimFound == true) {
                            break;
                        }
                        else {
                            thirdElement = varExprInDictionary.next();
                            fourthElement = varExprInDictionary.next();
                            timeDimFound = timeDim(fourthElement, dictionary, processedVals);
                            if(timeDimFound == true) {
                                break;
                            }
                            else {
                                fifthElement = varExprInDictionary.next();
                                sixthElement = varExprInDictionary.next();
                                timeDimFound = timeDim(sixthElement, dictionary, processedVals);
                                break;
                            }
                        }
                    }
                    else {
                        secondElement = varExprInDictionary.next();
                        if(secondElement.equalsIgnoreCase("asa")) {
                            timeDimFound = false;
                            break;
                        }
                        else if(secondElement.equalsIgnoreCase("fby")||secondElement.equalsIgnoreCase("upon")||secondElement.equalsIgnoreCase("whenever")) {
                            timeDimFound = true;
                            break;
                        }
                        else {
                            thirdElement = varExprInDictionary.next();
                            if(thirdElement.equals("*")||thirdElement.equals("=")) {
                                timeDimFound = timeDim(firstElement, dictionary, processedVals);
                                if(timeDimFound == true) {
                                    break;
                                }
                                else {
                                    fourthElement = varExprInDictionary.next();
                                    timeDimFound = timeDim(fourthElement, dictionary, processedVals);
                                }
                            }
                            else {
                                timeDimFound = timeDim(firstElement, dictionary, processedVals);
                                if(timeDimFound == true) {
                                    break;
                                }
                                else {
                                    timeDimFound = timeDim(thirdElement, dictionary, processedVals);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return timeDimFound;
    }
}
