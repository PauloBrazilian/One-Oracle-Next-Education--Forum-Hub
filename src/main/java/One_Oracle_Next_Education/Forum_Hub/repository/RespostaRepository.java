package One_Oracle_Next_Education.Forum_Hub.repository;

import One_Oracle_Next_Education.Forum_Hub.model.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, Long> {

    @Query("SELECT r FROM Resposta r JOIN FETCH r.autor WHERE r.topico.id = :topicoId ORDER BY r.dataCriacao ASC")
    List<Resposta> findByTopicoIdWithAutor(@Param("topicoId") Long topicoId);

    @Query("SELECT COUNT(r) > 0 FROM Resposta r WHERE r.topico.id = :topicoId AND r.solucao = true")
    boolean existsSolucaoByTopicoId(@Param("topicoId") Long topicoId);
}