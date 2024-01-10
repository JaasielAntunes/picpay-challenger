package com.antunes.picpayfinanceservice.services;

import com.antunes.picpayfinanceservice.domain.transaction.Transaction;
import com.antunes.picpayfinanceservice.domain.user.User;
import com.antunes.picpayfinanceservice.domain.user.UserType;
import com.antunes.picpayfinanceservice.dtos.TransactionDTO;
import com.antunes.picpayfinanceservice.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository repo;

    @Autowired
    private RestTemplate restTemplate;

    public void criateTransaction(TransactionDTO transaction) throws Exception {
        User sender = this.userService.findById(transaction.senderId());
        User receiver = this.userService.findById(transaction.receiverId());

        userService.validateTransaction(sender, transaction.value());

        boolean isAuthorized = this.authorizeTransaction(sender, transaction.value());
        if (!this.authorizeTransaction(sender, transaction.value())) {
            throw new Exception("Transação não autorizada!");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        this.repo.save(newTransaction);
        this.userService.save(sender);
        this.userService.save(receiver);
    }

    public boolean authorizeTransaction(User sender, BigDecimal value) {
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6", Map.class);

        if (authorizationResponse.getStatusCode() == HttpStatus.OK) {
            String message = authorizationResponse.getBody().get("message").toString();
            return "Autorizado".equalsIgnoreCase(message);
        } else return false;
    }
}
