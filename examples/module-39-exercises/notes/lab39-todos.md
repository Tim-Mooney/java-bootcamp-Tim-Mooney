# Lab 39 — Fill JPA TODOs

```java
@Entity
@Table(name = "customer")
class CustomerEntity {
  @Id
  private String customerId;
  @Column(name = "full_name", nullable = false)
  private String fullName;
  @Column(nullable = false)
  private String status;
  @Version
  private long version; // optimistic lock
}

interface CustomerRepository extends JpaRepository<CustomerEntity, String> {
  Page<CustomerEntity> findByStatus(String status, Pageable pageable);
}

// application.yml ideas
spring.datasource.url: jdbc:postgresql://localhost:5432/northstar
spring.jpa.hibernate.ddl-auto: validate
spring.flyway.enabled: true
```