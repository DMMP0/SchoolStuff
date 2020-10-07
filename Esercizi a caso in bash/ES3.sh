#!/usr/bin/env bash

read -p "Inserire un numetro tra 1 e 10: " n1

if [ $n1 -ge 1 -a $n1 -le 10 ]; then
	echo raddoppio l\'offerta! $((n1*2))
else
	echo sai contare?
fi
