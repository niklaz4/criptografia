package com.example.service;

import com.example.model.PaymentInfo;
import com.example.repository.PaymentInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentInfoService {
    private final PaymentInfoRepository repository;

    @Autowired
    public PaymentInfoService(PaymentInfoRepository repository) {
        this.repository = repository;
    }

    public PaymentInfo save(PaymentInfo paymentInfo) {
        return repository.save(paymentInfo);
    }

    public List<PaymentInfo> findAll() {
        return repository.findAll();
    }

    public Optional<PaymentInfo> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}