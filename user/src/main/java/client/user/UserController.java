package client.user;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notify")
public class UserController {


    private final UserService service;
    public UserController(UserService service){
        this.service = service;
    }


    @PostMapping
    public void receiveNotification(@RequestBody String payload){
        System.out.println(payload);
        service.notification(payload);
    }

}
