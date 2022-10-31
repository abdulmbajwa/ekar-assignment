package com.ekar.assignment.controller;

import com.ekar.assignment.CounterSingleton;
import com.ekar.assignment.model.RequestInformation;
import com.ekar.assignment.repository.RequestInformationRepository;
import com.ekar.assignment.service.ConsumerService;
import com.ekar.assignment.service.MonitorService;
import com.ekar.assignment.service.ProducerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@RestController
@Slf4j
@AllArgsConstructor
public class EndpointController {
    final ProducerService producerService;
    final ConsumerService consumerService;
    final MonitorService monitorService;
    final RequestInformationRepository requestInformationRepository;

    @GetMapping(value = "/update-threads")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateThreads(@RequestParam("incrementProducer") @Min(0) int incrementProducer,
                              @RequestParam("incrementConsumer") @Min(0) int incrementConsumer,
                              HttpServletRequest request) {
        log.info("Producer threads increased by count of {}\n Consumer Threads increased by count of {}", incrementProducer, incrementConsumer);
        producerService.increaseProducersCount(incrementProducer);
        consumerService.increaseConsumerCount(incrementConsumer);
        monitorService.runMonitor();
        RequestInformation requestInfo = new RequestInformation();
        requestInfo.setRequesterIp(request.getRemoteAddr());
        requestInfo.setRequestReceivedAtTime(LocalDateTime.now());
        requestInfo.setConsumerDecreaseCount(incrementConsumer);
        requestInfo.setProducerIncreaseCount(incrementProducer);
        requestInformationRepository.save(requestInfo);
    }

    @GetMapping(value = "/set-counter")
    @ResponseStatus(HttpStatus.OK)
    public void setCounterValue(@RequestParam("value") @Min(0) int value) {
        log.info("Counter value has been update to {}", value);
        CounterSingleton.getInstance().reset(0);
    }

    @GetMapping(value = "/reset-count")
    @ResponseStatus(HttpStatus.OK)
    public void resetCount() {
        log.info("Counter has been reset to initial value of 50");
        CounterSingleton.getInstance().reset(50);
    }
}
