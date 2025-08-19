package One_Oracle_Next_Education.Forum_Hub.dto;

import One_Oracle_Next_Education.Forum_Hub.model.Resposta;
import java.time.LocalDateTime;

public record RespostaResponse(
        Long id,
        String mensagem,
        LocalDateTime dataCriacao,
        Long autorId,
        String autorNome,
        Boolean solucao
) {
    public RespostaResponse(Resposta resposta) {
        this(
                resposta.getId(),
                resposta.getMensagem(),
                resposta.getDataCriacao(),
                resposta.getAutor().getId(),
                resposta.getAutor().getNome(),
                resposta.getSolucao()
        );
    }
}