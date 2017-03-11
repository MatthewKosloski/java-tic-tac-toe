#!/bin/bash  
path=.;
program=TicTacToe; 
find . -name $program.java | entr -c bash compile.bash $path $program;