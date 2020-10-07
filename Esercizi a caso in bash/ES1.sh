#!/usr/bin/env bash

read -p "Inserire il primo numero: " n1
read -p "Inserire il secondo numero: " n2

echo somma: $((n1+n2))
echo sottr: $((n1-n2))
echo molti: $((n1*n2))
echo divis: $((n1/n2))
echo resto: $((n1%n2))
