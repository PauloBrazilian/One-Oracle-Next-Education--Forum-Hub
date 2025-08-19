package One_Oracle_Next_Education.Forum_Hub.repository;

import One_Oracle_Next_Education.Forum_Hub.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    UserDetails findUserDetailsByEmail(String email);
    boolean existsByEmail(String email);
}