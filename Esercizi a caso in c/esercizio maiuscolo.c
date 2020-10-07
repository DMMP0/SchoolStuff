#include<stdio.h>
#include<stdlib.h>
#include<math.h>

void Maiuscolo(char *str) //Scopo: data una stringa deve trasformare tutte le lettere in maiuscole
{
  //Si usa il deferenziatore per accedere al carattere
  while(*str != '\0')
  {
     if(*str >= 'a' && *str <= 'z' )   // contolla se sono minuscole tramite la tabella hash  (ricordarsi  di mettere le ' ' e non le "")
        *str -= 32;    // le minuscole si distanziano dalle maiuscole di 32 , A + 32 = a. 
     //Non si usa il deferenziatore
     str ++ ;  // aumenta l' indirizzo . Essendo un puntatore sa di base di quanto spostarsi, quindi basta fare ++
  }
 
}

int main()
{
  char S[] = "Costo_pasta_13_euro\n";   //se si dichiara direttamente la stringa si può evitare di dire di quante celle sarà composto l' array
  Maiuscolo(S);
  printf("%s",S);
  
   return 0;
}
