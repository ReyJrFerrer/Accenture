package accenture;
public class VarArgs {

    //VarArgs (variable-length arguments)
    // that allows a method to accept zero or more arguments of the same data type.
    static double totalDeposits(double... deposits){
        double total = 0;
        for(double deposit : deposits){
            total += deposit;
        }
        return total;
    }

    public static void main(String[] args) {
        //Passing the arguments to the method totalDeposits using varargs. We can pass as many arguments as we want.
        double total = totalDeposits(100.50, 200.75, 50.25);

        System.out.println("Total Deposits: $" + total);

        double anotherTotal = totalDeposits(500.00, 150.25, 300.75, 400.00, 250.50);
        System.out.println("Another Total Deposits: $" + anotherTotal);
    }

}
