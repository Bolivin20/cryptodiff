package com.cryptodiff.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "symbol", nullable = false)
    private String symbol;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Subscription() {
    }

    public Subscription(String symbol, User user) {
        this.symbol = symbol;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", user=" + user +
                '}';
    }
}
