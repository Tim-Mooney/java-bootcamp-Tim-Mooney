import java.lang.ref.WeakReference;

public class WeakReferenceDemo {

    public static void main(String[] args) {
        System.out.println("===== Weak Reference Demonstration =====");

        System.out.println("--- Strong Reference ---");
        Person strongPerson = new Person("Strong User", 40);
        System.out.println("Before GC : " + strongPerson);
        MemoryMonitor.triggerGarbageCollection();
        System.out.println("After GC  : " + strongPerson);
        System.out.println("Object remains because a strong reference still exists.");

        System.out.println();
        System.out.println("--- Weak Reference ---");
        Person weakTarget = new Person("weak", 1);

        WeakReference<Person> weakRef = new WeakReference<>(weakTarget);

        System.out.println("Before removing strong reference: "+ weakRef.get());

        weakTarget = null;
        System.out.println("Strong reference removed.");
        MemoryMonitor.triggerGarbageCollection();

        System.out.println("After GC weakRef.get() = " +weakRef.get());

        // TODO: create Person weakTarget; wrap in WeakReference<Person>
        // TODO: null weakTarget; trigger GC; print WeakReference.get() result
        //throw new UnsupportedOperationException("TODO");
    }
}