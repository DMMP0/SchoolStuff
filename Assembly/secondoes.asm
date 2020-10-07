codice SEGMENT 
	ASSUME CS: codice
inizio:         mov AX,05
                mov BX,03
                add AX,BX
                mov AX,4Ch
          	int 21h
codice ENDS
END inizio 
          
          	
