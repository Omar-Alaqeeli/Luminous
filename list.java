//List all variables and their expressions in the dictionary

import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

class list {
    public static void list(Map<String,String> dictionary) {
        System.out.println();
        System.out.println();
        System.out.println("Variable : Expression");
        for(String u:dictionary.keySet()) {
            System.out.println(u+" : "+dictionary.get(u));
        }
        System.out.println();
    }
}
