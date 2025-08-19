package One_Oracle_Next_Education.Forum_Hub.repository;

import One_Oracle_Next_Education.Forum_Hub.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findByNome(String nome);
    boolean existsByNome(String nome);
}