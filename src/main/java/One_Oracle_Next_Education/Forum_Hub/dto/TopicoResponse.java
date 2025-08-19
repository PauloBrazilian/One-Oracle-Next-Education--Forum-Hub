package One_Oracle_Next_Education.Forum_Hub.dto;

import One_Oracle_Next_Education.Forum_Hub.model.StatusTopico;
import One_Oracle_Next_Education.Forum_Hub.model.Topico;

import java.time.LocalDateTime;

public record TopicoResponse(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        StatusTopico status,
        Long autorId,
        String autorNome,
        Long cursoId,
        String cursoNome
) {
    public TopicoResponse(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                topico.getAutor().getId(),
                topico.getAutor().getNome(),
                topico.getCurso().getId(),
                topico.getCurso().getNome()
        );
    }
}