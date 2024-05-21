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
        // Verificar se o objeto paymentDto.creditCard() é nulo
        if (paymentDto.creditCard() != null) {
            Mono<Customer> customerMono = customerService.findById(paymentDto.customerId());
            Mono<Order> orderMono = orderService.findById(paymentDto.orderId());
            return Mono.zip(customerMono, orderMono, (customer, order) ->
                    creditCardService.findByNumber(paymentDto.creditCard().number())
                            .flatMap(creditCard ->
                                    authorizePayment(customer, order, creditCard)
                            ).onErrorResume(error ->
                                    authorizePaymentWithNewCreditCard(paymentDto, customer, order)
                            )
            ).flatMap(paymentMono -> paymentMono);
        } else {
            // Lógica para lidar com a situação em que o cartão de crédito é nulo
            // Por exemplo, lançar uma exceção ou retornar um Mono vazio
            return Mono.error(new IllegalArgumentException("O cartão de crédito não pode ser nulo."));
        }
    }

    private Mono<Payment> authorizePaymentWithNewCreditCard(PaymentDTO paymentDto, Customer customer, Order order) {
        return creditCardService.create(paymentDto.creditCard(), customer)
                .flatMap(creditCard ->
                        savePayment(customer, order, creditCard, PaymentStatus.APPROVED)
                );
    }

    private Mono<Payment> authorizePayment(Customer customer, Order order, CreditCard creditCard) {
        if (creditCard.getCustomerId().equals(customer.getId())
                || creditCard.getDocumentNumber().equals(customer.getCpf())) {
            return savePayment(customer, order, creditCard, PaymentStatus.APPROVED);
        } else {
            return savePayment(customer, order, creditCard, PaymentStatus.DISAPPROVED);
        }
    }

    private Mono<Payment> savePayment(Customer customer, Order order, CreditCard creditCard, PaymentStatus status) {
        var paymentBuilder = Payment.builder();
        paymentBuilder
                .dtRegistedPayment(LocalDateTime.now())
                .orderId(order.getId())
                .customerId(customer.getId())
                .creditCardId(creditCard.getId())
                .status(status);
        return paymentRepository.save(paymentBuilder.build());
    }
}
