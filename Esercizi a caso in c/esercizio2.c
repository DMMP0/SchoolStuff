#include<stdio.h>
#include<stdlib.h>

void scambiaptr(int* a, int* b) 
{
   int temp;
   temp = *a;
   *a = *b;
   *b = temp;
   printf("--------------------------\n");
   printf( " dentro scambiaptr \n");
   printf("a = %p ,\n b = %p \n",a,b);
   printf("&a = %p ,\n &b = %p \n ",&a,&b);
   printf("--------------------------\n");

}


void scambia(int a, int b) 
{
   int temp;
   temp = a;
   a = b;
   b = temp;
   printf("--------------------------\n");
   printf("  dentro scambia \n");
   printf("a = %d ,\n b = %d \n",a,b);
   printf("&a = %p ,\n &b = %p \n ",&a,&b);
   printf("--------------------------\n");

}

int main()
{
   int a = 5 , b = 7;
   int *ptra;
   int *ptrb;
   
   ptra = &a;
   ptrb = &b;

   printf("a = %d ,\n b = %d \n",a,b);
   printf("&a = %p,\n &b = %p \n",&a,&b);
printf("--------------------------\n");
   printf("scambia \n");

   
    scambia(a,b);
   
   printf("a = %d ,\n b = %d \n",a,b);
   printf("&a = %p,\n &b = %p \n",&a,&b);
printf("--------------------------\n");
   printf("scambiaptr \n");

   scambiaptr(ptra,ptrb);
   printf("a = %d ,\n b = %d \n",a,b);
   printf("&a = %p ,\n &b = %p \n",&a,&b);
    
}
