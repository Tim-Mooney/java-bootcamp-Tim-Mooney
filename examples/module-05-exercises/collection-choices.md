# Collection choices

| Scenario | Interface        | Implementation | Why                                                                                                                                               |
| -------- |------------------|----------------|---------------------------------------------------------------------------------------------------------------------------------------------------|
| Ordered catalog; duplicate titles allowed | List<Book>       | ArrayList<>    | Ordered and allows duplicates                                                                                                                     |
| Unique registered book IDs | Set<String>      | HashSet<>      | Unique IDs and don't care about order                                                                                                             |
| Book ID → current borrower ID | Map<Id, String>  | HashMap<>      | Key value pairs and don't care about order                                                                                                        |
| Alphabetically sorted categories | Set<String>      | TreeSet<>      | Red Black trees sort automatically, assuming categories are unique                                                                                |
| Category → count, sorted by category | Map<String, int> | TreeMap<>      | Key value pairs and sorted order                                                                                                                  |
| Checkout history in event order | List<String>     | ArrayList<>    | Could be linked list as well I don't know how often you need to lookup in the middle. Easy to keep in event order by simply appending to the end. |


If unique IDs must also preserve registration order, what changes?
Reference: LinkedHashSet.
If borrower lookup must preserve insertion order for display, what changes?
Reference: LinkedHashMap.
If many insertions/removals occur in the middle, is LinkedList automatically best?
Reference: No. Access pattern and traversal cost matter; measure rather than assume.

Something like a hashmap is faster than a linked hashmap, but it doesn't maintain insertion order. Similarly, a hashmap is faster than a treemap, but doesn't maintain sorted order.

Deciding which structure to use based on all criteria is best, instead of only focusing on speed.

