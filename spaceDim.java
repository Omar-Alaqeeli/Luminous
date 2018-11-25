//Find out if space dimension exists

import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

class  spaceDim {
    public static boolean spaceDim(String dimVariable, Map<String,String> dictionary, ArrayList<String> processedVals) {
        boolean spaceDimFound = false;
        String spaceDimVariable = dimVariable;
        
        String firstElement = "";
        String secondElement = "";
        String thirdElement = "";
        String fourthElement = "";
        String fifthElement = "";
        String sixthElement = "";
        
        if(!processedVals.contains(spaceDimVariable)) {
            processedVals.add(spaceDimVariable);
            
            for(String i:dictionary.keySet()) {
                if(i.equals(spaceDimVariable)) {
                    Scanner varExprInDictionary = new Scanner(dictionary.get(i));
                    firstElement = varExprInDictionary.next();
                    if(firstElement.equalsIgnoreCase("int")||firstElement.equalsIgnoreCase("flo")||firstElement.equalsIgnoreCase("init")) {
                        spaceDimFound = false;
                        break;
                    }
                    else if(firstElement.equalsIgnoreCase("right")||firstElement.equalsIgnoreCase("succ")||firstElement.equalsIgnoreCase("first")||firstElement.equalsIgnoreCase("next")||firstElement.equalsIgnoreCase("NOT")) {
                        secondElement = varExprInDictionary.next();
                        spaceDimFound = spaceDim(secondElement, dictionary, processedVals);
                        break;
                    }
                    else if(firstElement.equalsIgnoreCase("all")) {
                        secondElement = varExprInDictionary.next();
                        timeDim timeDimStatus = new timeDim();
                        spaceDimFound = timeDimStatus.timeDim(secondElement, dictionary, processedVals);
                        if(spaceDimFound == true) {
                            break;
                        }
                        processedVals.remove(secondElement);
                        spaceDimFound = spaceDim(secondElement, dictionary, processedVals);
                        if(spaceDimFound == true) {
                            break;
                        }
                    }
                    else if(firstElement.equalsIgnoreCase("elements")) {
                        spaceDimFound = false;
                    }
                    else if(firstElement.equalsIgnoreCase("if")) {
                        secondElement = varExprInDictionary.next();
                        spaceDimFound = spaceDim(secondElement, dictionary, processedVals);
                        if(spaceDimFound == true) {
                            break;
                        }
                        else {
                            thirdElement = varExprInDictionary.next();
                            fourthElement = varExprInDictionary.next();
                            spaceDimFound = spaceDim(fourthElement, dictionary, processedVals);
                            if(spaceDimFound == true) {
                                break;
                            }
                            else {
                                firstElement = varExprInDictionary.next();
                                sixthElement = varExprInDictionary.next();
                                spaceDimFound = spaceDim(sixthElement, dictionary, processedVals);
                                break;
                            }
                        }
                    }
                    else {
                        secondElement = varExprInDictionary.next();
                        if(secondElement.equalsIgnoreCase("sby")||secondElement.equalsIgnoreCase("wherever")) {
                            spaceDimFound = true;
                            break;
                        }
                        else {
                            thirdElement = varExprInDictionary.next();
                            if(thirdElement.equals("*")||thirdElement.equals("=")) {
                                spaceDimFound = spaceDim(firstElement, dictionary, processedVals);
                                if(spaceDimFound == true) {
                                    break;
                                }
                                else {
                                    fourthElement = varExprInDictionary.next();
                                    spaceDimFound = spaceDim(fourthElement, dictionary, processedVals);
                                }
                            }
                            else {
                                spaceDimFound = spaceDim(firstElement, dictionary, processedVals);
                                if(spaceDimFound == true) {
                                    break;
                                }
                                else {
                                    spaceDimFound = spaceDim(thirdElement, dictionary, processedVals);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return spaceDimFound;
    }
}
