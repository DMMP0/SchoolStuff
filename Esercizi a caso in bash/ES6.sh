#!/usr/bin/env bash

read -p "Inserire un numero tra 1 e 10: " n1

case $n1 in
1)	
	echo il valore inserito è 1
	;;
2)	
	echo il valore inserito è 2
	;;
3)	
	echo il valore inserito è 3
	;;
4)	
	echo il valore inserito è 4
	;;
5)	
	echo il valore inserito è 5
	;;
6)	
	echo il valore inserito è 6
	;;
7)	
	echo il valore inserito è 7
	;;
8)	
	echo il valore inserito è 8
	;;
9)	
	echo il valore inserito è 9
	;;
10)	
	echo il valore inserito è 10
	;;
*) 
	echo Errore: sai contare?
	;;
esac
