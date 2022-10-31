package com.ekar.assignment.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "request_information")
public class RequestInformation {
    private Long id;
    private int producerIncreaseCount;
    private int consumerDecreaseCount;
    private LocalDateTime requestReceivedAtTime;
    private String requesterIp;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public int getProducerIncreaseCount() {
        return producerIncreaseCount;
    }

    public void setProducerIncreaseCount(int producerIncreaseCount) {
        this.producerIncreaseCount = producerIncreaseCount;
    }

    public int getConsumerDecreaseCount() {
        return consumerDecreaseCount;
    }

    public void setConsumerDecreaseCount(int consumerDecreaseCount) {
        this.consumerDecreaseCount = consumerDecreaseCount;
    }

    public LocalDateTime getRequestReceivedAtTime() {
        return requestReceivedAtTime;
    }

    public void setRequestReceivedAtTime(LocalDateTime requestReceivedAtTime) {
        this.requestReceivedAtTime = requestReceivedAtTime;
    }

    public String getRequesterIp() {
        return requesterIp;
    }

    public void setRequesterIp(String requesterIp) {
        this.requesterIp = requesterIp;
    }
}
