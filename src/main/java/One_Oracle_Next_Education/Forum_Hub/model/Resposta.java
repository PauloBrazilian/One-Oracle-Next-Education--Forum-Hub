package One_Oracle_Next_Education.Forum_Hub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "resposta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Mensagem é obrigatória")
    @Column(columnDefinition = "TEXT")
    private String mensagem;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @NotNull(message = "Autor é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @NotNull(message = "Tópico é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;

    private Boolean solucao = false;

    @PrePersist
    protected void onCreate() {
        dataCriacao = LocalDateTime.now();
    }
}