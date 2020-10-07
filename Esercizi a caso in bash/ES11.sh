#!/usr/bin/env bash

if [ ! -d out ]; then
	mkdir out
fi

read -p "Inserire un numero: " n1

x=1
while [[ $x -le n1 ]]; do
	echo $x
	touch out/file_$x
	let x++
done

exit 0
