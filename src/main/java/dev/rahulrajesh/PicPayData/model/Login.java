package dev.rahulrajesh.PicPayData.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("login")
@Data
@AllArgsConstructor
public class Login {
    @Id
    private String username;
    private String password;
    private long user;
}
