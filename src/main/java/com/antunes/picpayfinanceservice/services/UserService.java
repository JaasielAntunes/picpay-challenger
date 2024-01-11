package com.antunes.picpayfinanceservice.services;

import com.antunes.picpayfinanceservice.domain.user.User;
import com.antunes.picpayfinanceservice.domain.user.UserType;
import com.antunes.picpayfinanceservice.dtos.UserDTO;
import com.antunes.picpayfinanceservice.repositories.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRespository repo;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if (sender.getUserType() == UserType.MERCHANT) {
            throw new Exception("Usuário do tipo lojista não está autorizado a realizar transações!");
        }

        if (sender.getBalance().compareTo(amount) <= 0) {
            throw new Exception("Saldo insuficiente!");
        }
    }

    public User findById(Long id) throws Exception {
        return repo.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado!"));
    }

    public User createUser(UserDTO data) {
        User newUser = new User(data);
        this.save(newUser);
        return newUser;
    }

    public List<User> getAllUsers() {
        return this.repo.findAll();
    }

    @Transactional
    public void save(User user) {
        repo.save(user);
    }
}
