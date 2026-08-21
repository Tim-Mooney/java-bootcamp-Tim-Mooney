# Lab 38 — Performance report

| Experiment | Plan hash / notes               | Buffers | Median time | Write cost |
| ---------- |---------------------------------|---------|-------------|------------|
| lab38-001 baseline email | TODO                            | 531     | 4.1 ms      | N/A        |
| lab38-002 after email index | index Scan on uk_customer_email | 4       | .043 ms     | N/A        |
| lab38-003 OFFSET deep page | TODO                            | 438     | 3.5  ms     | N/A        |
| lab38-004 keyset page | TODO                            | 12      | .828ms      | N/A        |

## Why keyset beats deep OFFSET

Keyset beats offset because it seeks to the row just past the cursor, instead of a set offset which may or may not be correct.