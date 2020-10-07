#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>

void crea(int * ptr_v)
{
	ptr_v = (int * )malloc(10 * sizeof(int));
	printf("ptr_v crea: %p\n", ptr_v);
}

int * creaInt(int dim)
{
	int * ptr_v;
	ptr_v = (int * )malloc(10 * sizeof(int));
	printf("ptr_v nella creaInt: %p\n", ptr_v);
	return ptr_v;
}

void inizializza(int * ptr_v)
{
	int d = malloc_usable_size(ptr_v) / sizeof(int);
	while(d > 0)
	{
		*ptr_v = d;
		ptr_v++;
		d--;
	}
}

void stampa(int * ptr_v)
{
	int d = malloc_usable_size(ptr_v) / sizeof(int);
	while(d > 0)
	{
		printf("%d\n", *ptr_v);
		d--;
		ptr_v++; 
	}
}

void main()
{
	int * ptr_v;
	printf("ptr_v inizializzato: %p\n", ptr_v);
	crea(ptr_v);
	printf("ptr_v dopo crea: %p\n", ptr_v);
	ptr_v = creaInt(10);
	printf("ptr_v dopo creaInt: %p\n", ptr_v);
	inizializza(ptr_v);
	stampa(ptr_v);
}
