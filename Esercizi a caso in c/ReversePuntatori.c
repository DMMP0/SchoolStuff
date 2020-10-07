#include <stdio.h>
#include <stdlib.h>

void nrInsertor(float *ptr,int nr)             //inserisci i numeri
{
  int i;
  for (i = 0;i < nr;i++)
  {
  	printf("Inserisci numero (NO virgola) \n");
  	scanf("%f",(ptr + i));  	
  	printf("Numero inserito %f ",*(ptr + i));
  	printf("in posizione %d \n \n",i);
  }   	
}

void reverse (float *pa,float* pb,int n)          //funzione di scambio tra i due puntatori dove pt1 da i suoi valori a pt2 che gli assume mettendoli al contrario
{
	int i,j;
	for (i = 0,j = (n - 1);i < n;i++, j--)
	{
		*(pb + i) = *(pa + j);
	}
}

void stampaPunt (float *p,int n)        //stampa il nuovo pt2 
{
	int i;
	for (i = 0;i < n;i++)
	{
		printf("In posizione %d c'e' \n",i); 
		printf("%f \n",*(p + i));
	}
}

void main()
{
	int nr;
	float *ptra,*ptrb;
	printf ("Inserisci la lunghezza del vettore: \n");
	scanf("%d",&nr);
	ptra = (float *)calloc (nr,sizeof(float));
	ptrb = (float *)calloc (nr,sizeof(float));
	nrInsertor(ptra,nr);
	reverse(ptra,ptrb,nr);
	stampaPunt(ptrb,nr);	
	
}
