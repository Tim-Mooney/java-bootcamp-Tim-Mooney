SOLID - Single Responsibility, Open/Closed, Liskov Substitution, Interface Segregation, Dependency Inversion.

Creating a separate class for frozen accounts means Account was open to expansion but did not impact other account types working.

All the types of account are ultimately still accounts, just more specific types of account.

The accounts use everything given to them by Account

Account does not depend on any account types.