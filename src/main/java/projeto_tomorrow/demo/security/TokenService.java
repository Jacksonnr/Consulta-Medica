package projeto_tomorrow.demo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import projeto_tomorrow.demo.Entity.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;
    public String generateToken (User user){
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer("api-consulta-medica")
                    .withSubject(user.getEmail())
                    .withExpiresAt(this.generateDateExpiration())
                    .sign(algoritmo);
            return token;
        } catch (JWTCreationException ex) {
            throw new RuntimeException("Erro durante autenticação");
        }
    }

    public String validationToken (String token){
        try{
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("api-consulta-medica")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException ex) {
            return null;
        }
    }

    private Instant generateDateExpiration(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
