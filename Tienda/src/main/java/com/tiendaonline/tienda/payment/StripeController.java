package com.tiendaonline.tienda.payment;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.tiendaonline.tienda.orders.dto.OrderResponseDTO;
import com.tiendaonline.tienda.orders.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/payments/stripe")
public class StripeController {

    private final StripeService service;
    private final OrderService oService;

    public StripeController(StripeService service,
                            OrderService oService
    ) {
        this.service = service;
        this.oService = oService;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createPayment(@RequestParam BigDecimal amount) throws StripeException {
        PaymentIntent intent = service.createPaymentIntent(amount, "eur");

        Map<String, String> response = new HashMap<>();
        response.put("clientSecret", intent.getClientSecret());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/pay/{orderId}")
    public ResponseEntity<Map<String, String>> payOrder(@PathVariable Long orderId) throws StripeException {
        OrderResponseDTO order = oService.getOrderById(orderId);
        PaymentIntent intent = service.createPaymentIntent(order.getTotal(), "EUR");

        Map<String, String> response = new HashMap<>();
        response.put("clientSecret", intent.getClientSecret());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/test")
    public ResponseEntity<String> createTestPayment(@RequestParam BigDecimal amount) throws Exception {
        String clientSecret = service.createPaymentIntent(amount, "usd");
        return ResponseEntity.ok(clientSecret);
    }

}
