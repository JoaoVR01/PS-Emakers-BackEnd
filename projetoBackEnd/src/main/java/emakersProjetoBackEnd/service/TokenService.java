package emakersProjetoBackEnd.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import emakersProjetoBackEnd.data.entity.Pessoa;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    public String secret;

    public String generateToken(Pessoa pessoa){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                        .withIssuer("emakersProjetoPratico")
                        .withSubject(pessoa.getEmail())
                        .withExpiresAt(expirationDate())
                        .sign(algorithm);
            return token;
        }catch(JWTCreationException exception) {     
            throw new RuntimeException("Token has not been generated", exception);
        }
    }

    public String validationToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("emakersProjetoPratico")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return "";
        }

    }

    private Instant expirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
