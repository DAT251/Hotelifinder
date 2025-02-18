package dat251_gruppe2.hotelifinder.controller;

public class exampleController {
}

/*
Example of User controller:

@RestController
@CrossOrigin
@RequestMapping("/api/v1/users")
public class UserController {

    private final DomainManager domainManager;

    public UserController(@Autowired DomainManager domainManager) {
        this.domainManager = domainManager;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(@RequestHeader("Authorization") String authToken) {
        if (!authenticationService.validateToken(authToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(domainManager.getAllUsers());
    }
}

 */
