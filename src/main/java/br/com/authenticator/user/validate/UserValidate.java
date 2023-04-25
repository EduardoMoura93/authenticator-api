package br.com.authenticator.user.validate;

import br.com.authenticator.user.model.UserModel;
import br.com.authenticator.user.repository.UserRepository;
import br.com.authenticator.user.request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static br.com.authenticator.common.Constants.*;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserValidate {

    private final UserRepository repository;

    public void validateRequest(UserRequest request){
        if(request.getName().isBlank()){
            throw new InvalidRequestException(NAME_IS_REQUIRED);
        }
        if(request.getUserName().isBlank()){
            throw new InvalidRequestException(LOGIN_IS_REQUIRED);
        }
        if(request.getPassword().isBlank()){
            throw new InvalidRequestException(PASSWORD_IS_REQUIRED);
        }
        validateUser(request);
    }

    public void validateUser(UserRequest request){
        Optional<UserModel> model = repository.findByUserName(request.getUserName());
        if(model.isPresent()){
            throw new RuntimeException(USER_ALREADY_REGISTERED);
        }
    }
}
