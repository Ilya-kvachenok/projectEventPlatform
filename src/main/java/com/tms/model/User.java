package com.tms.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private BigDecimal bonusBalance;
    private LocalDateTime created;
    private LocalDateTime updated;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Security security;

    @OneToMany(mappedBy = "organizer")
    List<Event> events = new ArrayList<>();
}
