package client.user;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
public class UserController {


    private final UserService service;
    public UserController(UserService service){
        this.service = service;
    }


    @PostMapping()
    public void bookNotification(@RequestBody String payload){
         service.notification(payload);
    }

}
