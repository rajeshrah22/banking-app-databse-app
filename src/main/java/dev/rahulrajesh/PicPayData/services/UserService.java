package dev.rahulrajesh.PicPayData.services;

import dev.rahulrajesh.PicPayData.model.User;
import dev.rahulrajesh.PicPayData.model.Wallet;
import dev.rahulrajesh.PicPayData.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //user might exist already
    public User createUser(User user) {
        return userRepository.save(user);
    }

    //user with cpf possibly not found
    public User addWallet(long cpf) throws  NoSuchElementException{
        User resultUser = userRepository.findById(cpf)
                .orElseThrow(() -> new NoSuchElementException("User with cpf: " + cpf + " not found"));

        resultUser.setWallet(new Wallet());

        return userRepository.save(resultUser);
    }

    public User getUserByCpf(long cpf) throws NoSuchElementException {
        User resultUser = userRepository.findById(cpf)
                .orElseThrow(() -> new NoSuchElementException("User with cpf: " + cpf + " not found"));

        return resultUser;
    }

    public Wallet getWalletByUserCpf(long cpf) {
        User resultUser = userRepository.findById(cpf)
                .orElseThrow(() -> new NoSuchElementException("User with cpf: " + cpf + " not found"));

        Wallet wallet = resultUser.getWallet();

        return wallet;
    }

    public User deleteUserByCpf(long cpf) {
        User resultUser = userRepository.findById(cpf)
                .orElseThrow(() -> new NoSuchElementException("User with cpf: " + cpf + " not found"));

        userRepository.delete(resultUser);

        return resultUser;
    }
}
