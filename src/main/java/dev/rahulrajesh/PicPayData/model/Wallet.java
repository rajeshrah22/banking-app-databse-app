package dev.rahulrajesh.PicPayData.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Table("wallet")
@Data
@AllArgsConstructor
public class Wallet {
    @Id
    private Integer id;
    private Double balance;
    private Timestamp created = null;
    private Timestamp txdate = null;

    public Wallet() {
        this.created = new Timestamp(System.currentTimeMillis());
    }
}
