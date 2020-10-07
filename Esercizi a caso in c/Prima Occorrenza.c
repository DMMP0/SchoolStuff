#include<stdio.h>
#include<stdlib.h>
#include<math.h>

void stampa(char * a)  // stampa la stringa , carattere abbinato ad indirizzo
{
	while(*a != '\0')    //finche non finisce la stringa
	{
		printf("Carattere: %c      Indirizzo : %p\n",*a,a);  //stampa carattere per carattere, carattere ed indirizzo
		a++;
	}		
}

char* Occorrenza(char *stringa ,char c )    //ritorna la prima occorrenza di c in stringa
{
	
	while(*stringa != c)   //finche non trova il carattere
	{
		if(*stringa == '\0')  // il carattere non c'è
		{
			printf("%s","Il carattere non è presente");   
			return NULL ;
			
		}
		stringa ++;
	}
	
	
	return stringa;   //ritorna l'indirizzo del carattere
}

int main()
{
  char S[] = "Ciao_bello";
  char carattere = 'l';
  char *ris = Occorrenza(S,carattere) ;
  printf("La prima occorrenza di %c è all'indirizzo : %p \n",*ris,ris);
  printf("Per verificare: \n");
 stampa(S);
 
  return 0;
}
