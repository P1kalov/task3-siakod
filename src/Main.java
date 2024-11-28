import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        evaluatePriorityQueues(1000, 6000, 1000);
    }

    private static void evaluatePriorityQueues(int minSize, int maxSize, int step) {
        Random random = new Random();
        List<Integer> sizes = new ArrayList<>();
        List<Double> heapInsertTimes = new ArrayList<>();
        List<Double> sortedInsertTimes = new ArrayList<>();
        List<Double> heapExtractTimes = new ArrayList<>();
        List<Double> sortedExtractTimes = new ArrayList<>();

        for (int queueSize = minSize; queueSize <= maxSize; queueSize += step) {
            sizes.add(queueSize);

            PriorityQueue heapQueue = new PriorityQueueBinaryHeap();
            PriorityQueue sortedQueue = new ArrayPriorityQueue();

            int finalQueueSize = queueSize;
            double heapInsertTime = measureOperationTime(() -> {
                for (int i = 0; i < finalQueueSize; i++) {
                    int value = random.nextInt(1000);
                    heapQueue.insert(value, value);
                }
            });

            int finalQueueSize1 = queueSize;
            double sortedInsertTime = measureOperationTime(() -> {
                for (int i = 0; i < finalQueueSize1; i++) {
                    int value = random.nextInt(1000);
                    sortedQueue.insert(value, value);
                }
            });

            heapInsertTimes.add(heapInsertTime);
            sortedInsertTimes.add(sortedInsertTime);
            double heapExtractTime = measureOperationTime(heapQueue::extractMax);
            double sortedExtractTime = measureOperationTime(sortedQueue::extractMax);

            heapExtractTimes.add(heapExtractTime);
            sortedExtractTimes.add(sortedExtractTime);

            System.out.println("Размер очереди: " + queueSize);
            System.out.println("Время вставки (бинарная куча): " + heapInsertTime + " мс");
            System.out.println("Время вставки (сортированный массив): " + sortedInsertTime + " мс");
            System.out.println("Время извлечения (бинарная куча): " + heapExtractTime + " мс");
            System.out.println("Время извлечения (сортированный массив): " + sortedExtractTime + " мс");
            System.out.println();
        }
    }
    private static double measureOperationTime(Runnable operation) {
        long startTime = System.nanoTime();
        operation.run();
        long endTime = System.nanoTime();
        return (double) (endTime - startTime) / 1_000_000; // Время в миллисекундах
    }
}
