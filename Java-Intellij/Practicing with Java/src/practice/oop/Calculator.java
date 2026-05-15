package practice.oop;

public class Calculator extends Operations {
    double result ;

    public void setResult(double result){
        this.result = result;
    }
    public double getResult(){
        return result;
    }

    @Override
    public double add(double a, double b) {
        return a + b;
    }
    public double add() {
        return 0.0d;
    }

    @Override
    public double subtract(double a, double b) {
        return a - b;
    }

    @Override
    public double divide(double a, double b) {
        if (a == 0 || b == 0){
            return 0.0d;

        }
        return a / b;
    }

    @Override
    public double multiply(double a, double b) {
        return a * b;
    }
    @Override
    public void display(double result){
        String stringResult = String.format("%.2f", result);
        System.out.println("The result is " + stringResult);;
    }

    public void displayChoices(){
        System.out.println("Choose an operation: ");
        System.out.println("1. Add");
        System.out.println("2. Subtract");
        System.out.println("3. Multiply");
        System.out.println("4. Divide");
        System.out.print("Choice: ");
    }


}
