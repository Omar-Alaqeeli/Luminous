/*
 Luminous is an interactive interpreter for Lucid programming language. It uses terminal for inputs and outputs. The file luminous.java is the main file that starts subroutines calling sequence when running.
 */

import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class  luminous {
    public static void main(String[] args)
    throws FileNotFoundException {
        
        Map<String,String> dictionary = new LinkedHashMap<>();
                
        String completeLine = "";
        String command = "";
        String equation = "";
        String amendedEquation = "";
        int varId = 0;
        int varIdStatus = 0;
        String inquiredVariable = "";
        
        System.out.print("\033[31;1m>> \033[0m");

        Scanner contents = new Scanner(System.in);
        while(contents.hasNextLine()) {
            completeLine = contents.nextLine();
            Scanner line = new Scanner(completeLine);
            command = line.next();
            
            if(command.equalsIgnoreCase("var")) {
                while(line.hasNext()) {
                    equation = equation+" "+line.next();
                }
                amend amendEquation = new amend();
                amendedEquation = amendEquation.amend(equation);
                dismantler dismantleEquation = new dismantler();
                varIdStatus=dismantleEquation.dismantler(amendedEquation, dictionary,varId);
                varId=varIdStatus;
            }
            else if(command.equalsIgnoreCase("val")) {
                inquiredVariable = line.next();
                analyzer analyzeEquation = new analyzer();
                analyzeEquation.analyzer(inquiredVariable, dictionary);
                completeLine="";
            }
            else if(command.equalsIgnoreCase("defs")) {
                list listVals = new list();
                listVals.list(dictionary);
            }
            else if(command.equalsIgnoreCase("defined")) {
                boolean canBeEvaluated = false;
                command = line.next();
                evalQuery varEvalStatus = new evalQuery();
                canBeEvaluated = varEvalStatus.evalQuery(command, dictionary);
                System.out.println(canBeEvaluated);
            }
            else if(command.equalsIgnoreCase("constant")) {
                boolean constsOnly = false;
                command = line.next();
                constQuery constsOnlyStatus = new constQuery();
                constsOnly = constsOnlyStatus.constQuery(command, dictionary);
                System.out.println(constsOnly);
            }
            else if(command.equalsIgnoreCase("dims")) {
                boolean  timeDimension  = false;
                boolean  spaceDimension  = false;
                
                ArrayList<String> processedVals = new ArrayList<String>();
                command = line.next();
                timeDim timeDimStatus = new timeDim();
                timeDimension = timeDimStatus.timeDim(command, dictionary,processedVals);
                processedVals.clear();
                
                spaceDim spaceDimStatus = new spaceDim();
                spaceDimension = spaceDimStatus.spaceDim(command, dictionary, processedVals);
                processedVals.clear();
                
                if((timeDimension == true)&&(spaceDimension == true)) {
                    System.out.println("t , s");
                }
                else if(timeDimension == true) {
                    System.out.println("t");
                }
                else if(spaceDimension == true) {
                    System.out.println("s");
                }
                else {
                    System.out.println("None");
                }
            }
            else {
                continue;
            }
            completeLine="";
            equation="";
            System.out.print("\033[31;1m>> \033[0m");
        }
    }
}
