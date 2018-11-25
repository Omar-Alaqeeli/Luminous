//Amend scanned equation (add spaces to identify tokens) for correct formation

class amend {
    public static String amend(String equation) {
        String newEquation="";
        for(int i=0;i<equation.length();i++) {
            if(equation.charAt(i)=='+'||equation.charAt(i)=='<'||equation.charAt(i)=='>'||equation.charAt(i)=='!'||equation.charAt(i)=='%'||equation.charAt(i)=='*'||equation.charAt(i)=='/'||equation.charAt(i)=='='||equation.charAt(i)==';'||equation.charAt(i)=='('||equation.charAt(i)==')'||equation.charAt(i)==' ') {
                newEquation=newEquation+" "+equation.charAt(i)+" ";
            }
            else if(equation.charAt(i)=='-') {
                if(equation.charAt(i+1)=='('||equation.charAt(i-1)==')') {
                    newEquation=newEquation+" "+equation.charAt(i)+" ";
                }
                else {
                    newEquation=newEquation+equation.charAt(i);
                }
            }
            else {
                newEquation=newEquation+equation.charAt(i);
            }
        }
        return newEquation;
    }
}
