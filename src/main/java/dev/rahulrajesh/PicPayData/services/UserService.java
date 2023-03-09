package dev.rahulrajesh.PicPayData.services;

import dev.rahulrajesh.PicPayData.model.User;
import dev.rahulrajesh.PicPayData.model.Wallet;
import dev.rahulrajesh.PicPayData.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.NoSuchElementException;

//need to do validation soon

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

    public Wallet getWalletByUserCpf(long cpf) throws NoSuchElementException{
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

    public Wallet updateBalanceByUserCpf(long cpf, double amount) {
        User resultUser = userRepository.findById(cpf)
                .orElseThrow(() -> new NoSuchElementException("User with cpf: " + cpf + " not found"));

        Wallet wallet = resultUser.getWallet();

        wallet.setBalance(amount);

        userRepository.save(resultUser);

        return  wallet;
    }

    public Wallet adjustAmountByUserCpf(long cpf, double amount) throws NoSuchElementException{
        User resultUser = userRepository.findById(cpf)
                .orElseThrow(() -> new NoSuchElementException("User with cpf: " + cpf + " not found"));

        Wallet wallet = resultUser.getWallet();

        double balance = wallet.getBalance();

        if (balance + amount < 0) {
            throw new ArithmeticException("Can't adjust by given amount because balance will become negative");
        }

        wallet.setBalance(balance + amount);

        userRepository.save(resultUser);

        return  wallet;
    }

    //amount is positive
    @Transactional
    public String transactAmount(long fromCpf, long toCpf, double amount)
            throws NoSuchElementException, ArithmeticException{
        User fromUser = getUserByCpf(fromCpf);
        User toUser = getUserByCpf(toCpf);

        Wallet fromWallet = fromUser.getWallet();
        Wallet toWallet = toUser.getWallet();

        double fromBalance = fromWallet.getBalance();

        //validate balance
        if (fromBalance - amount < 0) {
            throw new ArithmeticException("Not enough balance");
        }

        //transact amount
        fromWallet.setBalance(fromWallet.getBalance() - amount);
        toWallet.setBalance(toWallet.getBalance() + amount);

        userRepository.save(fromUser);
        userRepository.save(toUser);

        return "transaction success!";
    }
}
