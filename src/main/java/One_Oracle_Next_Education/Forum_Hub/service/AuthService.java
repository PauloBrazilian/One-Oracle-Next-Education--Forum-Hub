package One_Oracle_Next_Education.Forum_Hub.service;


import One_Oracle_Next_Education.Forum_Hub.model.Usuario;
import One_Oracle_Next_Education.Forum_Hub.repository.UsuarioRepository;
import One_Oracle_Next_Education.Forum_Hub.security.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String autenticarUsuario(String email, String senha) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(email, senha);
        Authentication auth = authenticationManager.authenticate(usernamePassword);

        var usuario = (Usuario) auth.getPrincipal();
        return tokenService.gerarToken(usuario);
    }

    public Usuario getUsuarioFromToken(String token) {
        var jwtToken = token.replace("Bearer ", "");
        var email = tokenService.validarToken(jwtToken);
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}
