#!/usr/bin/env bash

if [ ! -d out ]; then
	mkdir out
	for x in {1..5}; do
		touch out/file_$x
	done
else
	echo Errore: directory già esistente!
fi
