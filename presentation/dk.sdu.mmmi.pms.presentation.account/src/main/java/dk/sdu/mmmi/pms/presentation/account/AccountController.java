package dk.sdu.mmmi.pms.presentation.account;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @GetMapping("/test")
    public String test() {
        return "Account service is up and running!";
    }
}