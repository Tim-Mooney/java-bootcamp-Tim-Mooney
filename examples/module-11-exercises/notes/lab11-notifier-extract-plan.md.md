smell: I/O buried inside the service method

System.out.println("Activated " + id); // testability smell
emailClient.send(customer.getEmail(), ...); // hidden side effect

extract sketch -- paper only, do not implement yet
```azure
public interface CustomerNotifier {
    void notifyStatusChange(String customerId,
                            CustomerStatus from,
                            CustomerStatus to);
}
```

do not implement Spring events or Kafka yet — prep sketch only.
stronger prompts name the collaborator so AI does not bury I/O in the service.

