The original method did two things. SRP states that methods should have one job. This makes everything easier for expanding this project.

Main should manage menu input, BankService should coordinate banking operations, and domain classes should protect their own state.

Calculating, printing, and calling these methods should be separate. Maybe the program needs to recalculate interest later without printing.