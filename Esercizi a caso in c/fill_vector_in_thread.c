#include <stdlib.h>
#include <stdio.h>
#include <time.h>

int cmp (const void* a, const void* b)
{
	int _a = *(int*)a;
	int _b = *(int*)b;
	if ( _a < _b )
		return -1;
	else
		return 1;
}
	

void * fill_int_vector(void* arg)
{
	int* v = (int*) arg;
	int i;
	for (i = 0; i < 5; i++)
	{
		v[i] = rand() % 100;
	}
	return 0;
}

int main()
{
	srand(time(NULL));
	int v[5];
	pthread_t thread;
	
	int failure = pthread_create( &thread, NULL, &fill_int_vector, (void*)v );
	if (failure)
	{
		printf("\nError during the creation of the thread\n");
		return 1;
	}
	
	printf("\nThread created. Waiting for result. \n");
	
	failure = pthread_join(thread, NULL);
	if (failure)
	{
		printf("Error number %d during the execution of the thread\n\n", failure);
		return 1;
	}
	
	printf("Thread terminated: vector is ");
	int i;
	for (i = 0; i < 5; i++)
	{
		printf("%d ", v[i]);
	}
	
	printf("\nSorting vector...\n");
	qsort( v, 5, sizeof(int), &cmp );
	
	printf("Done. New vector is ");
	for (i = 0; i < 5; i++)
	{
		printf("%d ", v[i]);
	}
	printf("\n\n");
	return 0;
}
