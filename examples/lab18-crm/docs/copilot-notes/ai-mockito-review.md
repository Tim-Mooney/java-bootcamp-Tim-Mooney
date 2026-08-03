# Prompt
Generate a Mockito test that mocks CustomerRepository for DefaultCustomerService
duplicate-email path. Verify existsByEmail and that save is never called.
Fixtures: CUS-1001 Amina Khan. No Spring annotations. 
I also handed it the current CustomerServiceMockitoTest.java

# Did it mock the class under test? Reject if yes.
Only mocked repository. Testing service
# Are stubs minimal (no unused when)?
Stubs existsById and existsByEmail. Both used in validation.
# Does verification match the real validator call order?
I had to add verify existsById, but it is in order.
# Any Thread.sleep or real DB?
None
# Run mvn -q test after accepting?
Passes