#!/usr/bin/env bash

backup_dir="backup"

if [ ! -d $backup_dir ]; then
	mkdir $backup_dir
fi

read -p "Inserire il nome del file: " x
if [ -e $x ]; then
	cp $x $backup_dir/$x.bak
	echo backup eseguito
else
	echo File non trovato!
fi
