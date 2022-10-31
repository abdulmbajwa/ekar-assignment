package com.ekar.assignment.service;

import com.ekar.assignment.CounterSingleton;
import com.ekar.assignment.model.CounterInformation;
import com.ekar.assignment.repository.CounterInformationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

@Service
@AllArgsConstructor
@Slf4j
public class ConsumerService {
    final ExecutorService consumerExecutor;
    final CounterInformationRepository repository;

    public void increaseConsumerCount(int count) {
        for (int i = 0; i < count; i++) {
            consumerExecutor.submit((new ConsumerCallable()));
        }
    }

    public void stopAllConsumers() {
        consumerExecutor.shutdown();
        log.info("Stopping all consumer threads!");
    }

    class ConsumerCallable implements Callable<Void> {
        @Override
        public Void call() throws Exception {
            CounterSingleton counter = CounterSingleton.getInstance();
            if (counter.getCount() == 100 || counter.getCount() == 0) {
                CounterInformation counterInformation = new CounterInformation();
                counterInformation.setCounterValue(counter.getCount());
                counterInformation.setUpdatedAt(LocalDateTime.now());
                repository.save(counterInformation);
            }
            while (counter.getCount() < 100 && counter.getCount() > 0) {
                int increasedCount = counter.decreaseCounter();
                String threadId = Thread.currentThread().getName();
                log.info("{} has decreased counter, current count is {}", threadId, increasedCount);
                Thread.sleep(1000);
            }
            return null;
        }
    }

}

