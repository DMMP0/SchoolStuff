#!/usr/bin/env bash

if [ $# -le 0 ]; then
	echo lanciare lo script passando almeno un file come argomento!
	exit 1
fi

echo verranno spostati i seguenti files: ${@:1}
read -p "continuare? [Y\n]: " r
if [ ! $r = Y ] && [ ! -z $r ]; then
	echo annullato!
	exit 0
fi

if [ ! -d $HOME/old ]; then
	mkdir $HOME/old
fi

for x in ${@:1}; do
	if [ ! -e $x ]; then 
		echo il file $x non esiste!
		exit 0
	fi
	mv $x $HOME/old
done

exit 0
