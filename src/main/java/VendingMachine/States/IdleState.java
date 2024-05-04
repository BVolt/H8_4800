package VendingMachine.States;

import VendingMachine.VendingMachine;

import java.util.ArrayList;
import java.util.List;

public class IdleState implements StateOfVendingMachine {
    public double insertMoney(VendingMachine vendingMachine, double money) {
        System.out.println("Please select a snack first.");
        return vendingMachine.returnMoney();
    }

    public void selectSnack(VendingMachine vendingMachine, String snack) {
        System.out.println("Snack Selected: " + snack);
        vendingMachine.setSnackChoice(snack);
        vendingMachine.setState(new WaitingState());
    }

    public List<Object> dispenseSnack(VendingMachine vendingMachine) {
        System.out.println("Cannot dispense. No snack selected.");
        List<Object> result = new ArrayList<>();
        result.add(null);
        result.add(0.00);

        return result;
    }
}
