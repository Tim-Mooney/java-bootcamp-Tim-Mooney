The program allocated about 250 MB over time despite a 64 MB maximum heap.
GC log entries appeared between rounds. A before/after value that decreased
shows that memory was reclaimed. Exact pause times varied on my machine.

Allocated over time is the total memory used overall and simultaneously live is how much memory is actually in use at a given time.

If you use and free 1 byte 1 million times the allocated over time will be very high but the simultaneously live will be very low.

