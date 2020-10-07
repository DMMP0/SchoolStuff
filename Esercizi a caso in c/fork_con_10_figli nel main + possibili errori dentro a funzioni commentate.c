#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>

#include<sys/wait.h>  //per la WEXITSTATUS() macro

#include<sys/types.h>

#include <errno.h>  //per la perror()
#define nump 10


void forkBase()
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
         printf("Se il pid del padre è diverso è perchè il padre è morto ed il figlio è stato    acquisito da qualcuno \n");
      }
      //se il pid è zero ci si trova nel padre
      if(pid >0)
      {
        
        printf("Io sono il padre, pid padre = %d,\n pid figlio = %d \n " , getpid(),pid); 
        //se si mette getppid quì , si ottiene il pid del nonno del processo
       }
}

void switchl( pid_t pid)
{
 int uscita,status;
 switch(pid ) {
        case -1:
            perror("fork");  /* errore */
            exit(1);         /* il padre esce con errore 1 */
        case 0:
            printf(" CHILD: processo figlio !\n");
            printf(" CHILD: il mio PID : %d\n", getpid());
            printf(" CHILD: il PID di mio padre: %d\n", getppid());
            printf(" CHILD: scrivi il valore di uscita (< 100):\n ");
            scanf(" %d", &uscita);
            sleep(3);
            printf(" CHILD: termino !\n");
            exit(uscita);

        default:
            sleep(5); //serve per avere il tempo di scrivere il valore di exit()
            printf("PARENT: processo padre !\n");
            printf("PARENT: il mio PID : %d\n", getpid());
            printf("PARENT: il PID di mio figlio : %d\n", pid);
	    printf("PARENT: Attendo la fine del figlio ...\n");sleep(3);
            printf("PARENT: PID del primo processo figlio terminato:  %d\n" ,wait(&status));

            printf("PARENT: ecco lo stato di uscita del figlio :%d\n", WEXITSTATUS(status));
            printf("PARENT: termino !\n");
     }
}



void ForkConErroriGuardareDentro()
{ 

    pid_t pid1;   // pid_t : creato per identificare il pid , ma è un intero
int i = 0;
  /* pid_t pid2;  
pid_t pid3;  
pid_t pid4;  
pid_t pid5;  
pid_t pid6;  
pid_t pid7;  
pid_t pid8;  
pid_t pid9;  
pid_t pid10; */
int uscita,status;

/*while(i < 10)
{
switchl(pid1);

i++;                                  facendo così genera sempre lo stesso figlio va in errore

}

 */
    
   
/*
switchl(pid2);
switchl(pid3);
switchl(pid4);
switchl(pid5);
switchl(pid6);
switchl(pid7);
switchl(pid8);
switchl(pid9);
switchl(pid10);*/                                   //facendo così crea circa 1000 figli
/*
while(i < 10)                                    //anche così vanno tutti in errore
{
    switch(pid1 ) {
        case -1:
            perror("fork");  /* errore *//*
           exit(1);         // il padre esce con errore 1 
        case 0:
            printf(" CHILD: processo figlio !\n");
            printf(" CHILD: il mio PID : %d\n", getpid());
            printf(" CHILD: il PID di mio padre: %d\n", getppid());
            printf(" CHILD: scrivi il valore di uscita (< 100):\n ");
            scanf(" %d", &uscita);
            sleep(3);
            printf(" CHILD: termino !\n");
            exit(uscita);

        default:
            sleep(5); //serve per avere il tempo di scrivere il valore di exit()
            printf("PARENT: processo padre !\n");
            printf("PARENT: il mio PID : %d\n", getpid());
            printf("PARENT: il PID di mio figlio : %d\n", pid1);
	    printf("PARENT: Attendo la fine del figlio ...\n");sleep(3);
            printf("PARENT: PID del primo processo figlio terminato:  %d\n" ,wait(&status));

            printf("PARENT: ecco lo stato di uscita del figlio :%d\n", WEXITSTATUS(status));
            printf("PARENT: termino !\n");
}
     
 i++;
}*/
}
int main()
{
   
     int i,pid;

for( i = 0; i < nump ; i++)
{

   if((pid = fork()) == 0)
     {
       usleep(500 * (i+1) );
       printf("Figlio : %d \n", i+1);
       usleep(5000 * (i+1) );
       return(101+i);
     }else{

     printf("figlio %d con pid %d",i+1,pid); 
     }
}



for(i = 0; i < nump ; i++)
{

int status;
wait(&status);
printf("terminato processo %d \n",WEXITSTATUS(status));

}

   
}

