package VendingMachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.*;

public class VendingMachineTest {

    private VendingMachine vendingMachine;

    @BeforeEach
    public void setUp() {
        vendingMachine = new VendingMachine();
    }

    @Test
    public void testReturnMoneyWithoutPurchase() {
        vendingMachine.insertMoney(2.00);
        double returnedMoney = vendingMachine.returnMoney();
        assertEquals(2.00, returnedMoney);
    }

    @Test
    public void testSnackDispensationProcess() {
        vendingMachine.selectSnack("Coke");
        vendingMachine.insertMoney(1.00);
        List<Object> result = vendingMachine.dispenseSnack();
        Snack snack = (Snack) result.get(0);
        double change = (Double) result.get(1);
        assertNotNull(snack);
        assertEquals("Coke", snack.getName());
        assertEquals(0.00, change);
    }

    @Test
    public void testChangeDispensationWithExcessMoney() {
        vendingMachine.selectSnack("Coke");
        vendingMachine.insertMoney(2.00);
        List<Object> result = vendingMachine.dispenseSnack();
        double change = (Double) result.get(1);
        assertEquals(1.00, change);
    }

    @Test
    public void testNoSnackSelectedButMoneyInserted() {
        double actual = vendingMachine.insertMoney(1.00);
        List<Object> result = vendingMachine.dispenseSnack();
        assertNull(result.get(0));
        assertEquals(1.00, actual);
    }

    @Test
    public void testHandleRequestWithNoSnackSelected() {
        vendingMachine.insertMoney(1.00);
        Snack snack = vendingMachine.handleRequest();
        assertNull(snack);
    }

    @Test
    public void testSnackRequestWhileDispensingAnotherSnack() {
        vendingMachine.selectSnack("Coke");
        vendingMachine.insertMoney(1.00);
        vendingMachine.dispenseSnack();
        vendingMachine.selectSnack("Pepsi");
        vendingMachine.insertMoney(1.25);
        List<Object> result = vendingMachine.dispenseSnack();
        Snack snack = (Snack) result.get(0);
        assertNotNull(snack);
        assertEquals("Pepsi", snack.getName());
    }
}
