import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        var threads = new ArrayList<Thread>();
        var count = new AtomicLong(0);

        long start = System.currentTimeMillis();
        for (var i = 0; i < 1_000_000; i++) {
            var t = Thread.startVirtualThread(() -> {
                count.incrementAndGet();
            });
            threads.add(t);
        }

        for (Thread t : threads) {
            t.join();
        }
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("Total counted: "+count.get() +" time: "+timeElapsed);
    }

}
