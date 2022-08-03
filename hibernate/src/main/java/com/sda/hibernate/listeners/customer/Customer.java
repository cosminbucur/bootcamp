package com.sda.hibernate.listeners.customer;

import com.sda.hibernate.listeners.history.CustomerHistoryListener;

import javax.persistence.*;

@Entity
@Table(name = "customer")
// needed to react to listeners
@EntityListeners(CustomerHistoryListener.class)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    public Customer() {
    }

    public Customer(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
