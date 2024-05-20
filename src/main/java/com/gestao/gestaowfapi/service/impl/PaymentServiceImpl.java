package com.gestao.gestaowfapi.service.impl;

import com.gestao.gestaowfapi.dto.PaymentDTO;
import com.gestao.gestaowfapi.enums.PaymentStatus;
import com.gestao.gestaowfapi.model.CreditCard;
import com.gestao.gestaowfapi.model.Customer;
import com.gestao.gestaowfapi.model.Order;
import com.gestao.gestaowfapi.model.Payment;
import com.gestao.gestaowfapi.repository.PaymentRepository;
import com.gestao.gestaowfapi.service.CreditCardService;
import com.gestao.gestaowfapi.service.CustomerService;
import com.gestao.gestaowfapi.service.OrderService;
import com.gestao.gestaowfapi.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final CustomerService customerService;
    private final OrderService orderService;
    private final CreditCardService creditCardService;
    private final PaymentRepository paymentRepository;

    @Override
    public Mono<Payment> process(PaymentDTO paymentDto) {
        Mono<Customer> customerMono = customerService.findById(paymentDto.customerId());
        Mono<Order> orderMono = orderService.findById(paymentDto.orderId());
        return Mono.zip(customerMono, orderMono)
                .flatMap(tuple -> {
                    Customer customer = tuple.getT1();
                    Order order = tuple.getT2();
                    return creditCardService.findByNumber(paymentDto.creditCard().number())
                            .flatMap(creditCard -> authorizePayment(customer, order, creditCard))
                            .onErrorResume(error -> authorizePaymentWithNewCreditCard(paymentDto, customer, order));
                });
    }

    private Mono<Payment> authorizePaymentWithNewCreditCard(PaymentDTO paymentDto, Customer customer, Order order) {
        return creditCardService.create(paymentDto.creditCard(), customer)
                .flatMap(creditCard -> savePayment(customer, order, creditCard, PaymentStatus.APPROVED));
    }

    private Mono<Payment> authorizePayment(Customer customer, Order order, CreditCard creditCard) {
        if (creditCard.getCustomer().getId().equals(customer.getId())
                || creditCard.getDocumentNumber().equals(customer.getCpf())) {
            return savePayment(customer, order, creditCard, PaymentStatus.APPROVED);
        } else {
            return savePayment(customer, order, creditCard, PaymentStatus.DISAPPROVED);
        }
    }

    private Mono<Payment> savePayment(Customer customer, Order order, CreditCard creditCard, PaymentStatus status) {
        Payment payment = Payment.builder()
                .dtRegistedPayment(LocalDateTime.now())
                .order(order)
                .customer(customer)
                .creditCard(creditCard)
                .status(status)
                .build();
        return paymentRepository.save(payment);
    }
}
