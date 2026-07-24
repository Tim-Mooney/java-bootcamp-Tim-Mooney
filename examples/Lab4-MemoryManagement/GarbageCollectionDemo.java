public class GarbageCollectionDemo {

    private static class DemoObject {
        private final String label;
        private final byte[] payload = new byte[128];

        DemoObject(String label) {
            this.label = label;
        }
    }

    public static void main(String[] args) {
        System.out.println("===== Garbage Collection Demonstration =====");
        long startTime = System.nanoTime();

        MemoryMonitor.printMemoryReport("Before Allocation");

        DemoObject[] objects = new DemoObject[100000];
        System.out.println("Creating Objects...");
        int objCreated = 0;
        for(int i = 0; i < objects.length; i++){
            objects[i] = new DemoObject("Object-" + i);
            objCreated++;
        }
        System.out.println("Objects created: "+ objCreated);
        MemoryMonitor.printMemoryReport("After Allocation");
        System.out.println("Removing strong references...");
        for(int i = 0; i < objects.length; i++){
            objects[i] = null;
        }
        System.out.println("Triggering Garbage Collection...");
        MemoryMonitor.triggerGarbageCollection();
        MemoryMonitor.printMemoryReport("After GC");
        long elapsedMillis = (System.nanoTime() - startTime) / 1_000_000;
        System.out.println("Execution time: "+ elapsedMillis+"ms");

        // TODO: fill objects[i] = new DemoObject("Object-" + i)
        // TODO: print Objects Created count; printMemoryReport After Allocation
        // TODO: set objects = null; trigger GC; print After GC report + elapsed ms
        // Tip: elapsedMillis = (System.nanoTime() - startTime) / 1_000_000
        //throw new UnsupportedOperationException("TODO");
    }
}