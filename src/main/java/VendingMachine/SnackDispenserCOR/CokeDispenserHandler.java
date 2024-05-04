package VendingMachine.SnackDispenserCOR;

import VendingMachine.Snack;

public class CokeDispenserHandler extends SnackDispenserHandler {
    Snack snack = new Snack("Coke", 1.00, 9);
    public CokeDispenserHandler (SnackDispenserHandler next){
        super(next);
    }

    public Snack handleRequest(String snackType, double money){
        if(snackType.equals(snack.getName())){
            if(snack.getQuantity() > 0) {
                if(money >= snack.getPrice()) {
                    snack.decreaseQuantity();
                    return new Snack("Coke", money - snack.getPrice(), 1);
                }else{
                    System.out.println("Not enough money inputted. Need: " + snack.getPrice());
                    return null;
                }
            }
            else {
                System.out.println("Out of " + snack.getName());
                return null;
            }
        }else{
            return super.handleRequest(snackType, money);
        }
    }
}
