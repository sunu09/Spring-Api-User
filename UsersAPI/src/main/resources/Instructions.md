API Design Lab
In the lab, we'll build a simple API to test the concepts from the lesson. The API will be used to interact with users of a sample application.
1. Create a new spring boot application
   Field Value
   Name UsersAPI
   Group com.tts
   Artifact UsersAPI
   Version 0.0.1-SNAPSHOT
   Description API for interacting with Users
   Package com.tts.UsersAPI
2. Include the dependencies for DevTools, Web, Lombok, H2, and JPA.
3. Create a model for a User. A user has the following properties:
   Id
   First name
   Last name
   State (of residence)
4. In the User model, add the following annotations for Id:
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
5. Add the necessary annotations above the User class definition:
   @Data
   @Builder
   @AllArgsConstructor
   @NoArgsConstructor
   @Entity
6. Create a repository for Users. In addition to the default methods made available by having the interface extend CrudRepository, add a method to find users with
   a given value for the property 'state'. Don't forget to add the @Repository annotation above the interface definition.
7. Add a file to seed the database so that you have some users to work with. In src/main/resources , add a file called data.sql and paste in the following
   insert script. This will cause users to be added to the database each time the app starts up.
   INSERT INTO USER (ID, FIRST_NAME, LAST_NAME, STATE) VALUES
   (1, 'James', 'Garner', 'Ohio'),
   (2, 'Craig', 'Steger', 'Florida'),
   (3, 'Bruce', 'Campbell', 'Illinois'),
   (4, 'Louis', 'Greene', 'Ohio'),
   (5, 'Lawrence', 'Spellman', 'New Mexico'),
   (6, 'Rita', 'Richmond', 'Florida'),
   (7, 'Lisa', 'Smith', 'Ohio'),
   (8, 'Melissa', 'Grant', 'Vermont'),
   (9, 'Patti', 'Simmons', 'Maryland'),
   (10, 'Mary', 'Jackson', 'Floria');
8. Create a controller for Users. Include the annotation @RestController above the class declaration to indicate that this controller is part of a REST API and does
   not return HTML.
9. In the controller, add an autowired dependency for the repository.
10. Add an endpoint to get all users.
    @GetMapping("/users")
    public List<User> getUsers(){
    return (List<User>) repository.findAll();
    }
11. Try running your app. If you navigate to http://localhost:8080/users , you should get back a JSON array containing all 10 users in the database.
12. Add an endpoint to get a single user by id. Notice that this endpoint uses a path variable. Also, since it is possible that a user with the given id might not exist,
    the method has to return Optional<User> , meaning that the result might be null.
    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable(value="id") Long id){
    return repository.findById(id);
    }
13. Add an endpoint to create a new user. This endpoint requires that user data be sent in the request body, so we use the @RequestBody annotation on the
    parameter.
    @PostMapping("/users")
    public void createUser(@RequestBody User user){
    repository.save(user);
    }
14. Try creating a new user using the POST method. Open an API client such as Postman and create a POST request to localhost:8080/users with the
    following request body:
    {
    "firstName": "Bob",
    "lastName": "Smith",
    "state": "New York"
    }
15. Make a request to get all users to ensure that the new user has been added.
16. Add an endpoint to update an existing user. We again use the @RequestBody annotation on the user data parameter.
    @PutMapping("/users/{id}")
    public void createUser(@PathVariable(value="id") Long id, @RequestBody User user){
    repository.save(user);
    }
17. Try updating a user using the PUT method. Open an API client such as Postman and create a PUT request to localhost:8080/users/10 with the following
    request body:
    {
    "id": 10,
    "firstName": "Mary",
    "lastName": "Smith",
    "state": "Florida"
    }
18. Make a request to get the user to ensure that the last name was changed.
19. Add an endpoint to delete an existing user.
    @DeleteMapping("/users/{id}")
    public void createUser(@PathVariable(value="id") Long id){
    repository.deleteById(id);
    }
20. Finally, modify our endpoint for getting all users to allow filtering by state. Note we are not creating a new endpoint, just updating the first one we created. If a
    user passes in a query parameter for state, we call the repository method to find by state. If the user passes in no value, we simply call the repository method
    to get all users.
    @GetMapping("/users")
    public List<User> getUsers(@RequestParam(value="state", required=false) String state){
    if (state != null) {
    return (List<User>) repository.findByState(state);
    }
    return (List<User>) repository.findAll();
    }