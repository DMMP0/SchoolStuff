#!/usr/bin/env bash

if [ -z $1 ] || [ -z $2 ]; then
	echo Errore: lanciare lo script con due valori come parametri!
	exit
fi

echo somma: $(($1+$2))
echo sottr: $(($1-$2))
echo molti: $(($1*$2))
echo divis: $(($1/$2))
echo resto: $(($1%$2))
