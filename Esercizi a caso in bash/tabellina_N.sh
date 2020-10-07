#!/bin/bash
echo inserire numero
read n
echo inserire fino a che numero la si vuole
read j
let "j=$j+1"
i=1
for((i=1;i<j;i++))
{
  let "t=$i*$n"
  echo -n "$t "
} 
 echo -e "\n"
