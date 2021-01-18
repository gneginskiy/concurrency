package deferredcallback;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DeferredCallbackRunner {
    private ScheduledExecutorService executorService;

    public DeferredCallbackRunner(int poolSize) {
        this.executorService = Executors.newScheduledThreadPool(poolSize);
    }

    public void registerTask(Duration delay, Runnable task) {
        this.executorService.schedule(task, delay.toMillis(), TimeUnit.MILLISECONDS);
    }
}
