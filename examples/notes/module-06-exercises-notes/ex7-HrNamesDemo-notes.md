filter: Stream<Employee> -> Stream<Employee>
map:    Stream<Employee> -> Stream<String>
sorted: Stream<String>   -> Stream<String>
toList: Stream<String>   -> List<String>

the Employee contains the department; a mapped String name does not.