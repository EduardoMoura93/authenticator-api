package br.com.authenticator.user.service;

import br.com.authenticator.user.assembler.UserAssemblerMapper;
import br.com.authenticator.user.model.UserModel;
import br.com.authenticator.user.repository.UserRepository;
import br.com.authenticator.user.request.UserRequest;
import br.com.authenticator.user.response.UserResponse;
import br.com.authenticator.user.validate.UserValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final UserAssemblerMapper mapper;
    private final UserValidate validate;

    public UserResponse createUser(UserRequest request){

        validate.validateRequest(request);

        request.setPassword(encoder.encode(request.getPassword()));
        repository.save(mapper.toEntity(request));

        UserResponse response = new UserResponse();
         response.setMessage("Usuario " + request.getUserName() + " cadastrado com sucesso!");
         return response;
    }


    public ResponseEntity<Boolean> validatePassword(String login, String password) {

        Optional<UserModel> user = repository.findByUserName(login);

        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

        boolean valid = encoder.matches(password, user.get().getPassword());

        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;

        return ResponseEntity.status(status).body(valid);
    }
}
