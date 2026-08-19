# Lab 39 — Entity Mapping

## Reference

| Column | Java field / annotation idea |
| --- | --- |
| customer_id | @Id String customerId |
| full_name | String fullName + @Column |
| status | String or enum status |
| created_at | Instant createdAt |

## Step 2 — Account

Add account mapping: Long id, String customerId, @ManyToOne optional note.
@Id
@Column(name = "id")
private Long id;
@Column(name = "customer_id", nullable = false)
private String customerId;
@Column(name = "full_name"")
private String fullName;


## Step 3 — Naming

Decide snake_case columns vs camelCase fields strategy.
Columns are in snake case, fields are in camel case

## Step 4 — Fixture

Entity instance mental model: customerId=`CUS-1001`, fullName=`Amina Khan`.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.