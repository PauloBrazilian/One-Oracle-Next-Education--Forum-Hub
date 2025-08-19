package One_Oracle_Next_Education.Forum_Hub.service;


import One_Oracle_Next_Education.Forum_Hub.dto.RespostaResponse;
import One_Oracle_Next_Education.Forum_Hub.dto.TopicoDetalhesResponse;
import One_Oracle_Next_Education.Forum_Hub.dto.TopicoRequest;
import One_Oracle_Next_Education.Forum_Hub.dto.TopicoResponse;
import One_Oracle_Next_Education.Forum_Hub.exception.DuplicadoException;
import One_Oracle_Next_Education.Forum_Hub.exception.RecursoNaoEncontradoException;
import One_Oracle_Next_Education.Forum_Hub.model.Curso;
import One_Oracle_Next_Education.Forum_Hub.model.StatusTopico;
import One_Oracle_Next_Education.Forum_Hub.model.Topico;
import One_Oracle_Next_Education.Forum_Hub.model.Usuario;
import One_Oracle_Next_Education.Forum_Hub.repository.CursoRepository;
import One_Oracle_Next_Education.Forum_Hub.repository.RespostaRepository;
import One_Oracle_Next_Education.Forum_Hub.repository.TopicoRepository;
import One_Oracle_Next_Education.Forum_Hub.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private RespostaRepository respostaRepository;

    @Transactional
    public TopicoResponse criarTopico(TopicoRequest request) {
        // Verificar se já existe tópico com mesmo título e mensagem
        if (topicoRepository.existsByTituloAndMensagem(request.titulo(), request.mensagem())) {
            throw new DuplicadoException("Já existe um tópico com este título e mensagem");
        }

        Usuario autor = usuarioRepository.findById(request.autorId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Autor não encontrado"));

        Curso curso = cursoRepository.findById(request.cursoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Curso não encontrado"));

        Topico topico = new Topico();
        topico.setTitulo(request.titulo());
        topico.setMensagem(request.mensagem());
        topico.setAutor(autor);
        topico.setCurso(curso);
        topico.setStatus(StatusTopico.NAO_RESPONDIDO);

        Topico saved = topicoRepository.save(topico);
        return new TopicoResponse(saved);
    }

    public Page<TopicoResponse> listarTodos(Pageable pageable) {
        return topicoRepository.findAll(pageable)
                .map(TopicoResponse::new);
    }

    public Page<TopicoResponse> listarPorCurso(String cursoNome, Pageable pageable) {
        return topicoRepository.findByCursoNome(cursoNome, pageable)
                .map(TopicoResponse::new);
    }

    public Page<TopicoResponse> listarPorAno(int ano, Pageable pageable) {
        return topicoRepository.findByAno(ano, pageable)
                .map(TopicoResponse::new);
    }

    public Page<TopicoResponse> listarPorCursoEAno(String cursoNome, int ano, Pageable pageable) {
        return topicoRepository.findByCursoNomeAndAno(cursoNome, ano, pageable)
                .map(TopicoResponse::new);
    }

    public Optional<TopicoDetalhesResponse> buscarPorId(Long id) {
        return topicoRepository.findByIdWithDetails(id)
                .map(topico -> {
                    List<RespostaResponse> respostas = respostaRepository.findByTopicoIdWithAutor(id)
                            .stream()
                            .map(RespostaResponse::new)
                            .toList();
                    return new TopicoDetalhesResponse(topico, respostas);
                });
    }

    @Transactional
    public Optional<TopicoResponse> atualizarTopico(Long id, TopicoRequest request) {
        return topicoRepository.findById(id)
                .map(topico -> {
                    topico.setTitulo(request.titulo());
                    topico.setMensagem(request.mensagem());

                    if (!topico.getAutor().getId().equals(request.autorId())) {
                        Usuario novoAutor = usuarioRepository.findById(request.autorId())
                                .orElseThrow(() -> new RecursoNaoEncontradoException("Autor não encontrado"));
                        topico.setAutor(novoAutor);
                    }

                    if (!topico.getCurso().getId().equals(request.cursoId())) {
                        Curso novoCurso = cursoRepository.findById(request.cursoId())
                                .orElseThrow(() -> new RecursoNaoEncontradoException("Curso não encontrado"));
                        topico.setCurso(novoCurso);
                    }

                    return new TopicoResponse(topicoRepository.save(topico));
                });
    }

    @Transactional
    public boolean deletarTopico(Long id) {
        if (topicoRepository.existsById(id)) {
            topicoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}