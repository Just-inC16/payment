package com.tcs.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
	private PaymentRepository paymentRepository;

	public PaymentController(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}

	@PostMapping
	public ResponseEntity<?> makePayment(@RequestBody Payment payment) {
		return ResponseEntity.ok(paymentRepository.save(payment));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getPaymentById(@PathVariable Long id) {
		Payment paymentById = paymentRepository.getReferenceById(id);
		Payment paymentDto = new Payment(paymentById.getId(), paymentById.getCustomerId(), paymentById.getAmount());
		return ResponseEntity.ok(paymentDto);
	}
}