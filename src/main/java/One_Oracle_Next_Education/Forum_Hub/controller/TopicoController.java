package One_Oracle_Next_Education.Forum_Hub.controller;

import One_Oracle_Next_Education.Forum_Hub.dto.TopicoDetalhesResponse;
import One_Oracle_Next_Education.Forum_Hub.dto.TopicoRequest;
import One_Oracle_Next_Education.Forum_Hub.dto.TopicoResponse;
import One_Oracle_Next_Education.Forum_Hub.service.TopicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/topicos")
@Tag(name = "Tópicos", description = "Operações relacionadas a tópicos do fórum")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Operation(summary = "Criar um novo tópico")
    public ResponseEntity<TopicoResponse> criarTopico(
            @RequestBody @Valid TopicoRequest request,
            UriComponentsBuilder uriBuilder) {

        TopicoResponse response = topicoService.criarTopico(request);
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(response.id()).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    @Operation(summary = "Listar todos os tópicos")
    public ResponseEntity<Page<TopicoResponse>> listarTopicos(
            @PageableDefault(size = 10, sort = "dataCriacao") Pageable pageable) {

        Page<TopicoResponse> topicos = topicoService.listarTodos(pageable);
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/curso/{cursoNome}")
    @Operation(summary = "Listar tópicos por curso")
    public ResponseEntity<Page<TopicoResponse>> listarPorCurso(
            @PathVariable String cursoNome,
            @PageableDefault(size = 10, sort = "dataCriacao") Pageable pageable) {

        Page<TopicoResponse> topicos = topicoService.listarPorCurso(cursoNome, pageable);
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/ano/{ano}")
    @Operation(summary = "Listar tópicos por ano")
    public ResponseEntity<Page<TopicoResponse>> listarPorAno(
            @PathVariable int ano,
            @PageableDefault(size = 10, sort = "dataCriacao") Pageable pageable) {

        Page<TopicoResponse> topicos = topicoService.listarPorAno(ano, pageable);
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/curso/{cursoNome}/ano/{ano}")
    @Operation(summary = "Listar tópicos por curso e ano")
    public ResponseEntity<Page<TopicoResponse>> listarPorCursoEAno(
            @PathVariable String cursoNome,
            @PathVariable int ano,
            @PageableDefault(size = 10, sort = "dataCriacao") Pageable pageable) {

        Page<TopicoResponse> topicos = topicoService.listarPorCursoEAno(cursoNome, ano, pageable);
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar tópico por ID")
    public ResponseEntity<TopicoDetalhesResponse> buscarTopico(@PathVariable Long id) {
        return topicoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar tópico")
    public ResponseEntity<TopicoResponse> atualizarTopico(
            @PathVariable Long id,
            @RequestBody @Valid TopicoRequest request) {

        return topicoService.atualizarTopico(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar tópico")
    public ResponseEntity<Void> deletarTopico(@PathVariable Long id) {
        boolean deletado = topicoService.deletarTopico(id);
        return deletado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}