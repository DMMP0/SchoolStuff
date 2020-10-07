codice SEGMENT 
	ASSUME CS: codice
inizio: 	mov AH,03
          	mov AL,04
          	add AH,AL
          	mov AH,4Ch
          	int 21h
codice ENDS
END inizio 
          
          	
