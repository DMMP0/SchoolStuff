#!/bin/bash
    #es1_cancella_bak.bash <percorso>

    #chiede di inserire una password all’utente
    #se la password corrisponde a “mperuffo” cancella tutti i file con estensione .bak presenti in <percorso> e sottodirectory
    #se la password è sbagliata da un messaggio di errore
     
     echo "Inserire Password" 
     read pass
     if test $pass == "mperuffo"
       then
           cd $1
          rm -r -d  ./*.bak
           cd ..
      else
         echo errore
    fi

