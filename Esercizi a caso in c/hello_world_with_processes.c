#include <stdlib.h>
#include <stdio.h>
#include <sys/wait.h>

int main()
{
	int pid = fork();
	if (pid == -1)
	{
		printf("\n\nError during the creation of the process\n\n");
		return 1;
	}
	
	if (pid == 0)
	{
		printf("\n\nHello ");
	}
	else
	{
		wait(NULL);
		pid = fork();
		if (pid == -1)
		{
			printf("\nError during the creation of the process\n\n");
			return 1;
		}
		if (pid == 0)
		{
			printf("World!\n\n");
		}
	}
	wait(NULL);
	return 0;
}
