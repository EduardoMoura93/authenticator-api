package br.com.authenticator.config.service;

import br.com.authenticator.config.data.DetailsUserData;
import br.com.authenticator.user.model.UserModel;
import br.com.authenticator.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static br.com.authenticator.common.Constants.USER_NOT_FOUND;

@Component
public class DetailsUserServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    public DetailsUserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<UserModel> user = repository.findByUserName(userName);
        if(user.isEmpty()){
            throw new UsernameNotFoundException(USER_NOT_FOUND);
        }
        return new DetailsUserData(user);
    }
}
