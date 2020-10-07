#include <stdlib.h>
#include <stdio.h>
#include <time.h>

void * generate_random(void* arg)
{
	int r = rand() % 100;
	return (void*) r;
}

int main()
{
	srand(time(NULL));
	int* r;
	pthread_t thread;
	
	int failure = pthread_create( &thread, NULL, &generate_random, NULL );
	if (failure)
	{
		printf("\nError during the creation of the thread\n");
		return 1;
	}
		
	printf("\nThread created. Waiting for result. \n");
	
	failure = pthread_join(thread, &r);
	if (failure)
	{
		printf("Error number %d during the execution of the thread\n\n", failure);
		return 1;
	}
	
	printf("Thread terminated: result is %d. \n\n", (int)r);
	return 0;
}
