//Dismantle expression and add it to dictionary

import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

class dismantler {
    public static int dismantler(String console, Map<String,String> dictionary, int varId1)
    throws FileNotFoundException {
        Scanner sentence = new Scanner(console);
        
        ArrayList<String> equation= new ArrayList<String>();
        ArrayList<String> conditionEquation= new ArrayList<String>();
        String token=" ";
        int tokenCount=0;
        String temp="";
        int sum=0;
        int quit=0;
        int varId=varId1;
        String newKey="";
        int remainToken=0;
        int startParenth=0;
        int endParenth=0;
        int numAreRemain=0;
        int lastAtomicEq=0;
        int differ=0;
        int conditionStart=0;
        int conditionEnd=0;
        String conditionToken="";
        int conditionTokenCount=0;
        int endConditionParenth=0;
        int startConditionParenth=0;
        int simplestEq=0;
        
        while(!token.equals(";")) {
            token=sentence.next();
            equation.add(token);
            tokenCount++;
        }
        if(!equation.get(1).equals("=")) {
            System.out.println("Syntax Error: 'var' is used for assigning values to variables\n");
        }
        while(equation.contains("(")) {
            int q=-1;
            for(String u:equation) {
                q++;
                if(u.equals("(")) {
                    startParenth=q;
                }
            }
            for(int i=startParenth;i<tokenCount;i++) {
                if(equation.get(i).equals(")")) {
                    endParenth=i;
                    break;
                }
            }
            for(int i=startParenth+1;i<endParenth;i++) {
                conditionStart=0;
                conditionEnd=0;
                for(int j=startParenth+1;j<endParenth;j++) {
                    if(equation.get(j).equalsIgnoreCase("if")) {
                        conditionStart=j;
                    }
                }
                for(int j=conditionStart+1;j<endParenth;j++) {
                    if(equation.get(j).equalsIgnoreCase("fi")) {
                        conditionEnd=j;
                        break;
                    }
                }
                if(conditionStart!=0) {
                    for(int j=conditionStart;j<=conditionEnd;j++) {
                        if(equation.get(j).matches("[-+]?\\d*\\.?\\d+")) {
                            if(equation.get(j).matches("([-+]?[0-9]*)\\.([0-9]*)")) {
                                temp="flo "+equation.get(j);
                                newKey="V"+Integer.toString(varId);
                                dictionary.put(newKey,temp);
                                equation.set(j,newKey);
                                j--;
                                temp="";
                                varId++;
                            }
                            else {
                                temp="int "+equation.get(j);
                                newKey="V"+Integer.toString(varId);
                                dictionary.put(newKey,temp);
                                equation.set(j,newKey);
                                j--;
                                temp="";
                                varId++;
                            }
                        }
                    }
                    for(int j=conditionEnd-1;j>conditionStart;j--) {
                        if(equation.get(j).equalsIgnoreCase("init")||equation.get(j).equalsIgnoreCase("right")||equation.get(j).equalsIgnoreCase("succ")||equation.get(j).equalsIgnoreCase("all")) {
                            temp=equation.get(j)+" "+equation.get(j+1);
                            newKey="V"+Integer.toString(varId);
                            dictionary.put(newKey,temp);
                            equation.set(j,newKey);
                            equation.remove(j+1);
                            conditionEnd--;
                            temp="";
                            varId++;
                        }
                    }
                    for(int j=conditionEnd-1;j>conditionStart;j--) {
                        if(equation.get(j).equalsIgnoreCase("first")||equation.get(j).equalsIgnoreCase("next")||equation.get(j).equalsIgnoreCase("elements")) {
                            temp=equation.get(j)+" "+equation.get(j+1);
                            newKey="V"+Integer.toString(varId);
                            dictionary.put(newKey,temp);
                            equation.set(j,newKey);
                            equation.remove(j+1);
                            conditionEnd--;
                            temp="";
                            varId++;
                        }
                    }
                    for(int j=conditionStart;j<=conditionEnd;j++) {
                        if((equation.get(j).equals("*"))&&(equation.get(j+1).equals("*"))) {
                            temp=equation.get(j-1)+" "+equation.get(j)+" "+equation.get(j+1)+" "+equation.get(j+2);
                            newKey="V"+Integer.toString(varId);
                            dictionary.put(newKey,temp);
                            equation.set(j-1,newKey);
                            equation.remove(j);
                            equation.remove(j);
                            equation.remove(j);
                            j--;
                            conditionEnd--;
                            conditionEnd--;
                            conditionEnd--;
                            temp="";
                            varId++;
                        }
                    }
                    for(int j=conditionStart;j<=conditionEnd;j++) {
                        if((equation.get(j).equals("*"))||(equation.get(j).equals("/"))||(equation.get(j).equals("%"))&&(!equation.get(j+1).equals("*"))) {
                            temp=equation.get(j-1)+" "+equation.get(j)+" "+equation.get(j+1);
                            newKey="V"+Integer.toString(varId);
                            dictionary.put(newKey,temp);
                            equation.set(j-1,newKey);
                            equation.remove(j);
                            equation.remove(j);
                            j--;
                            conditionEnd--;
                            conditionEnd--;
                            temp="";
                            varId++;
                        }
                    }
                    for(int j=conditionStart;j<=conditionEnd;j++) {
                        if((equation.get(j).equals("+"))||(equation.get(j).equals("-"))) {
                            temp=equation.get(j-1)+" "+equation.get(j)+" "+equation.get(j+1);
                            newKey="V"+Integer.toString(varId);
                            dictionary.put(newKey,temp);
                            equation.set(j-1,newKey);
                            equation.remove(j);
                            equation.remove(j);
                            j--;
                            conditionEnd--;
                            conditionEnd--;
                            temp="";
                            varId++;
                        }
                    }
                    for(int j=conditionStart;j<=conditionEnd;j++) {
                        if((equation.get(j).equals("<"))||(equation.get(j).equals(">"))||(equation.get(j).equalsIgnoreCase("eq"))||(equation.get(j).equalsIgnoreCase("ne"))) {
                            if(equation.get(j+1).equals("=")) {
                                temp=equation.get(j-1)+" "+equation.get(j)+" "+equation.get(j+1)+" "+equation.get(j+2);
                                newKey="V"+Integer.toString(varId);
                                dictionary.put(newKey,temp);
                                equation.set(j-1,newKey);
                                equation.remove(j);
                                equation.remove(j);
                                equation.remove(j);
                                j--;
                                conditionEnd--;
                                conditionEnd--;
                                conditionEnd--;
                                temp="";
                                varId++;
                            }
                            else {
                                temp=equation.get(j-1)+" "+equation.get(j)+" "+equation.get(j+1);
                                newKey="V"+Integer.toString(varId);
                                dictionary.put(newKey,temp);
                                equation.set(j-1,newKey);
                                equation.remove(j);
                                equation.remove(j);
                                j--;
                                conditionEnd--;
                                conditionEnd--;
                                temp="";
                                varId++;
                            }
                        }
                    }
                    for(int j=conditionStart;j<=conditionEnd;j++) {
                        if((equation.get(j).equals("="))||(equation.get(j).equals("!"))) {
                            if(equation.get(j+1).equals("=")) {
                                temp=equation.get(j-1)+" "+equation.get(j)+" "+equation.get(j+1)+" "+equation.get(j+2);
                                newKey="V"+Integer.toString(varId);
                                dictionary.put(newKey,temp);
                                equation.set(j-1,newKey);
                                equation.remove(j);
                                equation.remove(j);
                                equation.remove(j);
                                j--;
                                conditionEnd--;
                                conditionEnd--;
                                conditionEnd--;
                                temp="";
                                varId++;
                            }
                        }
                    }
                    for(int j=conditionEnd-1;j>conditionStart;j--) {
                        if(equation.get(j).equalsIgnoreCase("NOT")) {
                            temp=equation.get(j)+" "+equation.get(j+1);
                            newKey="V"+Integer.toString(varId);
                            dictionary.put(newKey,temp);
                            equation.set(j,newKey);
                            equation.remove(j+1);
                            conditionEnd--;
                            temp="";
                            varId++;
                        }
                    }
                    for(int j=conditionStart;j<=conditionEnd;j++) {
                        if((equation.get(j).equalsIgnoreCase("AND"))||(equation.get(j).equalsIgnoreCase("OR"))) {
                            temp=equation.get(j-1)+" "+equation.get(j)+" "+equation.get(j+1);
                            newKey="V"+Integer.toString(varId);
                            dictionary.put(newKey,temp);
                            equation.set(j-1,newKey);
                            equation.remove(j);
                            equation.remove(j);
                            j--;
                            conditionEnd--;
                            conditionEnd--;
                            temp="";
                            varId++;
                        }
                    }
                    for(int j=conditionStart;j<=conditionEnd;j++) {
                        if((equation.get(j).equalsIgnoreCase("sby"))) {
                            temp=equation.get(j-1)+" "+equation.get(j)+" "+equation.get(j+1);
                            newKey="V"+Integer.toString(varId);
                            dictionary.put(newKey,temp);
                            equation.set(j-1,newKey);
                            equation.remove(j);
                            equation.remove(j);
                            j--;
                            conditionEnd--;
                            conditionEnd--;
                            temp="";
                            varId++;
                        }
                    }
                    for(int j=conditionStart;j<=conditionEnd;j++) {
                        if((equation.get(j).equalsIgnoreCase("fby"))||(equation.get(j).equalsIgnoreCase("asa"))||(equation.get(j).equalsIgnoreCase("upon"))||(equation.get(j).equalsIgnoreCase("whenever"))||(equation.get(j).equalsIgnoreCase("wherever"))) {
                            temp=equation.get(j-1)+" "+equation.get(j)+" "+equation.get(j+1);
                            newKey="V"+Integer.toString(varId);
                            dictionary.put(newKey,temp);
                            equation.set(j-1,newKey);
                            equation.remove(j);
                            equation.remove(j);
                            j--;
                            conditionEnd--;
                            conditionEnd--;
                            temp="";
                            varId++;
                        }
                    }
                    for(int j=conditionStart;j<=conditionEnd;j++) {
                        temp=temp+" "+equation.get(j);
                    }
                    newKey="V"+Integer.toString(varId);
                    dictionary.put(newKey,temp);
                    equation.set(conditionStart,newKey);
                    temp="";
                    varId++;
                    for(int j=conditionStart+1;j<=conditionEnd;j++) {
                        equation.remove(j);
                        conditionEnd--;
                        endParenth--;
                        j--;
                    }
                }
            }
            lastAtomicEq=equation.size();
            if((lastAtomicEq==8)&&(equation.get(2).equals("("))) {
                equation.remove(startParenth);
                equation.remove(endParenth-1);
                break;
            }
            for(int i=startParenth+1;i<endParenth;i++) {
                if(equation.get(i).matches("[-+]?\\d*\\.?\\d+")) {
                    if(equation.get(i).matches("([-+]?[0-9]*)\\.([0-9]*)")) {
                        temp="flo "+equation.get(i);
                        newKey="V"+Integer.toString(varId);
                        dictionary.put(newKey,temp);
                        equation.set(i,newKey);
                        i--;
                        temp="";
                        varId++;
                    }
                    else {
                        temp="int "+equation.get(i);
                        newKey="V"+Integer.toString(varId);
                        dictionary.put(newKey,temp);
                        equation.set(i,newKey);
                        i--;
                        temp="";
                        varId++;
                    }
                }
            }
            for(int i=endParenth-1;i>startParenth;i--) {
                if(equation.get(i).equalsIgnoreCase("init")||equation.get(i).equalsIgnoreCase("right")|| equation.get(i).equalsIgnoreCase("succ")||equation.get(i).equalsIgnoreCase("all")) {
                    temp=equation.get(i)+" "+equation.get(i+1);
                    newKey="V"+Integer.toString(varId);
                    dictionary.put(newKey,temp);
                    equation.set(i,newKey);
                    equation.remove(i+1);
                    endParenth--;
                    temp="";
                    varId++;
                }
            }
            for(int i=endParenth-1;i>startParenth;i--) {
                if(equation.get(i).equalsIgnoreCase("first")||equation.get(i+1).equalsIgnoreCase("next")||equation.get(i).equalsIgnoreCase("elements")) {
                    temp=equation.get(i)+" "+equation.get(i+1);
                    newKey="V"+Integer.toString(varId);
                    dictionary.put(newKey,temp);
                    equation.set(i,newKey);
                    equation.remove(i+1);
                    endParenth--;
                    temp="";
                    varId++;
                }
            }
            for(int i=startParenth+1;i<endParenth;i++) {
                if((equation.get(i).equals("*"))&&(equation.get(i+1).equals("*"))) {
                    temp=equation.get(i-1)+" "+equation.get(i)+" "+equation.get(i+1)+" "+equation.get(i+2);
                    newKey="V"+Integer.toString(varId);
                    dictionary.put(newKey,temp);
                    equation.set(i-1,newKey);
                    equation.remove(i);
                    equation.remove(i);
                    equation.remove(i);
                    i--;
                    endParenth--;
                    endParenth--;
                    endParenth--;
                    temp="";
                    varId++;
                }
            }
            for(int i=startParenth+1;i<endParenth;i++) {
                if((equation.get(i).equals("*"))||(equation.get(i).equals("/"))||(equation.get(i).equals("%"))&&(!equation.get(i+1).equals("*"))) {
                    temp=equation.get(i-1)+" "+equation.get(i)+" "+equation.get(i+1);
                    newKey="V"+Integer.toString(varId);
                    dictionary.put(newKey,temp);
                    equation.set(i-1,newKey);
                    equation.remove(i);
                    equation.remove(i);
                    i--;
                    endParenth--;
                    endParenth--;
                    temp="";
                    varId++;
                }
            }
            for(int i=startParenth+1;i<endParenth;i++) {
                if((equation.get(i).equals("+"))||(equation.get(i).equals("-"))) {
                    temp=equation.get(i-1)+" "+equation.get(i)+" "+equation.get(i+1);
                    newKey="V"+Integer.toString(varId);
                    dictionary.put(newKey,temp);
                    equation.set(i-1,newKey);
                    equation.remove(i);
                    equation.remove(i);
                    i--;
                    endParenth--;
                    endParenth--;
                    temp="";
                    varId++;
                }
            }
            for(int i=startParenth+1;i<endParenth;i++) {
                if((equation.get(i).equals("<"))||(equation.get(i).equals(">"))||(equation.get(i).equalsIgnoreCase("eq"))||(equation.get(i).equalsIgnoreCase("ne"))) {
                    if(equation.get(i+1).equals("=")) {
                        temp=equation.get(i-1)+" "+equation.get(i)+" "+equation.get(i+1)+" "+equation.get(i+2);
                        newKey="V"+Integer.toString(varId);
                        dictionary.put(newKey,temp);
                        equation.set(i-1,newKey);
                        equation.remove(i);
                        equation.remove(i);
                        equation.remove(i);
                        i--;
                        endParenth--;
                        endParenth--;
                        endParenth--;
                        temp="";
                        varId++;
                    }
                    else {
                        temp=equation.get(i-1)+" "+equation.get(i)+" "+equation.get(i+1);
                        newKey="V"+Integer.toString(varId);
                        dictionary.put(newKey,temp);
                        equation.set(i-1,newKey);
                        equation.remove(i);
                        equation.remove(i);
                        i--;
                        endParenth--;
                        endParenth--;
                        temp="";
                        varId++;
                    }
                }
            }
            for(int i=startParenth+1;i<endParenth;i++) {
                if((equation.get(i).equals("="))||(equation.get(i).equals("!"))) {
                    if(equation.get(i+1).equals("=")) {
                        temp=equation.get(i-1)+" "+equation.get(i)+" "+equation.get(i+1)+" "+equation.get(i+2);
                        newKey="V"+Integer.toString(varId);
                        dictionary.put(newKey,temp);
                        equation.set(i-1,newKey);
                        equation.remove(i);
                        equation.remove(i);
                        equation.remove(i);
                        i--;
                        endParenth--;
                        endParenth--;
                        endParenth--;
                        temp="";
                        varId++;
                    }
                }
            }
            for(int i=endParenth-1;i>startParenth;i--) {
                if(equation.get(i).equalsIgnoreCase("NOT")) {
                    temp=equation.get(i)+" "+equation.get(i+1);
                    newKey="V"+Integer.toString(varId);
                    dictionary.put(newKey,temp);
                    equation.set(i,newKey);
                    equation.remove(i+1);
                    endParenth--;
                    temp="";
                    varId++;
                }
            }
            for(int i=startParenth+1;i<endParenth;i++) {
                if((equation.get(i).equalsIgnoreCase("AND"))||(equation.get(i).equalsIgnoreCase("OR"))) {
                    temp=equation.get(i-1)+" "+equation.get(i)+" "+equation.get(i+1);
                    newKey="V"+Integer.toString(varId);
                    dictionary.put(newKey,temp);
                    equation.set(i-1,newKey);
                    equation.remove(i);
                    equation.remove(i);
                    i--;
                    endParenth--;
                    endParenth--;
                    temp="";
                    varId++;
                }
            }
            for(int i=endParenth-1;i>startParenth;i--) {
                if((equation.get(i).equalsIgnoreCase("sby"))) {
                    temp=equation.get(i-1)+" "+equation.get(i)+" "+equation.get(i+1);
                    newKey="V"+Integer.toString(varId);
                    dictionary.put(newKey,temp);
                    equation.set(i-1,newKey);
                    equation.remove(i);
                    equation.remove(i);
                    i--;
                    endParenth--;
                    endParenth--;
                    temp="";
                    varId++;
                }
            }
            for(int i=endParenth-1;i>startParenth;i--) {
                if((equation.get(i).equalsIgnoreCase("fby"))||(equation.get(i).equalsIgnoreCase("asa"))||(equation.get(i).equalsIgnoreCase("upon"))||(equation.get(i).equalsIgnoreCase("whenever"))||(equation.get(i).equalsIgnoreCase("wherever"))) {
                    temp=equation.get(i-1)+" "+equation.get(i)+" "+equation.get(i+1);
                    newKey="V"+Integer.toString(varId);
                    dictionary.put(newKey,temp);
                    equation.set(i-1,newKey);
                    equation.remove(i);
                    equation.remove(i);
                    i--;
                    endParenth--;
                    endParenth--;
                    temp="";
                    varId++;
                }
            }
            equation.remove(startParenth);
            equation.remove(endParenth-1);
        }
        
        while(equation.contains("if")||equation.contains("If")||equation.contains("IF")||equation.contains("iF")) {
            conditionStart=0;
            conditionEnd=0;
            int s=-1;
            for(String u:equation) {
                s++;
                if(u.equalsIgnoreCase("if")) {
                    conditionStart=s;
                }
            }
            for(int i=conditionStart;i<tokenCount;i++) {
                if(equation.get(i).equalsIgnoreCase("fi")) {
                    conditionEnd=i;
                    break;
                }
            }
            for(int i=conditionStart;i<=conditionEnd;i++) {
                if(equation.get(i).matches("[-+]?\\d*\\.?\\d+")) {
                    if(equation.get(i).matches("([-+]?[0-9]*)\\.([0-9]*)")) {
                        temp="flo "+equation.get(i);
                        newKey="V"+Integer.toString(varId);
                        dictionary.put(newKey,temp);
                        equation.set(i,newKey);
                        i--;
                        temp="";
                        varId++;
                    }
                    else {
                        temp="int "+equation.get(i);
                        newKey="V"+Integer.toString(varId);
                        dictionary.put(newKey,temp);
                        equation.set(i,newKey);
                        i--;
                        temp="";
                        varId++;
                    }
                }
            }
            for(int i=conditionEnd-1;i>conditionStart;i--) {
                if(equation.get(i).equalsIgnoreCase("init")||equation.get(i).equalsIgnoreCase("right")|| equation.get(i).equalsIgnoreCase("succ")||equation.get(i).equalsIgnoreCase("all")) {
                    temp=equation.get(i)+" "+equation.get(i+1);
                    newKey="V"+Integer.toString(varId);
                    dictionary.put(newKey,temp);
                    equation.set(i,newKey);
                    equation.remove(i+1);
                    conditionEnd--;
                    temp="";
                    varId++;
                }
            }
            for(int i=conditionEnd-1;i>conditionStart;i--) {
                if(equation.get(i).equalsIgnoreCase("first")||equation.get(i).equalsIgnoreCase("next")||equation.get(i).equalsIgnoreCase("elements")) {
                    temp=equation.get(i)+" "+equation.get(i+1);
                    newKey="V"+Integer.toString(varId);
                    dictionary.put(newKey,temp);
                    equation.set(i,newKey);
                    equation.remove(i+1);
                    conditionEnd--;
                    temp="";
                    varId++;
                }
            }
            for(int i=conditionStart;i<=conditionEnd;i++) {
                if((equation.get(i).equals("*"))&&(equation.get(i+1).equals("*"))) {
                    temp=equation.get(i-1)+" "+equation.get(i)+" "+equation.get(i+1)+" "+equation.get(i+2);
                    newKey="V"+Integer.toString(varId);
                    dictionary.put(newKey,temp);
                    equation.set(i-1,newKey);
                    equation.remove(i);
                    equation.remove(i);
                    equation.remove(i);
                    i--;
                    conditionEnd--;
                    conditionEnd--;
                    conditionEnd--;
                    temp="";
                    varId++;
                }
            }
            for(int i=conditionStart;i<=conditionEnd;i++) {
                if((equation.get(i).equals("*"))||(equation.get(i).equals("/"))||(equation.get(i).equals("%"))&&(!equation.get(i+1).equals("*"))) {
                    temp=equation.get(i-1)+" "+equation.get(i)+" "+equation.get(i+1);
                    newKey="V"+Integer.toString(varId);
                    dictionary.put(newKey,temp);
                    equation.set(i-1,newKey);
                    equation.remove(i);
                    equation.remove(i);
                    i--;
                    conditionEnd--;
                    conditionEnd--;
                    temp="";
                    varId++;
                }
            }
            for(int i=conditionStart;i<=conditionEnd;i++) {
                if((equation.get(i).equals("+"))||(equation.get(i).equals("-"))) {
                    temp=equation.get(i-1)+" "+equation.get(i)+" "+equation.get(i+1);
                    newKey="V"+Integer.toString(varId);
                    dictionary.put(newKey,temp);
                    equation.set(i-1,newKey);
                    equation.remove(i);
                    equation.remove(i);
                    i--;
                    conditionEnd--;
                    conditionEnd--;
                    temp="";
                    varId++;
                }
            }
            for(int i=conditionStart;i<=conditionEnd;i++) {
                if((equation.get(i).equals("<"))||(equation.get(i).equals(">"))||(equation.get(i).equalsIgnoreCase("eq"))||(equation.get(i).equalsIgnoreCase("ne"))) {
                    if(equation.get(i+1).equals("=")) {
                        temp=equation.get(i-1)+" "+equation.get(i)+" "+equation.get(i+1)+" "+equation.get(i+2);
                        newKey="V"+Integer.toString(varId);
                        dictionary.put(newKey,temp);
                        equation.set(i-1,newKey);
                        equation.remove(i);
                        equation.remove(i);
                        equation.remove(i);
                        i--;
                        conditionEnd--;
                        conditionEnd--;
                        conditionEnd--;
                        temp="";
                        varId++;
                    }
                    else {
                        temp=equation.get(i-1)+" "+equation.get(i)+" "+equation.get(i+1);
                        newKey="V"+Integer.toString(varId);
                        dictionary.put(newKey,temp);
                        equation.set(i-1,newKey);
                        equation.remove(i);
                        equation.remove(i);
                        i--;
                        conditionEnd--;
                        conditionEnd--;
                        temp="";
                        varId++;
                    }
                }
            }
            for(int i=conditionStart;i<=conditionEnd;i++) {
                if((equation.get(i).equals("="))||(equation.get(i).equals("!"))) {
                    if(equation.get(i+1).equals("=")) {
                        temp=equation.get(i-1)+" "+equation.get(i)+" "+equation.get(i+1)+" "+equation.get(i+2);
                        newKey="V"+Integer.toString(varId);
                        dictionary.put(newKey,temp);
                        equation.set(i-1,newKey);
                        equation.remove(i);
                        equation.remove(i);
                        equation.remove(i);
                        i--;
                        conditionEnd--;
                        conditionEnd--;
                        conditionEnd--;
                        temp="";
                        varId++;
                    }
                }
            }
            for(int i=conditionEnd-1;i>conditionStart;i--) {
                if(equation.get(i).equalsIgnoreCase("NOT")) {
                    temp=equation.get(i)+" "+equation.get(i+1);
                    newKey="V"+Integer.toString(varId);
                    dictionary.put(newKey,temp);
                    equation.set(i,newKey);
                    equation.remove(i+1);
                    conditionEnd--;
                    temp="";
                    varId++;
                }
            }
            for(int i=conditionStart;i<=conditionEnd;i++) {
                if((equation.get(i).equalsIgnoreCase("AND"))||(equation.get(i).equalsIgnoreCase("OR"))) {
                    temp=equation.get(i-1)+" "+equation.get(i)+" "+equation.get(i+1);
                    newKey="V"+Integer.toString(varId);
                    dictionary.put(newKey,temp);
                    equation.set(i-1,newKey);
                    equation.remove(i);
                    equation.remove(i);
                    i--;
                    conditionEnd--;
                    conditionEnd--;
                    temp="";
                    varId++;
                }
            }
            for(int i=conditionStart;i<=conditionEnd;i++) {
                if((equation.get(i).equalsIgnoreCase("sby"))) {
                    temp=equation.get(i-1)+" "+equation.get(i)+" "+equation.get(i+1);
                    newKey="V"+Integer.toString(varId);
                    dictionary.put(newKey,temp);
                    equation.set(i-1,newKey);
                    equation.remove(i);
                    equation.remove(i);
                    i--;
                    conditionEnd--;
                    conditionEnd--;
                    temp="";
                    varId++;
                }
            }
            for(int i=conditionStart;i<=conditionEnd;i++) {
                if((equation.get(i).equalsIgnoreCase("fby"))||(equation.get(i).equalsIgnoreCase("asa"))||(equation.get(i).equalsIgnoreCase("upon"))||(equation.get(i).equalsIgnoreCase("whenever"))||(equation.get(i).equalsIgnoreCase("wherever"))) {
                    temp=equation.get(i-1)+" "+equation.get(i)+" "+equation.get(i+1);
                    newKey="V"+Integer.toString(varId);
                    dictionary.put(newKey,temp);
                    equation.set(i-1,newKey);
                    equation.remove(i);
                    equation.remove(i);
                    i--;
                    conditionEnd--;
                    conditionEnd--;
                    temp="";
                    varId++;
                }
            }
            for(int i=conditionStart;i<=conditionEnd;i++) {
                temp=temp+" "+equation.get(i);
            }
            newKey="V"+Integer.toString(varId);
            dictionary.put(newKey,temp);
            equation.set(conditionStart,newKey);
            temp="";
            varId++;
            for(int i=conditionStart+1;i<=conditionEnd;i++) {
                equation.remove(i);
                conditionEnd--;
                i--;
            }
        }
        
        numAreRemain=0;
        for(String e:equation) {
            numAreRemain++;
        }
        for(int i=0;i<numAreRemain;i++) {
            lastAtomicEq=equation.size();
            if((lastAtomicEq<5)&&(equation.get(2).matches("[-+]?\\d*\\.?\\d+"))) {
                if(equation.get(2).matches("([-+]?[0-9]*)\\.([0-9]*)")) {
                    temp="flo "+equation.get(2);
                    newKey=equation.get(0);
                    dictionary.put(newKey,temp);
                    equation.set(i,newKey);
                    i--;
                    temp="";
                    simplestEq=1;
                    break;
                }
                else {
                    temp="int "+equation.get(2);
                    newKey=equation.get(0);
                    dictionary.put(newKey,temp);
                    equation.set(i,newKey);
                    i--;
                    temp="";
                    simplestEq=1;
                    break;
                }
            }
            else if((lastAtomicEq>5)&&(equation.get(i).matches("[-+]?\\d*\\.?\\d+"))) {
                if(equation.get(i).matches("([-+]?[0-9]*)\\.([0-9]*)")) {
                    temp="flo "+equation.get(i);
                    newKey="V"+Integer.toString(varId);
                    dictionary.put(newKey,temp);
                    equation.set(i,newKey);
                    i--;
                    temp="";
                    varId++;
                }
                else {
                    temp="int "+equation.get(i);
                    newKey="V"+Integer.toString(varId);
                    dictionary.put(newKey,temp);
                    equation.set(i,newKey);
                    i--;
                    temp="";
                    varId++;
                }
            }
        }
        for(int i=numAreRemain-1;i>0;i--) {
            lastAtomicEq=equation.size();
            if((lastAtomicEq==5)) {
                break;
            }
            if(equation.get(i).equalsIgnoreCase("init")||equation.get(i).equalsIgnoreCase("right")|| equation.get(i).equalsIgnoreCase("succ")||equation.get(i).equalsIgnoreCase("all")) {
                temp=equation.get(i)+" "+equation.get(i+1);
                newKey="V"+Integer.toString(varId);
                dictionary.put(newKey,temp);
                equation.set(i,newKey);
                equation.remove(i+1);
                numAreRemain--;
                temp="";
                varId++;
            }
        }
        for(int i=numAreRemain-1;i>0;i--) {
            lastAtomicEq=equation.size();
            if((lastAtomicEq==5)) {
                break;
            }
            if(equation.get(i).equalsIgnoreCase("first")||equation.get(i).equalsIgnoreCase("next")||equation.get(i).equalsIgnoreCase("elements")) {
                temp=equation.get(i)+" "+equation.get(i+1);
                newKey="V"+Integer.toString(varId);
                dictionary.put(newKey,temp);
                equation.set(i,newKey);
                equation.remove(i+1);
                numAreRemain--;
                temp="";
                varId++;
            }
        }
        for(int i=0;i<numAreRemain;i++) {
            lastAtomicEq=equation.size();
            if((lastAtomicEq==6)) {
                break;
            }
            if((equation.get(i).equals("*"))&&(equation.get(i+1).equals("*"))) {
                temp=equation.get(i-1)+" "+equation.get(i)+" "+equation.get(i+1)+" "+equation.get(i+2);
                newKey="V"+Integer.toString(varId);
                dictionary.put(newKey,temp);
                equation.set(i-1,newKey);
                equation.remove(i);
                equation.remove(i);
                equation.remove(i);
                i--;
                numAreRemain--;
                numAreRemain--;
                numAreRemain--;
                temp="";
                varId++;
            }
        }
        for(int i=0;i<numAreRemain;i++) {
            lastAtomicEq=equation.size();
            if((lastAtomicEq==6)) {
                break;
            }
            if((equation.get(i).equals("*"))||(equation.get(i).equals("/"))||(equation.get(i).equals("%"))&&(!equation.get(i+1).equals("*"))) {
                temp=equation.get(i-1)+" "+equation.get(i)+" "+equation.get(i+1);
                newKey="V"+Integer.toString(varId);
                dictionary.put(newKey,temp);
                equation.set(i-1,newKey);
                equation.remove(i);
                equation.remove(i);
                i--;
                numAreRemain--;
                numAreRemain--;
                temp="";
                varId++;
            }
        }
        for(int i=0;i<numAreRemain;i++) {
            lastAtomicEq=equation.size();
            if((lastAtomicEq==6)) {
                break;
            }
            if((equation.get(i).equals("+"))||(equation.get(i).equals("-"))) {
                temp=equation.get(i-1)+" "+equation.get(i)+" "+equation.get(i+1);
                newKey="V"+Integer.toString(varId);
                dictionary.put(newKey,temp);
                equation.set(i-1,newKey);
                equation.remove(i);
                equation.remove(i);
                i--;
                numAreRemain--;
                numAreRemain--;
                temp="";
                varId++;
            }
        }
        for(int i=0;i<numAreRemain;i++) {
            lastAtomicEq=equation.size();
            if((lastAtomicEq==6))
            {
                break;
            }
            if((equation.get(i).equals("<"))||(equation.get(i).equals(">"))||(equation.get(i).equalsIgnoreCase("eq"))||(equation.get(i).equalsIgnoreCase("ne"))) {
                if(equation.get(i+1).equals("=")) {
                    temp=equation.get(i-1)+" "+equation.get(i)+" "+equation.get(i+1)+" "+equation.get(i+2);
                    newKey="V"+Integer.toString(varId);
                    dictionary.put(newKey,temp);
                    equation.set(i-1,newKey);
                    equation.remove(i);
                    equation.remove(i);
                    equation.remove(i);
                    i--;
                    numAreRemain--;
                    numAreRemain--;
                    numAreRemain--;
                    temp="";
                    varId++;
                }
                else {
                    temp=equation.get(i-1)+" "+equation.get(i)+" "+equation.get(i+1);
                    newKey="V"+Integer.toString(varId);
                    dictionary.put(newKey,temp);
                    equation.set(i-1,newKey);
                    equation.remove(i);
                    equation.remove(i);
                    i--;
                    numAreRemain--;
                    numAreRemain--;
                    temp="";
                    varId++;
                }
            }
        }
        for(int i=0;i<numAreRemain;i++) {
            lastAtomicEq=equation.size();
            if((lastAtomicEq==6)) {
                break;
            }
            if((equation.get(i).equals("="))||(equation.get(i).equals("!"))) {
                if(equation.get(i+1).equals("=")) {
                    temp=equation.get(i-1)+" "+equation.get(i)+" "+equation.get(i+1)+" "+equation.get(i+2);
                    newKey="V"+Integer.toString(varId);
                    dictionary.put(newKey,temp);
                    equation.set(i-1,newKey);
                    equation.remove(i);
                    equation.remove(i);
                    equation.remove(i);
                    i--;
                    numAreRemain--;
                    numAreRemain--;
                    numAreRemain--;
                    temp="";
                    varId++;
                }
            }
        }
        for(int i=numAreRemain-1;i>0;i--) {
            lastAtomicEq=equation.size();
            if(lastAtomicEq==5) {
                break;
            }
            else if(equation.get(i).equalsIgnoreCase("NOT")) {
                temp=equation.get(i)+" "+equation.get(i+1);
                newKey="V"+Integer.toString(varId);
                dictionary.put(newKey,temp);
                equation.set(i,newKey);
                equation.remove(i+1);
                numAreRemain--;
                temp="";
                varId++;
            }
        }
        for(int i=0;i<numAreRemain;i++) {
            lastAtomicEq=equation.size();
            if ((lastAtomicEq==6)) {
                break;
            }
            if(lastAtomicEq<=5) {
                break;
            }
            if((equation.get(i).equalsIgnoreCase("AND"))||(equation.get(i).equalsIgnoreCase("OR"))) {
                temp=equation.get(i-1)+" "+equation.get(i)+" "+equation.get(i+1);
                newKey="V"+Integer.toString(varId);
                dictionary.put(newKey,temp);
                equation.set(i-1,newKey);
                equation.remove(i);
                equation.remove(i);
                i--;
                numAreRemain--;
                numAreRemain--;
                temp="";
                varId++;
            }
        }
        for(int i=numAreRemain-1;i>0;i--) {
            lastAtomicEq=equation.size();
            if ((lastAtomicEq==6)) {
                break;
            }
            if(lastAtomicEq<=5) {
                break;
            }
            if(equation.get(i).equalsIgnoreCase("sby")) {
                
                temp=equation.get(i-1)+" "+equation.get(i)+" "+equation.get(i+1);
                newKey="V"+Integer.toString(varId);
                dictionary.put(newKey,temp);
                equation.set(i-1,newKey);
                equation.remove(i);
                equation.remove(i);
                i--;
                numAreRemain--;
                numAreRemain--;
                temp="";
                varId++;
            }
        }
        for(int i=numAreRemain-1;i>0;i--) {
            lastAtomicEq=equation.size();
            if ((lastAtomicEq==6)) {
                break;
            }
            if(lastAtomicEq<=5) {
                break;
            }
            if((equation.get(i).equalsIgnoreCase("fby"))||(equation.get(i).equalsIgnoreCase("asa"))||(equation.get(i).equalsIgnoreCase("upon"))||(equation.get(i).equalsIgnoreCase("whenever"))||(equation.get(i).equalsIgnoreCase("wherever"))) {
                temp=equation.get(i-1)+" "+equation.get(i)+" "+equation.get(i+1);
                newKey="V"+Integer.toString(varId);
                dictionary.put(newKey,temp);
                equation.set(i-1,newKey);
                equation.remove(i);
                equation.remove(i);
                i--;
                numAreRemain--;
                numAreRemain--;
                temp="";
                varId++;
            }
        }
        for(String e:equation) {
            remainToken++;
        }
        for(int i=2;i<remainToken;i++) {
            if(!equation.get(i).equals(";"))
            {
                temp=temp+" "+equation.get(i);
            }
        }
        if(simplestEq==0) {
            if(remainToken<5) {
                varId--;
                newKey="V"+Integer.toString(varId);
                dictionary.put(equation.get(0),dictionary.get(newKey));
                dictionary.remove(newKey);
                varId++;
            }
            else {
                dictionary.put(equation.get(0),temp);
            }
        }
        temp="";
        return varId;
    }
}
