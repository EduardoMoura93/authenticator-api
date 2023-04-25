package br.com.authenticator.user;

import br.com.authenticator.user.request.UserRequest;
import br.com.authenticator.user.response.UserResponse;
import br.com.authenticator.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserApi {

    private final UserService service;

    @PostMapping("/create")
    public UserResponse createUser (@RequestBody UserRequest request){
        return service.createUser(request);
    }

    @GetMapping("/validatePassword")
    public ResponseEntity<Boolean> validatePassword(@RequestParam String login, @RequestParam String password){
        return service.validatePassword(login,password);
    }
}
