package com.ekar.assignment.service;

import com.ekar.assignment.CounterSingleton;
import com.ekar.assignment.model.CounterInformation;
import com.ekar.assignment.repository.CounterInformationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;

@Service
@Slf4j
@AllArgsConstructor
public class MonitorService {
    final ThreadPoolTaskExecutor monitorExecutor;
    final CounterInformationRepository repository;

    public void runMonitor() {
        if (monitorExecutor.getActiveCount() == 0) {
            monitorExecutor.submit(new MonitorCallable());
        }
    }

    class MonitorCallable implements Callable<Void> {
        @Override
        public Void call() throws InterruptedException {
            CounterSingleton counter = CounterSingleton.getInstance();
            while (true) {
                if (counter.getCount() == 100 || counter.getCount() == 0) {
                    CounterInformation counterInformation = new CounterInformation();
                    counterInformation.setCounterValue(counter.getCount());
                    counterInformation.setUpdatedAt(LocalDateTime.now());
                    repository.save(counterInformation);
                    break;
                }
                Thread.sleep(500);
            }
            return null;
        }
    }
}
