package dev.wows.buk.JavaDB.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer {

    private long id;
    
    private String name;
    private String address;
    private String email;
    private String phone;

    public Customer() { }
    public Customer(String name, String address,
                    String email, String phone) {
        
        setName(name);
        setAddress(address);
        setEmail(email);
        setPhone(phone);
    }
    public Customer(long id, String name, String address,
                    String email, String phone) {
        
        this(name, address, email, phone);

        setId(id);
    }
    public Customer(ResultSet res) throws SQLException {

        this(
            res.getLong("id"),
            res.getString("name"),
            res.getString("address"),
            res.getString("email"),
            res.getString("phone")
        );
    }

    public long getId() {

        return id;
    }
    public void setId(long id) {

        this.id = id;
    }

    public String getName() {
        
        return name;
    }
    public void setName(String name) {

        this.name = name;
    }

    public String getAddress() {

        return address;
    }
    public void setAddress(String address) {

        this.address = address;
    }

    public String getEmail() {

        return email;
    }
    public void setEmail(String email) {

        this.email = email;
    }

    public String getPhone() {

        return phone;
    }
    public void setPhone(String phone) {

        this.phone = phone;
    }

    @Override
    public String toString() {
        
        return "[" + getId() + "] Customer " + getName() + ":\n"
            + getAddress() + "\n"
            + getEmail() + "\n"
            + getPhone();
    }
}
