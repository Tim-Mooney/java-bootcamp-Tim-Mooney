public class PerformanceTest {

    private static class SampleObject {
        private final int value;
        private final byte[] data = new byte[64];

        SampleObject(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        System.out.println("===== Performance Measurement =====");
        MemoryMonitor.printMemoryReport("Start");

        int[] objectCounts = {10, 100, 1_000, 100_000, 1_000_000};

        System.out.println();
        System.out.printf("%-12s %-14s %-18s%n", "Objects", "Used Memory", "Execution Time");
        System.out.println("--------------------------------------------------");

        for (int count : objectCounts) {
            runAllocationTest(count);
        }

        System.out.println();
        System.out.println("Additional measurements:");
        measureLoopExecution();
        measureArrayAllocation();
        measureLargeByteArray();
    }

    private static void runAllocationTest(int count) {
        MemoryMonitor.triggerGarbageCollection();
        long memoryBefore = MemoryMonitor.getUsedMemoryBytes();
        long start = System.nanoTime();
        SampleObject[] objects = new SampleObject[count];

        for(int i = 0; i < count; i ++){
            objects[i] = new SampleObject(i);
        }

        long elapsed = System.nanoTime() - start;
        long memoryAfter = MemoryMonitor.getUsedMemoryBytes();
        long memoryUsed = memoryAfter - memoryBefore;
        System.out.printf("%-12d %-14s %-18s%n", count, MemoryMonitor.toMegabytes(memoryUsed) + "MB", elapsed + "ms");

        objects = null;
        MemoryMonitor.triggerGarbageCollection();
    }

    private static void measureLoopExecution() {
        long start = System.nanoTime();
        long sum = 0;

        for(int i = 0; i < 10_000_000; i++){
            sum += i;
        }

        long elapsed = System.nanoTime() - start;
        System.out.println("Loop execution (10M iterations) : "+elapsed+" ms | sum = "+sum+".");
    }

    private static void measureArrayAllocation() {
        long start = System.nanoTime();
        int[] arr = new int[1_000_000];

        for(int i = 0; i < 1_000_000; i++){
            arr[i] = i;
        }

        long elapsed = System.nanoTime() - start;
        System.out.println("int[1,000,000] allocation: "+elapsed+" ms");

    }

    private static void measureLargeByteArray() {
        MemoryMonitor.printMemoryReport("Before Large byte[]");

        byte[] arr = new byte[10485760];    //apparently this is more common? idk clean 10_000_000 if not

        MemoryMonitor.printMemoryReport("After Large byte[]");

        arr = null;
        MemoryMonitor.triggerGarbageCollection();

        MemoryMonitor.printMemoryReport("After Releasing");

    }
}