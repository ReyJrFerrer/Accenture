class Bike{
    void run(){System.out.println("running");}
}
class Splendor extends Bike{
    void run(){System.out.println("running safely with 60km");}
}

class HondaBeat extends Bike {
    void run(){System.out.println("playful type");}
}

class Adv160 extends Bike {
    void run(){System.out.println("RoadSync Type");}
}

public class Main{
    public static void main(String args[]){
        Bike bike = null;

        bike = new Splendor();//upcasting
        bike.run();

        bike = new HondaBeat();
        bike.run();

        bike = new Adv160();
        bike.run();



    }
}  