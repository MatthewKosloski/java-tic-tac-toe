#!/bin/bash  
path=$1;
program=$2;          
cd $path && javac $program.java Board.java Computer.java Main.java MyUtils.java Player.java Test.java TicTacToe.java Tile.java User.java && java $program;