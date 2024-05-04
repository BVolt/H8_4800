package VendingMachine;

import VendingMachine.SnackDispenserCOR.*;
import VendingMachine.States.IdleState;
import VendingMachine.States.StateOfVendingMachine;

import java.util.*;

public class VendingMachine {
    private StateOfVendingMachine state = new IdleState();
    private SnackDispenserHandler snackDispenser;
    private String snackChoice = "";
    private double MoneyInput = 0;

    public VendingMachine() {
        this.snackDispenser = new CokeDispenserHandler(new PepsiDispenserHandler(new CheetosDispenserHandler(new DoritosDispenserHandler(new KitKatDispenserHandler(new SnickersDispenserHandler(null))))));
    }

    public void setState(StateOfVendingMachine state){
        this.state = state;
    }

    public void selectSnack(String snack){
        this.state.selectSnack(this, snack);
    }

    public double insertMoney(double money){
        this.MoneyInput = money;
        return this.state.insertMoney(this, money);
    }

    public List<Object> dispenseSnack(){
        return this.state.dispenseSnack(this);
    }

    public Snack handleRequest(){
        return this.snackDispenser.handleRequest(snackChoice, MoneyInput);
    }

    public double returnMoney(){
        return this.MoneyInput;
    }

    public void setSnackChoice(String snackChoice) {
        this.snackChoice = snackChoice;
    }

    public void setMoneyInput(double moneyInput) {
        MoneyInput = moneyInput;
    }

    public StateOfVendingMachine getState(){
        return this.state;
    }
}
