package One_Oracle_Next_Education.Forum_Hub.repository;

import One_Oracle_Next_Education.Forum_Hub.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {

    Page<Topico> findAll(Pageable pageable);

    @Query("SELECT t FROM Topico t WHERE t.curso.nome = :cursoNome")
    Page<Topico> findByCursoNome(@Param("cursoNome") String cursoNome, Pageable pageable);

    @Query("SELECT t FROM Topico t WHERE YEAR(t.dataCriacao) = :ano")
    Page<Topico> findByAno(@Param("ano") int ano, Pageable pageable);

    @Query("SELECT t FROM Topico t WHERE t.curso.nome = :cursoNome AND YEAR(t.dataCriacao) = :ano")
    Page<Topico> findByCursoNomeAndAno(@Param("cursoNome") String cursoNome, @Param("ano") int ano, Pageable pageable);

    boolean existsByTituloAndMensagem(String titulo, String mensagem);

    @Query("SELECT t FROM Topico t JOIN FETCH t.autor JOIN FETCH t.curso WHERE t.id = :id")
    Optional<Topico> findByIdWithDetails(@Param("id") Long id);
}