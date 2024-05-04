package VendingMachine.States;

import VendingMachine.VendingMachine;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.*;

public class VendingMachineStateTest {

    private VendingMachine vendingMachine;

    @BeforeEach
    public void setUp() {
        vendingMachine = new VendingMachine();
    }

    @Test
    public void testIdleStateToWaitingStateOnSelectSnack() {
        vendingMachine.selectSnack("Coke");
        assertTrue(vendingMachine.getState() instanceof  WaitingState);
    }

    @Test
    public void testWaitingStateToDispensingStateOnInsertMoney() {
        vendingMachine.selectSnack("Coke");
        vendingMachine.insertMoney(1.00);
        assertTrue(vendingMachine.getState() instanceof DispensingState);
    }

    @Test
    public void testDispensingStateToIdleStateOnDispenseSnack() {
        vendingMachine.selectSnack("Coke");
        vendingMachine.insertMoney(1.00);
        vendingMachine.dispenseSnack();
        assertTrue(vendingMachine.getState() instanceof IdleState);
    }

    @Test
    public void testInsertMoneyInIdleState() {
        double returnedMoney = vendingMachine.insertMoney(1.00);
        assertEquals(1.00, returnedMoney);
        assertTrue(vendingMachine.getState() instanceof IdleState);
    }

    @Test
    public void testSelectSnackInDispensingState() {
        vendingMachine.selectSnack("Coke");
        vendingMachine.insertMoney(1.00);
        vendingMachine.selectSnack("Pepsi");
        assertTrue(vendingMachine.getState() instanceof DispensingState);
    }

    @Test
    public void testInsufficientFunds() {
        vendingMachine.selectSnack("Coke");
        vendingMachine.insertMoney(0.50);
        List<Object> result = vendingMachine.dispenseSnack();
        assertNull(result.get(0));
        assertTrue(vendingMachine.getState() instanceof IdleState);
    }

    @Test
    public void testInsertMoneyWhileDispensing() {
        vendingMachine.selectSnack("Coke");
        vendingMachine.insertMoney(1.00);
        double returnedMoney = vendingMachine.insertMoney(0.50);
        assertEquals(0.50, returnedMoney);
    }
}

