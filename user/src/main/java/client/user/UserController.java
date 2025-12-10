package client.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class UserController {


    private final UserService service;
    public UserController(UserService service){
        this.service = service;
    }


    @GetMapping
    public void bookNotification(){
         service.notification();
    }

}
