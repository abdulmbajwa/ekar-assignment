package com.ekar.assignment.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "counter_information")
public class CounterInformation {
    Long id;
    private int counterValue;
    private LocalDateTime updatedAt;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public int getCounterValue() {
        return counterValue;
    }

    public void setCounterValue(int counterValue) {
        this.counterValue = counterValue;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
