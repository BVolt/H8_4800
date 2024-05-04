package VendingMachine;
import java.util.List;

public class Driver {
    public static double customerWallet;
    public static Snack customerSnack;
    public static VendingMachine vendingMachine;

    public static void main(String[] args) {
        customerWallet = 300.00;
        customerSnack = null;
        vendingMachine = new VendingMachine(); // Vending Machine constructor initializes chain of responsibility snack inventory

        //Showing Chain of Responsibility. Function below.
        getSnack("Pepsi", 0.75);
        getSnack("Pepsi", 2.00);
        getSnack("Snickers", 3.00);
        getSnack("Snickers", 1.00);
        getSnack("Kitkat", 1.00);
        getSnack("KitKat", 1.00);

        //Showing Executing vending machine commands in wrong state
        vendingMachine.dispenseSnack();
        vendingMachine.insertMoney(100.00);
        vendingMachine.selectSnack("Pepsi");
        vendingMachine.dispenseSnack();
        vendingMachine.insertMoney(100.00);
        vendingMachine.dispenseSnack();
    }

    public static void getSnack(String snack, double payment){
        List<Object> vendingOutput;
        vendingMachine.selectSnack(snack);
        customerWallet -= payment; // Pull money out of wallet
        customerWallet += vendingMachine.insertMoney(payment); // Returns 0 if money successfully taken
        vendingOutput = vendingMachine.dispenseSnack(); //Outputs snack and change. Snack is null and money returned if unsuccessful.
        customerSnack = (Snack)vendingOutput.get(0);
        customerWallet += (double)vendingOutput.get(1);
        if (customerSnack != null){
            System.out.println("Customer Received Snack: " + customerSnack.getName());
        }
        System.out.printf("Customer Balance: $%.2f\n", customerWallet);
        System.out.println();
    }
}
