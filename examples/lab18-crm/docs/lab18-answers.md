## Which design decision most affected correctness (shared mock repo vs @InjectMocks alone)?
Shared mock repo. Service and validator are constructed with the same mocked repo. 
They need the same repo (some failure experiment in a lab before taught this) or else nothing works.
## What evidence proves the implementation works (captor values, never().save)?
the captor captured the object that was saved and then asserted based on that object.
## Which failure was hardest to diagnose (UnnecessaryStubbing, wrong verify count, …)?
None of them were too bad, they failed loudly. Unnecessary stubbing can be annoying if you don't understand your class very well.