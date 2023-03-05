package dev.rahulrajesh.PicPayData.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private long cpf;
    private String fname;
    private String lname;
    private String email;
    private Wallet wallet;
    private String usertype;
}
