import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Loader {

    private static final int processorCount = ((Runtime.getRuntime().availableProcessors()));
    private static final int regionCount = 200;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Start");
        long start = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(processorCount);
        for (int i = 1; i <= regionCount; i++){
            executorService.submit(new GenerateNumber(i));
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.HOURS);

        System.out.println((System.currentTimeMillis() - start) + " ms");
        System.out.println("End");
    }
}
