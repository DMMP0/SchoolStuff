#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>

//Ritorna l'indirizzo dell'ultimo numero nell'array
int * cercaLast(int * ptr_v, int numero, int dim)
{
	ptr_v += dim;
	while(dim > 0)
	{
		if(*ptr_v == numero)
			return ptr_v;
		else
		{
			ptr_v--;
			dim--;
		}
	}
}
 //Sostituisce il valore vecchio con quello nuovo
void modifica(int * ptr_v, int oldNumber, int newNumber, int dim)
{
	while(dim > 0)
	{
		if(*ptr_v == oldNumber)
			*ptr_v = newNumber;
		dim--;
		ptr_v++;
	}
}

void stampaIndirizzo(int * ptr_v, int dim)
{
	while(dim > 0)
	{
		printf("Valore: %d\tIndirizzo: %x\n", *ptr_v, ptr_v);
		dim--;
		ptr_v++;
	}
}

void stampa(int * ptr_v, int dim)
{
	while(dim > 0)
	{
		printf("Valore: %d\n", *ptr_v);
		dim--;
		ptr_v++;
	}
}

void main()
{
	int old, new;
	int c;
	int cont = 0;
	int n;
	int v[300];
	int * ptr_v;
	ptr_v = v;
	int * ris;
	int scelta;
	printf("1)CercaLast\n2)Modifica\nScegli la funzione: ");
	scanf("%d", &scelta);
	switch(scelta)
	{
		case 1:
			printf("Quanti valori vuoi inserire? ");
			scanf("%d", &n);
			while(cont < n)
			{
				printf("Inserisci il valore: ");
				scanf("%d", &c);
				*ptr_v = c;
				ptr_v++;
				cont++;
			}
			ptr_v -= n;
			printf("Insersci il numero da cercare: ");
			scanf("%d", &c);
			stampaIndirizzo(ptr_v, n);
			ris = cercaLast(ptr_v, c, n);
			printf("L'indirizzo dell'ultimo valore cercato è: %x\n\n", ris);
			break;
		case 2:
			printf("Quanti valori vuoi inserire? ");
			scanf("%d", &n);
			while(cont < n)
			{
				printf("Inserisci il valore: ");
				scanf("%d", &c);
				*ptr_v = c;
				ptr_v++;
				cont++;
			}
			ptr_v -= n;
			printf("Inserisci il vecchio valore: ");
			scanf("%d", &old);
			printf("Inserisci il nuovo valore: ");
			scanf("%d", &new);
			modifica(ptr_v, old, new, n);
			stampa(ptr_v, n);
			break;	
	}
}
