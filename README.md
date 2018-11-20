<!DOCTYPE html>
<html>
<body>

<h1>Luminous</h1>
<p>
Luminous is a Java-based interactive interpreter for Lucid programming language. It uses machine terminal for inputs and outputs. The folder Luminous/ contains all files necessary to run the interpreter. The file luminous.java is the main file that starts subroutines calling sequence when running.
</p>

<h2>Compilation and Running</h2>
<p>
  Assuming all the files are downloaded and located in the same the directory, using Terminal (mac OS):
  
  To compile all files at once:
  <pre> javac *.java </pre>
  
  To compile one file:
  <pre> javac file_name.java </pre>
  
  To run the interpreter: 
  <pre> java luminous </pre>

  When running, the right shift operator “>>” appears and user can start typing commands.
</p>

<h2>Luminous Commands</h2>
<li> var v: is used to declare a variable, v, and assign a value to it. </li>
<li> val v: is used to evaluate v and print its value according to its dimensions. </li>
<li> defs: to list all variables stored in the dictionary along with their expressions. </li>
<li> Defined v: returns true if v can be evaluated and returns false otherwise. </li>
<li> constant v: returns true if there is no temporal operators (exception: first) involve in an expression that is linked directly or indirectly to v. otherwise return false. </li>
<li> dims v: returns dimensions that v depends on; t for the time dimension, s for the space dimension, t & s for both and none for nothing. </li>

<br> <b>Note:</b> 1 is used to represent true and 0 to represent false. </br>


<h2>Tutorials</h2>

<h3>Example1:</h3>

If a=47, b=35 and c is their sum, then we define them as follows:
>> var a=47;
>> var b=35;
>> var c=a+b;
>> val c
82

Notice that we can’t add a and b directly without first defining a variable that equal to their sum, c, thus the evaluation is inquired for c. Notice also, when defining variables, sentence has to be concluded with a semicolon, “;”, otherwise and error will occur. 


<h3>Example2:</h3>
>> var i=1;
>> var j=i fby j+1;
>> val j
1 
2 
3 
4
..
..

When variable under evaluation has only one dimension, the stream of outputs is printed vertically (time dimension) or horizontally (space dimension). The stream is continued infinitely (due to the nature of Lucid programming language) and in this case the terminal has to be interrupted by using control+C (mac OS).

<h3>Example3:</h3>
>> var P=100 sby (P fby init P+1);  
>> val P
100 100 100 100 100 ...
100 101 101 101 101 ...
100 101 101 101 101 ...
100 101 101 101 101 ...
100 101 101 101 101 ...
... ... ... ...
... ...

When evaluating a variable with two dimensions, the stream of values is printed in a form of a matrix. For demonstration purposes, only five columns are printed (in reality, the stream is infinite) while printing rows is infinite therefore the terminal has to be interrupted. 

<h3>Important Note:</h3>
When evaluating variables that output streams of values (either one dimension or two), the interpreter is programmed to pause one second between each evaluation iteration for the purpose of demonstrating the outputs for the user. 

<h3>Example4:</h3>
>> var j=i fby i+1;
>> defs

Variable : Expression
V0 : int 1
V1 : i + V0
j :  i fby V1

defs lists all values in the dictionary; variables and their atomic expressions. 

>> defined j
false

j can’t be defined because i is not yet defined. Hence, j can’t be evaluated. 

>> constant j
false

j is not a constant since its expression includes the operator fby that constantly recalculates j.

>> dims j
t

j relies on time dimension. 

</body>
</html>
