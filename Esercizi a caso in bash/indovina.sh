#!/bin/bash
#lo script cerca di individuare un numero pensato dall'utente
a=1
b=100
for((;;))
do
    var=$(shuf -i $a-$b -n 1) 2> indovinaLog.txt
    echo "Il numero a cui ha pensato è $var ? [S/n] "
    read r
    if test "$r" == "S"  2> indovinaLog.txt
      then
        echo "pensato a $var" >> indovinaLog.txt
        break;
      else
        echo "Il numero da lei pensato è maggiore[+] o minore[-]?"
        read r
        if test "$r" == "+" #2> indovinaLog.txt
          then
             let "a=$var+1"
          else
              if test "$r" == "-" #2> indovinaLog.txt ;then
                then
                  let "b=$var-1"
                else
                  echo error
                  break;
              fi
        fi
    fi
done
