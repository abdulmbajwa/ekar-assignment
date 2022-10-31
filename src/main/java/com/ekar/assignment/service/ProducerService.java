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
public class ProducerService {
    final ExecutorService producerExecutor;
    final CounterInformationRepository repository;

    public void increaseProducersCount(int count) {
        for (int i = 0; i < count; i++) {
            producerExecutor.submit(new ProducerCallable());
        }
    }

    class ProducerCallable implements Callable<Void> {

        @Override
        public Void call() throws Exception {
            CounterSingleton counter = CounterSingleton.getInstance();
            if (counter.getCount() == 100 || counter.getCount() == 0) {
                CounterInformation counterInformation = new CounterInformation();
                counterInformation.setCounterValue(counter.getCount());
                counterInformation.setUpdatedAt(LocalDateTime.now());
                repository.save(counterInformation);
                return null;
            }
            while (counter.getCount() < 100 && counter.getCount() > 0) {
                int increasedCount = counter.increaseCounter();
                String threadId = Thread.currentThread().getName();
                log.info("{} has increased counter, current count is {}", threadId, increasedCount);
                Thread.sleep(1000);
            }
            return null;
        }
    }

}

