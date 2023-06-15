package by.paprauka.web.controller;

import by.paprauka.database.dto.LoginDto;
import by.paprauka.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static by.paprauka.web.util.PagesUtil.LOGIN;

@Controller
@RequestMapping(LOGIN)
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping
    public String getLoginPage() {
        return "login";
    }

    @PostMapping
    public String login(LoginDto login) {
        return userService.getBy(login)
                .map(user -> "redirect:/book")
                .orElse("redirect:/login?error=true");
    }
}
