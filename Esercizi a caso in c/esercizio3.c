#include<stdio.h>
#include<stdlib.h>

int main()
{
   int vettore[5];
   int* ptrvettore ;
   ptrvettore =    vettore;
   
  // sizeof(int);          4 byte
 //  sizeof(int*);        8 byte
  
  printf("&vettore = %p \n vettore = %p \n &vettore[0] = %p \n ",&vettore,vettore,&vettore[0]);
   sizeof(int*);
 printf("%lu \n %lu \n ",sizeof(int*),sizeof(int));

  
}
