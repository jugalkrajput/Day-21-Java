📘 Day 21 – REST API CRUD (Spring Boot)
========================================

✅ What I learned today

1. CRUD Mapping
   ------------

Operation	|	Method	|	URL
----------|-------|---------------------------------
Create		|	POST	|	/employees
Read All	|	GET	|	/employees
Read One	|	GET	|	/employees/{id}
Update		|	PUT	|	/employees/{id}
Delete		|	DELETE	|	/employees/{id}


2. Employee Model
   ---------------

//code
-------

public class Employee {

    private int id;
    
    private String name;
    
    private String department;
    
    private double salary;
    
    // Getters & Setters

}


3. Controller Code Snippets
   --------------------------

Create:
-------

//code
------

@PostMapping

public Employee create(@RequestBody Employee emp) {

    emp.setId(idCounter.incrementAndGet());
    
    employees.add(emp);
    
    return emp;

}

Update:
---------

//code
-------

@PutMapping("/{id}")

public Employee update(@PathVariable int id, @RequestBody Employee updated) {

    // find and update fields

}

Delete:
-------

//code
-------

@DeleteMapping("/{id}")

public String delete(@PathVariable int id) {

    employees.removeIf(e -> e.getId() == id);
    
    return "Deleted";

}


4. Postman Testing
   ----------------

- Set Content-Type: application/json

- Send JSON body for POST/PUT

5. Extra: Search Endpoint
   -----------------------

@GetMapping("/search")

public List<Employee> search(@RequestParam String department) {

    return employees.stream()
    
        .filter(e -> e.getDepartment().equalsIgnoreCase(department))
        
        .collect(Collectors.toList());

}
