Before map: Stream<Employee>
Mapping function: Employee::name
After map: Stream<String>
Final result: List<String>

before the map, the stream is holding employees. after, it holds strings

map(employee::name) maps employee objects to employee.names and then keeps strings.