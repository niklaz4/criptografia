package com.example.controller;

import com.example.model.PaymentInfo;
import com.example.service.PaymentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentInfoController {
    private final PaymentInfoService service;

    @Autowired
    public PaymentInfoController(PaymentInfoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PaymentInfo> create(@RequestBody PaymentInfo paymentInfo) {
        return ResponseEntity.ok(service.save(paymentInfo));
    }

    @GetMapping
    public ResponseEntity<List<PaymentInfo>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentInfo> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentInfo> update(@PathVariable Long id, @RequestBody PaymentInfo paymentInfo) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        paymentInfo.setId(id);
        return ResponseEntity.ok(service.save(paymentInfo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}