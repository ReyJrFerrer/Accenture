package accenture.customer;


public class Customer implements TaxRequirement {

    //input instance variables (owned by the object)
    private String name; //default value of null
    private String fuelType; //default value of null
    private double numberOfLiters; //default value of 0.0

    //computed instance variables (owned by the object)
    private double grossAmount; //default value of 0.0
    private double vatAmount; //default value of 0.0
    private double netAmount; //default value of 0.0
    private String fuelName; //default value of null

    private boolean isValidTransaction; //default value of false

    //HAS-A Relationship
    private BpiCardServiceDivision bpiCardService; //defaulted to null

    public Customer() { //no-arg constructor
        isValidTransaction = true;

        System.out.println("no-arg constructor executed.");
    }

    //Constructor Dependency Injection (CDI)
    public Customer(String name, String fuelType, double numberOfLiters,
                    BpiCardServiceDivision bpiCardService) { //parameterized constructor

        this(); //this constructor call

        this.name = name;
        this.fuelType = fuelType;
        this.numberOfLiters = numberOfLiters;

        this.bpiCardService = bpiCardService; //CDI

        System.out.println("parameterized constructor executed.");
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public double getNumberOfLiters() {
        return numberOfLiters;
    }

    public void setNumberOfLiters(double numberOfLiters) {
        this.numberOfLiters = numberOfLiters;
    }

    public double getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(double grossAmount) {
        this.grossAmount = grossAmount;
    }

    public double getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(double vatAmount) {
        this.vatAmount = vatAmount;
    }

    public double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(double netAmount) {
        this.netAmount = netAmount;
    }

    public String getFuelName() {
        return fuelName;
    }

    public void setFuelName(String fuelName) {
        this.fuelName = fuelName;
    }

    public boolean isValidTransaction() {
        return isValidTransaction;
    }

    public void setValidTransaction(boolean isValidTransaction) {
        this.isValidTransaction = isValidTransaction;
    }

    public void computeFuelPurchase() {
        switch (fuelType) {
            case "1":
                grossAmount = numberOfLiters * 92.13;
                fuelName = "Diesel";
                break;
            case "2":
                grossAmount = numberOfLiters * 90.65;
                fuelName = "Unleaded";
                break;
            case "3":
                grossAmount = numberOfLiters * 102.97;
                fuelName = "Premium 97";
                break;
            default:
                isValidTransaction = false;
                break;
        }

        if (isValidTransaction) {
            computeVAT();
            netAmount = grossAmount + vatAmount;
        }
    }

    public BpiCardServiceDivision getBpiCardService() {
        return bpiCardService;
    }

    //setter dependency injection
    public void setBpiCardService(BpiCardServiceDivision bpiCardService) {
        this.bpiCardService = bpiCardService;
    }

    @Override
    public void computeVAT() {
        vatAmount = grossAmount * NEW_VAT_RATE;
    }
}