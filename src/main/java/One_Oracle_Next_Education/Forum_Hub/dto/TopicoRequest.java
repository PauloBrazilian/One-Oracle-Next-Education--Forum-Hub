package One_Oracle_Next_Education.Forum_Hub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TopicoRequest(
        @NotBlank(message = "Título é obrigatório")
        @Size(min = 5, max = 100, message = "Título deve ter entre 5 e 100 caracteres")
        String titulo,

        @NotBlank(message = "Mensagem é obrigatória")
        @Size(min = 10, max = 1000, message = "Mensagem deve ter entre 10 e 1000 caracteres")
        String mensagem,

        @NotNull(message = "ID do autor é obrigatório")
        Long autorId,

        @NotNull(message = "ID do curso é obrigatório")
        Long cursoId
) {}