package pizzashop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PaymentServiceTestGetTotalAmount {

    private PaymentRepository paymentRepository;
    private PaymentService service;

    @BeforeEach
    void setUp() {
        this.paymentRepository = new PaymentRepository();
        this.service = new PaymentService(paymentRepository);

        paymentRepository.getAll().clear();
        paymentRepository.writeAll();
    }

    @Test
    void getTotalAmountTest1() {
        paymentRepository.add(new Payment(5, PaymentType.CASH, 13.0f));
        double result = service.getTotalAmount(PaymentType.CASH);
        assertEquals(13.0f, result);
    }

    @Test
    void getTotalAmountTest2() {
        double result = service.getTotalAmount(PaymentType.CASH);
        assertEquals(0.0f, result);
    }

    @Test
    void getTotalAmountTest3() {
        paymentRepository = Mockito.mock(PaymentRepository.class);
        when(paymentRepository.getAll()).thenReturn(null);

        service = new PaymentService(paymentRepository);
        double result = service.getTotalAmount(PaymentType.CASH);

        assertEquals(0.0f, result);
    }

    @Test
    void getTotalAmountTest4() {
        paymentRepository.add(new Payment(1, PaymentType.CARD, 12.0f));
        paymentRepository.add(new Payment(2, PaymentType.CASH, 13.0f));
        paymentRepository.add(new Payment(3, PaymentType.CARD, 12.0f));
        double result = service.getTotalAmount( PaymentType.CASH);
        assertEquals(13.0f, result);
    }

    @Test
    void getTotalAmountTest5() {
        paymentRepository.add(new Payment(5, PaymentType.CASH, 13.0f));
        double result = service.getTotalAmount(PaymentType.CARD);
        assertEquals(0.0f, result);
    }
}
