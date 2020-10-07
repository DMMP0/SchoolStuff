package com.company;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class TheGuy implements Saved
{
    public int X;
    public int Y;
    public static int TimesTheGuyWasSaved = 0;
    private List<Saved> listeners = new ArrayList<Saved>();

    public TheGuy()
    {
        X = ThreadLocalRandom.current().nextInt(0, 999 + 1);
        Y = ThreadLocalRandom.current().nextInt(0, 999 + 1);
    }

    @Override
    public void someoneWasSaved()
    {
        System.out.print("Guy says: Yeeeeeeeee\n");
        TimesTheGuyWasSaved++;
        System.exit(0);
    }
}
interface Saved {
    void someoneWasSaved();
}
// Deve essere implementata da chiunque voglia controllare se qualcuno è stato salvato

public  class  Robot
{
    private  static int TheID;
    private int ID; //1 - TheID
    private  int x; // 0 - 999
    private  int y; // 0 - 999
    private int Battery; // 0- 100
    private int speed;  // 0 cm/s 100 cm/s
    private int direction;  //  0 - 8
    private TheGuy Dude;
    private List<Saved> listeners = new ArrayList<Saved>();
    public boolean TheGuyWasSaved = false;

    //8    1    5
    //4    0    2
    //7    3    6

    //get e set

    public void addListener(Saved toAdd) {
        listeners.add(toAdd);
    }

