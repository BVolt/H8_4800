package VendingMachine.SnackDispenserCOR;

import VendingMachine.Snack;

public abstract class SnackDispenserHandler {
    private SnackDispenserHandler next;

    public SnackDispenserHandler(SnackDispenserHandler next){
        this.next = next;
    }

    public Snack handleRequest(String snackName, double money){
        if(next != null){
            return next.handleRequest(snackName, money);
        }else {
            System.out.println("Not a valid snack in vending inventory.");
            return null;
        }
    }
}
