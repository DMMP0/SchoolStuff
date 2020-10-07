#include<stdio.h>
#include<stdlib.h>
#include<math.h>
#define true 1
#define null NULL
#define false 0


//la calloc inizializza tutti gli elementi a 0 (n elementi , dimensione del singolo elementi)
// 
// la realloc void *(void *ptr, sizet new)  (bisogna passare lo spazio allocato dinamicamente) in teoria si potrebbe usare al posto della malloc o calloc, ma è meglio di no perchè su windows procura spesso problemi

//con free(void *) si libera lo spazio. Dopo aver chiamato la free si deve mettere a null il puntatore

/*Se vuoi usare un vettore senza puntatori la sintassi è la seguente:
  int vettore1[10];
  char vettore2[] = "Lol XD";
  Se vuoi accedere al valore del vettore, ad esempio il primo, devi scrivere v[0]
  int n = v[0];
  */
  
  //funzione per swappare 2 numeri
  void swap(int* a, int* b) 
{ 
    int t = *a; 
    *a = *b; 
    *b = t; 
} 
/* Prende l' ultimo pivot, lo mette nella posizione corretta nell' array ordinato, 
    mette tutti gli elementi minori del pivot a sinistra ed i maggiori a destra*/
int partition (int *v, int low, int high) 
{ 
    int pivot = *(v+high);    // pivot 
    int i = (low - 1);  // Indice del numero più piccolo
  
    for (int j = low; j <= high- 1; j++) 
    { 
        // se l'elemento corrente è minore o uguale al pivot
        if (*(v+j) <= pivot) 
        { 
            i++;    // increment index of smaller element 
            swap(v+i, v+j); 
        } 
    } 
    swap(v+i+1, v+high); 
    return (i + 1); 
} 
/* 
  low  --> indice di inizio, 
  high  --> indice di fine */
void quickSort(int *v, int low, int high) 
{ 
    if (low < high) 
    { 
        /* pi = indice di partizionamento  */
        int pi = partition(v, low, high); 
  
        quickSort(v, low, pi - 1); 
        quickSort(v, pi + 1, high); 
    } 
} 
int Max(int* v,int dim,int isArrayOrdered)
{
    if(!isArrayOrdered)
    quickSort(v,0,dim-1);
    
      return *(v+dim-1);
}
int Min(int* v,int dim,int isArrayOrdered)
{
    if(!isArrayOrdered)
    quickSort(v,0,dim-1);
    
      return *(v);
}
  
int Somma(int* v,int dim)
{
    int ris=0,i;
    for(i=0;i<dim;i++)
    {
    ris += *(v+i);
//  ris += v[i]; per i nabbi
    }
    return ris;
    
}
float SommaFloat(float* v,int dim)
{
    float ris=0;
    int i=0;
    for(i=0;i<dim;i++)
    {
    ris += *(v+i);
//  ris += v[i]; per i nabbi
    }
    return ris;
    
}

//statistica
int Moda(int* v,int dim)
{ 
    int maxValue = 0, maxCount = 0, i, j;

   for (i = 0; i < dim; ++i) {
      int count = 0;
      
      for (j = 0; j < dim; ++j) {
         if (*(v+j) == *(v+i))
         count++;
      }
      
      if (count > maxCount) {
         maxCount = count;
         maxValue = *(v+i);
      }
   }

   return maxValue;
    
    }
float Media(int* v,int dim)
{
    
    return ((float)Somma(v,dim)/(float)dim);
    
}
float Mediana(int* v,int dim,int isArrayOrdered)
{
    if(!isArrayOrdered)
        quickSort(v,0,dim-1);
    if(dim%2 == 0)
        return ((*(v+(dim/2))+*(v+( dim/2 + 1)))/2);
    else
    return (*(v+(int)(dim/2)));
}
float DeviazioneStandard(int* v,int dim)
{
    float media = Media(v,dim),ris;
    float* Scarti = (float*)calloc(dim,sizeof(float));
    int i;
    for(i=0;i<dim;i++)
    {
        *(Scarti+i) = pow((float)*(v+i)-media,2);
    }
    ris = sqrt(SommaFloat(Scarti,dim)/((float)(dim)));
    free(Scarti);
    Scarti=NULL;
    return ris;
}

void Ricerca(int* v,int dim,int n)
{
    int i;
    for(i=0;i<dim;i++)
    {
        //  if( v[i] == n) per i nabbi
    if(*(v+i) == n)
      {printf("Il numero %d è il %do nel vettore\n",n,i+1);return;}
        
    }
    printf("Il numero %d non è presente nel vettore\n",n);
    
}

void stampa(int* v,int dimensione)
{
   int i= 0;
   for(i = 0;i<dimensione;i++)
   {
      printf(" [%d] ",*(v + i)/* v[i] per i nabbi*/);
   }
}

//  non serve la prima parte per i nabbi
int* allocare(int * v, int dim)
{
    
   if(v == NULL)
   {
      v = (int *)calloc(dim,sizeof(int));
      
   }
   else
   {
      v = (int*)realloc(v,dim);
   }
   printf("Inserire %d numeri \n",dim);
  // da qui in poi si
   if(v != NULL)
   {
   int i= 0;
   for(i = 0;i<dim;i++)
   {
      scanf("%d",v + i);
   }
   }
   else
   {
   printf("deallocato o nullo\n");
   }
   return v;
}



int main()
{
  int scelta ,dim,num,orderedArr = false;
  int *v;
  printf("Scegliere tra le seguenti funzioni:\n");
  while(true)
  {
    printf("\n1)Inserisci\n2)Statistica\n3)somma\n4)stampa\n5)Ricerca\n0)esci\n");
    scanf("%d",&scelta);
    switch(scelta)
    {
      case 1:
      {
         
        printf("inserire la dimensione voluta\n");
        scanf("%d",&dim);
        v = allocare(v,dim);
        orderedArr = false;
        break;
      }
      case 2:
      {
           if(v == NULL){
          printf("Inserire prima dei numeri!");
          break;
      }
        quickSort(v,0,dim-1);
        orderedArr=true;
        
         printf("\nIl valore minimo è %d\n",Min(v,dim,orderedArr));
         printf("La valore massimo è %d\n",Max(v,dim,orderedArr));
         printf("La Media è %f\n",Media(v,dim));
         printf("La Moda è %d\n",Moda(v,dim));
         printf("La Mediana è %f\n",Mediana(v,dim,orderedArr));
         printf("La Deviazione Standard è %f\n",DeviazioneStandard(v,dim));
         printf("Array ordinato:\n");stampa(v,dim);
         break;
      }
      case 3:
      {
           if(v == NULL){
          printf("Inserire prima dei numeri!");
          break;
      }
         printf("La somma è %d\n",Somma(v,dim));
         break;
      }
      case 4 : default :
      {
        
         if(v != NULL){
            //se è stato liberato prima va in errore
         stampa(v,dim);}
         else{
         printf("vettore deallocato o nullo\n");
         }
         break;
      }
      case 5:
      {
           if(v == NULL){
          printf("Inserire prima dei numeri!");
          break;
      }
      printf("Che numero cercare?\n");
    scanf("%d",&scelta);
        Ricerca(v,dim,scelta);
        break;
      }
      case 0 :
      {
          free(v);
       return 0;
      }
    }
  }
}