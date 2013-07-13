package com.aripd.account.model;

import javax.persistence.Column;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class ProfileForm {

    @Column(nullable = false, unique = false)
    private String password;
    @NotBlank
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
