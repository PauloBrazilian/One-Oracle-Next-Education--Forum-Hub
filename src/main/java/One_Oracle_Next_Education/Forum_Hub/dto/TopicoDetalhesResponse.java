package One_Oracle_Next_Education.Forum_Hub.dto;

import One_Oracle_Next_Education.Forum_Hub.model.StatusTopico;
import One_Oracle_Next_Education.Forum_Hub.model.Topico;

import java.time.LocalDateTime;
import java.util.List;

public record TopicoDetalhesResponse(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        StatusTopico status,
        Long autorId,
        String autorNome,
        Long cursoId,
        String cursoNome,
        List<RespostaResponse> respostas
) {
    public TopicoDetalhesResponse(Topico topico, List<RespostaResponse> respostas) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                topico.getAutor().getId(),
                topico.getAutor().getNome(),
                topico.getCurso().getId(),
                topico.getCurso().getNome(),
                respostas
        );
    }
}