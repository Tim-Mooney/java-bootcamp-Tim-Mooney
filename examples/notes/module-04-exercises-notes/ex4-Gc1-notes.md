When I ran java -XX:+UseG1GC -Xms16m -Xmx64m -Xlog:gc GcObserve I saw Using G1 and then every pause was a (G1 Evacuation Pause)

Exact pause cannot be guaranteed because the garbage collector's work load changes based on what it sees in the heap. There could be more or less work so it could take more or less time.