    public static int getTheID() {
        return TheID;
    }
    public static void setTheID(int theID) {
        TheID = theID;
    }
    public int getID() {
        return ID;
    }
    public void setID(int ID)
    {
        if(ID < TheID)
            System.out.println("ID già usato");
        else
           this.ID = ID;
    }
    public int getIntX() {
        if(x>999)
            setX(999);
        if (x<0)
            setX(0);
           return x;
    }
    public float getFloatX()
    {
        float ris = (float)(x);
        return (ris/100);
    }
    public void setX(int x) {
        if(x < 0)
            x *= -1;
        if(x>999)
            this.x = 999;
        else
            this.x = x;
    }
    public void setX(float x) {
        if(x < 0)
            x *= -1;
        if(x>9.99f)
            this.x = 999;
        else
            this.x = (int)(x*100);
    }
    public int getIntY() {
        if(y>999)
            setY(999);
        if(y<0)
           setY(0);
        return y;
    }
    public float getFloatY()
    {
        float ris = (float)(y);
        return (ris/100);
    }
    public void setY(int y) {
        if(y < 0)
            y *= -1;
        if(y>999)
            this.y = 999;
        else
            this.y = y;
    }
    public void setY(float y) {
        if(y < 0)
            y *= -1;
        if(y>9.99f)
            this.y = 999;
        else
            this.y = (int)(y*100);
    }
    public int getBattery() {
        return Battery;
    }
    public void setBattery(int battery) {
        Battery = battery;
        if(battery > 100)
            Battery = 100;
        if(battery < 0)
            Battery = 0;
    }
    public int getIntSpeed() {
        return speed;
    }
    public float getFloatSpeed()
    {
        float ris = (float)(speed);
        return (ris/100);
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void setSpeed(float speed) {
        this.speed =(int)(speed*100);
    }
    public int getDirection() {
        return direction;
    }
    @NotNull
    public String getStringDirection()
    {
        if(direction == 0)
            return "fermo" ;
        if(direction == 1)
            return "su";
        if(direction == 2)
            return "destra";
        if(direction == 3)
            return "giù";
        if(direction == 4)
            return "sinistra";
        if(direction == 5)
            return "nord-est";
        if(direction == 6)
            return "sud-est";
        if(direction == 7)
            return "sud-ovest";
        else
            return "nord-ovest";
    }
    public void setDirection(int direction) {
        if(direction < 0)
            direction *= -1;
        if(direction > 8)
            this.direction = 0;
        else
           this.direction = direction;
    }
    public TheGuy getDude() {
        return Dude;
    }
    public void setDude(TheGuy dude) {
        Dude = dude;
    }

    //costruttori

    public Robot()
    {

        TheID += 1;
        ID = TheID;
        setX( ThreadLocalRandom.current().nextInt(0, 999 + 1));
        setY( ThreadLocalRandom.current().nextInt(0, 999 + 1));
        setBattery( 100);
        speed = 100;
        direction = 0;
        Go();
        Dude = new TheGuy();

    }
    public Robot(TheGuy d)
    {

        TheID += 1;
        ID = TheID;
        setX( ThreadLocalRandom.current().nextInt(0, 999 + 1));
        setY( ThreadLocalRandom.current().nextInt(0, 999 + 1));
        setBattery( 100);
        speed = 100;
        direction = 0;
        Dude = d;
        Go();


    }
    public Robot(Robot a)
    {
        ID = a.getID();
        setX(a.getIntX());
        setY(a.getIntY());
        Battery = a.getBattery();
        speed = a.getIntSpeed();
        direction = a.getDirection();
        Dude = a.getDude();


    }
    public Robot(Robot a, int x, int y)
    {
        ID = a.getID();
        setX(x);
        setY(y);
        Battery = a.getBattery();
        speed = a.getIntSpeed();
        direction = a.getDirection();
        Dude = a.getDude();

    }
    public Robot(Robot a, float x, float y)
    {
        ID = a.getID();
        this.x = (int)(x*100);
        this.y = (int)(y*100);
        Battery = a.getBattery();
        speed = a.getIntSpeed();
        direction = a.getDirection();
        Dude = a.getDude();

    }
    public Robot(int id,int X, int Y , int b,int s,int d,TheGuy t)
    {
        TheID +=1;
        ID = id;
        x = X;
        y = Y;
        setBattery( b);
        speed = s;
        direction = d;
        Dude = t;
        Go();

    }

    //metodi

    public void Go() // fa partire move e battery drain
    {


        Main.allThreads.add( new Thread(() -> {
            BatteryDrain();
        }));
       Main.allThreads.add( new Thread(() -> {
           try {
               move();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }));
        Main.allThreads.add( new Thread(() -> {
            try {
                check(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));

    }
    public void move() throws InterruptedException {
        while(true)
        {
          if (direction == 1)
            setY(y -= 1);
          if (direction == 2)
            setX(x += 1);
          if (direction == 3)
            setY(y +=1);
          if (direction == 4)
            setX(x -= 1);
          if (direction == 5)
          {
            setY(y -= 1);
            setX(x += 1);
          }
          if (direction == 6)
          {
            setY(y += 1);
            setX(x += 1);
          }
          if (direction == 7)
          {
            setY(y += 1);
            setX(x -= 1);
          }
          if (direction == 8)
          {
            setY(y -= 1);
            setX(x -= 1);
          }
          if (Battery <= 35)
            setSpeed((speed / 2 - 1) * 2);
          if (speed == 0)
          {
            setBattery( Battery / 2);
            speed = 100; }
           int r = ThreadLocalRandom.current().nextInt(0, 100 + 1);
           if (r > 75) {
            direction = ThreadLocalRandom.current().nextInt(0, 8 + 1);
            if(Battery == 0)
                break;
           }
            try
            {
                Thread.sleep(1000/speed);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }



    }
    private void BatteryDrain()
    {
        while(true)
        {

            try {
                Thread.sleep(500);
               setBattery( Battery -= 1);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(Battery == 0)
            {
                break;
            }
        }
    }
    public void check(int i) throws InterruptedException
    {
        int c=0;
        while (true)
        {
            if(getBattery() == 0)
                c++;
            if(c>2)
                break;
            System.out.print("Sec: " + i + ":  ID: " + getID() + "; X = " + getFloatX() + "; Y = " + getFloatY() + "; Speed: " + getFloatSpeed() + "; Battery: " + getBattery() + "\n");
            Thread.sleep(1000);
            i++;
             if(TheGuyWasSaved)
                 break;

        }


    }

    public void saveTheGuy()
    {
        System.out.println("The guy was saved by me: Robot " + getID() );
        TheGuyWasSaved = true;
        // Notifica gli interessati
        for (Saved hl : listeners)
            hl.someoneWasSaved();
    }

}






