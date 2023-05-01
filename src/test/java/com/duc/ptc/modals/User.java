package com.duc.ptc.modals;

public class User {
    private String userType;
    private String email;
    private String password;

    public String getUserType() {
        return userType;
    }

    public User setUserType(String userType) {
        this.userType = userType;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String toString() {
        return "[User Info]: {\n"
                + "\tuserType: " + userType + "\n"
                + "\temail: " + email + "\n"
                + "\tpassword: " + password + "\n"
                + "}\n";
    }
}
