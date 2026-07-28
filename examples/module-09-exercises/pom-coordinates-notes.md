<groupId>com.northstar</groupId>
<artifactId>customer-service</artifactId>
<version>0.1.0-SNAPSHOT</version>
<packaging>jar</packaging>


| Question | Your answer                                  |
| -------- |----------------------------------------------|
| What is the `groupId`? | com.northstar                                |
| What is the `artifactId`? | customer-service                             |
| What is the `version`? | 0.1.0-SNAPSHOT                               |
| What is the packaging? | jar                                          |
| Write the full GAV (`groupId:artifactId:version`) | com.northsar:customer-service:0.1.0-SNAPSHOT |

A -SNAPSHOT version means the artifact is still under active development and may change without a new release number.

groupId set to com.example while the Java packages are com.northstar.crm; that is not the right groupId so it won't be found

artifactId set to CustomerService (PascalCase); it is wrong, for one. If the artifactId is CustomerService, it should be kebab case

omitting <packaging> and assuming WAR for a plain Java library/app JAR; default is jar, war must be explicitly declared

committing a different version on every laptop with no team agreement. the dependencies might be different.