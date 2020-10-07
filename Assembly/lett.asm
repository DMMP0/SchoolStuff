.model small
.stack
.data
      carattere db ?
.code
 inizio:
   mov ax,@data
   mov ds,ax
;stampa del prompt
   mov dl,"?"
   mov ah,02
   int 21h
;lettura carattere
   mov ah,01
   int 21h
   mov carattere,al
;ritorno a capo
   mov dl,0Ah
   mov ah,02
   int 21h
   mov dl,0Dh
   mov ah,02
   int 21h
;stampa carattere
   mov dl,carattere
   mov ah,02
   int 21h
   mov ah,4ch
   int 21h
end inizio 
