package yashv.demo.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users")
public class User {
    // CAUTION! in a sensible project DTO and entity are two different classes for different purposes
    @Id
    @Column(name = "username")
    private String uname;

    @Column(nullable = false)
    private String password;

    public String getUname() {
        return uname;
    }

    public User setUname(String uname) {
        this.uname = uname;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "uname='" + uname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
