package br.com.authenticator.config.security;

import br.com.authenticator.config.data.DetailsUserData;
import br.com.authenticator.config.model.TokenResponse;
import br.com.authenticator.user.model.UserModel;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static br.com.authenticator.common.Constants.FAILED_AUTHENTICATE_USER;

@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final int TOKEN_EXPIRE = 900000;
    public static final String TOKEN_PASSWORD = "9494beb1-1d9f-41bc-b9b5-3e29ad1d973f";
    private final AuthenticationManager manager;

    public JWTAuthenticationFilter(AuthenticationManager manager) {
        this.manager = manager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try {
            UserModel user = new ObjectMapper()
                    .readValue(request.getInputStream(), UserModel.class);

                    return manager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),
                                                                                        user.getPassword(),
                                                                                        new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(FAILED_AUTHENTICATE_USER, e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException {

        DetailsUserData userData = (DetailsUserData) authResult.getPrincipal();

        String token = JWT.create()
                .withSubject(userData.getUsername())
                .withClaim("name", userData.getName())
                .withClaim("tokenExpiration", new Date(System.currentTimeMillis() + TOKEN_EXPIRE))
                .sign(Algorithm.HMAC512(TOKEN_PASSWORD));

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(token);

        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = mapper.writeValueAsString(tokenResponse);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
        response.getWriter().flush();

        String message = String.format("Usuário '%s' autenticado com sucesso.", userData.getUsername());
        log.info(message);
    }
}
