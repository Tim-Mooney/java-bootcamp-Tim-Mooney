validate → compile → test → package → verify → install → deploy


| Intent | Command      |
| ------ |--------------|
| Confirm POM parses before coding further | mvn validate |
| Compile production Java only (stops before tests if you use the phase carefully) | mvn compile  |
| Run unit tests | mvn test     |
| Produce `target/customer-service.jar` | mvn package  |
| Run package plus verification checks CI cares about | mvn verify   |
| Put the JAR into your local Maven cache | mvn install  |

package - 4
validate - 1
install - 6
compile - 2
verify - 5
test - 3

Continuous Integration usually runs mvn -B verify so the build is batch/non-interactive and stops after verification without casually installing or deploying from every laptop.

