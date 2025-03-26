package pizzashop.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import pizzashop.model.PaymentType;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pizzashop.service.PaymentService;


public class PaymentAlert implements PaymentOperation {

    private static final Logger logger = LoggerFactory.getLogger(PaymentAlert.class);

    private PaymentService service;
    private static final String SEPARATOR = "--------------------------";

    public PaymentAlert(PaymentService service){
        this.service=service;
    }

    @Override
    public void cardPayment() {
        logger.info(SEPARATOR);
        logger.info("Paying by card...");
        logger.info("Please insert your card!");
        logger.info(SEPARATOR);
    }
    @Override
    public void cashPayment() {
        logger.info(SEPARATOR);
        logger.info("Paying cash...");
        logger.info("Please show the cash...!");
        logger.info(SEPARATOR);
    }
    @Override
    public void cancelPayment() {
        logger.info(SEPARATOR);
        logger.info("Payment choice needed...");
        logger.info(SEPARATOR);
    }
      public void showPaymentAlert(int tableNumber, double totalAmount ) {
        Alert paymentAlert = new Alert(Alert.AlertType.CONFIRMATION);
        paymentAlert.setTitle("Payment for Table "+tableNumber);
        paymentAlert.setHeaderText("Total amount: " + totalAmount);
        paymentAlert.setContentText("Please choose payment option");
        ButtonType cardPayment = new ButtonType("Pay by Card");
        ButtonType cashPayment = new ButtonType("Pay Cash");
        ButtonType cancel = new ButtonType("Cancel");
        paymentAlert.getButtonTypes().setAll(cardPayment, cashPayment, cancel);
        Optional<ButtonType> result = paymentAlert.showAndWait();
        if (result.isPresent() && result.get() == cardPayment) {
            cardPayment();
            service.addPayment(tableNumber, PaymentType.CARD,totalAmount);
        } else if (result.isPresent() && result.get() == cashPayment) {
            cashPayment();
            service.addPayment(tableNumber, PaymentType.CASH,totalAmount);
        } else if (result.isPresent() && result.get() == cancel) {
             cancelPayment();
        } else {
            cancelPayment();
        }
    }
}