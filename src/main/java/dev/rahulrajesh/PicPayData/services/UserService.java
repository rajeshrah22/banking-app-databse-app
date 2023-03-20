package dev.rahulrajesh.PicPayData.services;

import dev.rahulrajesh.PicPayData.model.User;
import dev.rahulrajesh.PicPayData.model.Wallet;
import dev.rahulrajesh.PicPayData.repository.LoginRepository;
import dev.rahulrajesh.PicPayData.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class provides business logic for managing user data in a PicPayData app.
 * It defines methods for CRUD operations on users and wallets, and for transactions between users.
 * A UserRepository interacts with a MySQL database, and the service includes exception handling and validation.
 * The class uses Spring's @Transactional annotation for data consistency during transactions.
 */

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates a new User with an empty Wallet and saves it to the database.
     * Returns the newly created User.
     */
    public User createUser(User user) {
        Wallet wallet = new Wallet();
        wallet.setBalance(0.0);
        user.setWallet(wallet);
        return userRepository.save(user);
    }

    /**
     * Adds an empty Wallet to the User with the specified CPF.
     * Returns the User with the added Wallet.
     * Throws a NoSuchElementException if no User is found with the specified CPF.
     */
    public User addWallet(long cpf) throws  NoSuchElementException{
        User resultUser = userRepository.findById(cpf)
                .orElseThrow(() -> new NoSuchElementException("User with cpf: " + cpf + " not found"));

        resultUser.setWallet(new Wallet());

        return userRepository.save(resultUser);
    }

    /**
     * Retrieves a User object by CPF.
     * Returns the User object if found.
     * Throws a NoSuchElementException if no User is found with the specified CPF.
     */
    public User getUserByCpf(long cpf) throws NoSuchElementException {
        return userRepository.findById(cpf)
                .orElseThrow(() -> new NoSuchElementException("User with cpf: " + cpf + " not found"));
    }

    /**
     * Retrieves a User object by email.
     * Returns the User object if found.
     * Throws a NoSuchElementException if no User is found with the specified email.
     */
    public User getUserByEmail(String email) throws NoSuchElementException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new NoSuchElementException();
        }

        return user;
    }

    /**
     * Retrieves the Wallet object for the User with the specified CPF.
     * Returns the Wallet object.
     * Throws a NoSuchElementException if no User is found with the specified CPF.
     *
     * @param cpf the CPF of the User whose Wallet is to be retrieved
     * @return the Wallet object for the User with the specified CPF
     * @throws NoSuchElementException if no User is found with the specified CPF
     */
    public Wallet getWalletByUserCpf(long cpf) throws NoSuchElementException{
        User resultUser = userRepository.findById(cpf)
                .orElseThrow(() -> new NoSuchElementException("User with cpf: " + cpf + " not found"));

        return resultUser.getWallet();
    }

    /**
     * Deletes the User with the specified CPF from the database.
     * Returns the deleted User object.
     * Throws a NoSuchElementException if no User is found with the specified CPF.
     */
    public User deleteUserByCpf(long cpf) {
        User resultUser = userRepository.findById(cpf)
                .orElseThrow(() -> new NoSuchElementException("User with cpf: " + cpf + " not found"));

        userRepository.delete(resultUser);

        return resultUser;
    }

    /**
     * Updates the balance of the Wallet object for the User with the specified CPF.
     * Returns the updated Wallet object.
     * Throws a NoSuchElementException if no User is found with the specified CPF.
     */
    public Wallet updateBalanceByUserCpf(long cpf, double amount) {
        User resultUser = userRepository.findById(cpf)
                .orElseThrow(() -> new NoSuchElementException("User with cpf: " + cpf + " not found"));

        Wallet wallet = resultUser.getWallet();

        wallet.setBalance(amount);

        userRepository.save(resultUser);

        return  wallet;
    }

    /**
     * Adjusts the balance of the Wallet object for the User with the specified CPF by the given amount.
     * Returns the updated Wallet object.
     * Throws a NoSuchElementException if no User is found with the specified CPF.
     * Throws an ArithmeticException if adjusting the balance would result in a negative balance.
     */
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

    /**
     * Retrieves a list of User objects whose first or last name contains the specified substring.
     * Returns a list of up to numResults matching User objects, ordered by first name then last name.
     */
    public List<User> getUsersWithNameContaining(String nameSubstring, int numResults) {
        Sort sort = Sort.by("fname").ascending().and(Sort.by("lname").ascending());
        Pageable pageable = PageRequest.of(0, numResults, sort);

        return userRepository
                .findByFnameIsContainingIgnoreCaseOrLnameIsContainingIgnoreCase
                        (nameSubstring, nameSubstring, pageable);
    }

    /**
     * Performs a transaction between two users with the specified CPFs and amount.
     * Updates the balances of the Wallet objects for the sender and receiver.
     * Returns a success message upon completion.
     * Throws a NoSuchElementException if no User is found with the specified CPF.
     * Throws an ArithmeticException if the sender has an insufficient balance for the transaction.
     */
    @Transactional
    public String transactAmount(long fromCpf, long toCpf, double amount)
            throws NoSuchElementException, ArithmeticException {
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
