#!/bin/bash

   # es3_trova_grandi.bash <percorso>

    #da un messaggio di errore se non esiste <percorso>
    #stampa i primi 5 file più grandi presenti in <percorso> (non le sottodirectory)

         #find -printf”%s %p\n” | sort -h | tail 
    if test $# == 0
       then
           echo errore, passare percorso come parametro
       else
           cd $1
           ls -g -h -G  | sort -h -r | head -n 6 #funziona meglio
    fi
    cd ..
