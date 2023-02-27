package dev.rahulrajesh.PicPayData.model;

import org.springframework.data.annotation.Id;

public class User {

    @Id
    private Integer id;
    private String fName;
    private String lName;
    private Wallet wallet;

    private String userType;

    public User(String fName, String lName, Wallet wallet, String userType) {
        this.fName = fName;
        this.lName = lName;
        this.wallet = wallet;
        this.userType = userType;
    }

    public User() {

    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", wallet=" + wallet +
                ", userType='" + userType + '\'' +
                '}';
    }
}
