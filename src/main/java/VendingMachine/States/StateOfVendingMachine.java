package VendingMachine.States;

import VendingMachine.VendingMachine;
import java.util.*;

public interface StateOfVendingMachine {

    public double insertMoney(VendingMachine vendingMachine, double money);
    public void selectSnack(VendingMachine vendingMachine, String snack);
    public List<Object> dispenseSnack(VendingMachine vendingMachine);
}


