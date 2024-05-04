package VendingMachine.States;

import VendingMachine.Snack;
import VendingMachine.VendingMachine;

import java.util.ArrayList;
import java.util.List;

public class DispensingState implements StateOfVendingMachine {
    public double insertMoney(VendingMachine vendingMachine, double Money) {
        System.out.println("Snack Machine Dispensing. Cannot insert money.");
        return vendingMachine.returnMoney();
    }

    public void selectSnack(VendingMachine vendingMachine, String snack) {
        System.out.println("Snack Machine Ready to Dispense. Please Wait.");
    }

    public List<Object> dispenseSnack(VendingMachine vendingMachine) {
        Snack snack = vendingMachine.handleRequest();
        if(snack != null) {
            vendingMachine.setMoneyInput(snack.getPrice());
            System.out.println(snack.getName() + " dispensed");
            System.out.printf("Change Due: $%.2f\n", snack.getPrice());
        }
        else{
            System.out.printf("Returning: $%.2f\n", vendingMachine.returnMoney());
        }
        List<Object> result = new ArrayList<>();
        result.add(snack);
        result.add(vendingMachine.returnMoney());
        vendingMachine.setState(new IdleState());
        return result;
    }
}
