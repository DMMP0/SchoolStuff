#!/usr/bin/env bash

if [ ! -d out ]; then
	mkdir out
fi

read -p "Inserire un numero: " n1

for ((x = 0; x <= $n1; x++)); do
	echo $x
	touch out/file_$x
done

exit 0
