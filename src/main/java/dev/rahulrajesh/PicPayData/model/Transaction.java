package dev.rahulrajesh.PicPayData.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaction {
    private double amount;
    private long payer;
    private long payee;
}
