package VendingMachine.States;

import VendingMachine.VendingMachine;

import java.util.ArrayList;
import java.util.List;

public class WaitingState implements StateOfVendingMachine {
    public double insertMoney(VendingMachine vendingMachine, double money) {
        vendingMachine.setMoneyInput(money);
        vendingMachine.setState(new DispensingState());
        System.out.printf("$%.2f inserted.\n", money);
        return 0.00;
    }

    public void selectSnack(VendingMachine vendingMachine, String snack) {
        vendingMachine.setSnackChoice(snack);
        System.out.println("New snack selected.");
    }

    public List<Object> dispenseSnack(VendingMachine vendingMachine) {
        System.out.println("Cannot Dispense Snack. No money inserted.");
        List<Object> result = new ArrayList<>();
        result.add(null);
        result.add(0.00);

        return result;
    }
}

