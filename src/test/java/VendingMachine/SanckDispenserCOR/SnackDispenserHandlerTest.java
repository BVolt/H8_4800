package VendingMachine.SanckDispenserCOR;

import static org.junit.jupiter.api.Assertions.*;
import VendingMachine.Snack;
import VendingMachine.SnackDispenserCOR.*;
import org.junit.jupiter.api.*;

public class SnackDispenserHandlerTest {

    private SnackDispenserHandler chain;

    @BeforeEach
    public void setUp() {
        SnackDispenserHandler snickersHandler = new SnickersDispenserHandler(null);
        SnackDispenserHandler kitKatHandler = new KitKatDispenserHandler(snickersHandler);
        SnackDispenserHandler doritosHandler = new DoritosDispenserHandler(kitKatHandler);
        SnackDispenserHandler cheetosHandler = new CheetosDispenserHandler(doritosHandler);
        SnackDispenserHandler pepsiHandler = new PepsiDispenserHandler(cheetosHandler);
        chain = new CokeDispenserHandler(pepsiHandler);
    }

    @Test
    public void testDispenseValidSnackWithExactMoney() {
        Snack result = chain.handleRequest("Coke", 1.00);
        assertNotNull(result);
        assertEquals("Coke", result.getName());
        assertEquals(0.00, result.getPrice(), 0.01);
    }

    @Test
    public void testDispenseValidSnackWithInsufficientMoney() {
        Snack result = chain.handleRequest("Coke", 0.50);
        assertNull(result);
    }

    @Test
    public void testDispenseOutOfStockSnack() {
        for (int i = 0; i < 9; i++) {
            chain.handleRequest("Coke", 1.00);
        }
        Snack result = chain.handleRequest("Coke", 1.00);
        assertNull(result);
    }

    @Test
    public void testDispenseNonExistentSnack() {
        Snack result = chain.handleRequest("Water", 1.00);
        assertNull(result);
    }

    @Test
    public void testChainPassingToNextHandler() {
        Snack result = chain.handleRequest("Pepsi", 1.25);
        assertNotNull(result);
        assertEquals("Pepsi", result.getName());
        assertEquals(0.00, result.getPrice(), 0.01);
    }

    @Test
    public void testMultipleSnacks() {
        Snack coke = chain.handleRequest("Coke", 1.00);
        Snack pepsi = chain.handleRequest("Pepsi", 1.25);
        Snack cheetos = chain.handleRequest("Cheetos", 1.50);

        assertNotNull(coke);
        assertNotNull(pepsi);
        assertNotNull(cheetos);
        assertEquals("Coke", coke.getName());
        assertEquals("Pepsi", pepsi.getName());
        assertEquals("Cheetos", cheetos.getName());
    }
}
