package pizzashop.service;

import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;
import pizzashop.validator.PaymentValidator;

import java.util.List;

public class PaymentService {

    private final PaymentRepository payRepo;

    public PaymentService(PaymentRepository payRepo){
        this.payRepo=payRepo;
    }

    public List<Payment> getPayments(){return payRepo.getAll(); }

    public void addPayment(int table, PaymentType type, double amount){
        PaymentValidator.validate(table, type, amount);
        Payment payment= new Payment(table, type, amount);
        payRepo.add(payment);}


    public double getTotalAmount(PaymentType type){
        return getTotalAmount(getPayments(),type);
    }

    public double getTotalAmount(List<Payment> l, PaymentType type){
        double total=0.0f;
        if (l==null)
            return total;
        if (l.isEmpty())
            return total;
        for (Payment p:l){
            if (p.getType().equals(type))
                total+=p.getAmount();}
        return total;}

}