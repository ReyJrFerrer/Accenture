package pluralsight.singleton;

public class DbSingleton {

//    private static DbSingleton instance = new DbSingleton();

    // thread-safe and lazy loaded
    private static class LazyHolder{
        static final DbSingleton INSTANCE = new DbSingleton();
    }

    private DbSingleton (){}

    public static DbSingleton getInstance(){
        return LazyHolder.INSTANCE;
    }
}
