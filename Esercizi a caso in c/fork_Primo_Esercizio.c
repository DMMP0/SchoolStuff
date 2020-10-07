#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>

int main()
{
   int ppid, pid;
  //genera il processo figlio
  pid = fork();

  //termina in caso di errore  di chiamata nella fork

  if(pid == -1)
    {
      printf("errore nella fork()");
      exit(1);
    }
   //se il pid è zero ci si trova nel figlio
  if(pid == 0)
    {
      printf("Io sono il figlio,\n pid figlio = %d,\n pid padre = %d \n", getpid(),getppid());
printf("Se il pid del padre è diverso è perchè il padre è morto ed il figlio è stato acquisito da qualcuno \n");
    }
   //se il pid è zero ci si trova nel padre
   if(pid >0)
   {
     printf("Io sono il padre, pid padre = %d,\n pid figlio = %d \n " , getpid(),pid);  //se si mette getppid quì , si ottiene il pid del nonno del processo
     
   }
}

