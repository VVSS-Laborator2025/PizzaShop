package pizzashop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import pizzashop.repository.PaymentRepository;
import pizzashop.model.PaymentType;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {

    private PaymentService service;
    private PaymentRepository repository;

    @BeforeEach
    void setUp() {
        this.repository = new PaymentRepository();
        this.service = new PaymentService(repository);

        repository.getAll().clear();
        repository.writeAll();
    }

    @Test
    @Order(1)
    @DisplayName("EC Test 1")
    //@Disabled
    void addValidPaymentTest() {
        service.addPayment(1, PaymentType.CARD, 200.5);

        assertEquals(1, service.getPayments().size());

        System.out.println("ADDED");
    }

    @Test
    @Order(2)
    @DisplayName("EC Test 2")
    void addValidPaymentTest2() {
        service.addPayment(4, PaymentType.CARD, 100.75);

        assertEquals(1, service.getPayments().size());

        System.out.println("ADDED");
    }

    @Test
    @Order(3)
    @DisplayName("EC Test 3")
    void addValidPaymentTest3() {
        service.addPayment(6, PaymentType.CARD, 45.7);

        assertEquals(1, service.getPayments().size());

        System.out.println("ADDED");
    }

    @Test
    @Order(4)
    @DisplayName("EC Test 4")
    void addValidPaymentTest4() {
        service.addPayment(8, PaymentType.CARD, 28.9);

        assertEquals(1, service.getPayments().size());

        System.out.println("ADDED");
    }

    @Test
    @Order(5)
    @DisplayName("EC Test 5")
    void addInvalidPayment() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.addPayment(-10, PaymentType.CARD, 800.4);
        }, "Expected addPayment to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Table number must be between 1 and 8"), "Exception message does not match expected text.");

        System.out.println("Table number must be between 1 and 8");}


    @Test
    @Order(6)
    @DisplayName("EC Test 6")
    void addInvalidPayment2() {

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.addPayment(121, PaymentType.CARD, 455);
        }, "Expected addPayment to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Table number must be between 1 and 8"), "Exception message does not match expected text.");

        System.out.println("Table number must be between 1 and 8");}

    @Test
    @Order(7)
    @DisplayName("EC Test 7")
    void addInvalidPayment3() {

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.addPayment(4, PaymentType.CARD, 25000);
        }, "Expected addPayment to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Amount must be between 0 and 1000."), "Exception message does not match expected text.");

        System.out.println("Amount must be between 0 and 1000.");}

    @Test
    @Order(8)
    @DisplayName("EC Test 8")
    void addInvalidPayment4() {

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.addPayment(7, PaymentType.CARD, -7.5);
        }, "Expected addPayment to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Amount must be between 0 and 1000."), "Exception message does not match expected text.");

        System.out.println("Amount must be between 0 and 1000.");
    }

    @Test
    @Order(9)
    @Tag("BVA")
    @DisplayName("BVA Test 1")
    void addValidPaymentTestBVA() {
        service.addPayment(1, PaymentType.CARD, 200.5);

        assertEquals(1, service.getPayments().size());

        System.out.println("ADDED");
    }

    @Test
    @Order(10)
    @DisplayName("BVA Test 2")
    void addInvalidPaymentBVA1() {
        service.addPayment(8, PaymentType.CARD, 28.9);

        assertEquals(1, service.getPayments().size());
        System.out.println("ADDED");
    }

    @Test
    @Order(11)
    @DisplayName("BVA Test 3")
    void addInvalidPaymentBVA2() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.addPayment(-10, PaymentType.CARD, 800.4);
        }, "Expected addPayment to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Table number must be between 1 and 8"), "Exception message does not match expected text.");
        System.out.println("Table number must be between 1 and 8");
    }

    @Test
    @Order(12)
    @DisplayName("BVA Test 4")
    @EnabledOnOs(OS.WINDOWS)
    void addValidPaymentTestBVA3() {
        service.addPayment(1, PaymentType.CARD, 999.99);

        assertEquals(1, service.getPayments().size());

        System.out.println("ADDED");
    }

    @Test
    @Order(13)
    @DisplayName("BVA Test 5")
    @EnabledOnOs(OS.WINDOWS)
    void addInvalidPaymentBVA4() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.addPayment(2, PaymentType.CARD, 0);
        }, "Expected addPayment to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Amount must be between 0 and 1000."), "Exception message does not match expected text.");

        System.out.println("Amount must be between 0 and 1000.");
    }


}