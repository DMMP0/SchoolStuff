package robot;

//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;

//import java.util.concurrent.ThreadLocalRandom;

class WorldDevouringCalamity implements Runnable //se il programma non termina dopo 45 secondi lo ferma
{


    public WorldDevouringCalamity()
    {

       Main.allThreads.add( new Thread(() -> {
            run();
        }));
    }
    @Override
    public void run() {
        try {
            Thread.sleep(15000);
            System.out.print("\n\n\nOne\n\n\n");
            Thread.sleep(15000);
            System.out.print("\n\n\nTwo\n\n\n");
            Thread.sleep(15000);
            System.out.print("\n\n\nThree\n\n\n");

        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.print("Seems that the world is gonna end sooner...");
        }
        finally
        {
            System.out.print("The World Was Destroyed\n");
            System.exit(0);
        }


    }
}
