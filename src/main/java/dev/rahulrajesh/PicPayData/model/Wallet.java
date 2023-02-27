package dev.rahulrajesh.PicPayData.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Wallet {
    @Id
    private Integer id;
    private Double balance;
    private LocalDateTime createdOn = null;
    private LocalDateTime lastTransactedOn = null;

    public Wallet() {
        this.createdOn = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getLastTransactedOn() {
        return lastTransactedOn;
    }

    public void setLastTransactedOn(LocalDateTime lastTransactedOn) {
        this.lastTransactedOn = lastTransactedOn;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", balance=" + balance +
                ", createdOn=" + createdOn +
                ", lastTransactedOn=" + lastTransactedOn +
                '}';
    }
}
