##Sudoku Solver
###Installation
* Java 12+
* Build command (external libs under **/lib** folder)
````shell
mvn clean install
````

###Overview
* Can solve sudoku of any complexity
* Use zeros as a hidden fields
* Use positional arguments from 0 - 80 to set up fields:
<p>
[ _0, _1, _2  ] [ _3, _4, _5  ] [ _6, _7, _8  ]<br />
[ _9,  10, 11 ] [ 12, 13, 14 ]  [ 15, 16, 17 ]<br />
[ 18, 19, 20 ] [ 21, 22, 23 ]  [ 24, 25, 26 ]<br />
<p>
[ 27, 28, 29 ] [ 30, 31, 32 ]  [ 33, 34, 35 ]<br />
[ 36, 37, 38 ] [ 39, 40, 41 ]  [ 42, 43, 44 ]<br />
[ 45, 46, 47 ] [ 48, 49, 50 ]  [ 51, 52, 53 ]<br />
<p>
[ 54, 55, 56 ] [ 57, 58, 59 ]  [ 60, 61, 62 ]<br />
[ 63, 64, 65 ] [ 66, 67, 68 ]  [ 69, 70, 71 ]<br />
[ 72, 73, 74 ] [ 75, 76, 77 ]  [ 78, 79, 80 ]<br />

* If arguments length will be less than 81, all missing values will be 0 (hidden)
* Fields can be set individually from the menu
* Other menu options:
  * Clear sudoku fields
  * Undo last move
  * Show candidates / meta info for the field
  * Set one field
  * Print out sudoku field

###Example command to run a jar file
````shell
java -jar .\target\sudokuSolver-2.0.jar 1 2 3 4 5 6 7 8 9 
````