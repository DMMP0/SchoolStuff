#!/bin/bash
#Il programma mostra i file con x estensione e poi dovrebbe cancellarli, ma per sicurezza uso un echo
if test $# = 0
  then
    echo Nessun parametro passato  >> estLog.txt
    exit 1
fi
cd $1
ls -l -g -h -R | grep txt | more
echo rm ./*.$2
cd ..
