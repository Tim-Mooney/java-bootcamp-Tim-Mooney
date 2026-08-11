Failure 3: Storing a customer twice just silently overwrites the first one with the second one.
Failure 4: Added a thread.sleep in notificationservice. Made creating a customer take way longer.
Failure 5: Now each customerService instance has its own repo and map of customers so everything is wrong.

# Which design decision most affected correctness (constructor vs field injection)?
- Constructor injection
# What evidence proves the graph works (unit + IT + curls)?
-CustomerServiceTest proves CustomerService + InMemoryCustomerRepository + NotificationService cooperate correctly without Spring
# Which failure was hardest to diagnose (scan issues, missing beans)?
- Number 5. If I only add a customer, it works. It is silently broken.