## lab17-001

- Can every assert fail if production regresses? yes
- Shared CRM fixture IDs (not random PII)? yes CUS-#### style (1001, 1002, 2001, 9999)
- No phantom Spring/JPA imports? No phantom imports
- Independent @BeforeEach? yes
- mvn -q test after edits? runs, all pass