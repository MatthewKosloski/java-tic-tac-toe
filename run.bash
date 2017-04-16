#!/bin/bash  
path=.;
program=Main; 
find . -name $program.java | entr -c bash compile.bash $path $program;