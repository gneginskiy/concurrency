package concurrency.deferredcallback;

import java.time.Duration;

public class DeferredCallbackApp {
    public static void main(String[] args) {
        DeferredCallbackRunner runner = new DeferredCallbackRunner(1);
        runner.registerTask(Duration.ofSeconds(5), () -> System.out.println("the task is done now"));
        runner.registerTask(Duration.ofSeconds(5), () -> System.out.println("the task is done now"));
        runner.registerTask(Duration.ofSeconds(6), () -> System.out.println("the task is done now"));
        runner.registerTask(Duration.ofSeconds(6), () -> System.out.println("the task is done now"));
        runner.registerTask(Duration.ofSeconds(6), () -> System.out.println("the task is done now"));
        runner.shutdown();
    }
}
