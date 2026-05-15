package accenture;

public interface InterfaceExample {
    int Max_SPEED = 10;

    void StartEngine();

    double isStarting();

    default void printMethod(){
        System.out.println("Printing");
    }

}
