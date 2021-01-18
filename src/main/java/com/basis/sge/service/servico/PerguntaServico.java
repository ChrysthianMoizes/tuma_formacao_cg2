package com.basis.sge.service.servico;

import com.basis.sge.service.dominio.Pergunta;
import com.basis.sge.service.repositorio.PerguntaRepositorio;
import com.basis.sge.service.servico.dto.PerguntaDTO;
import com.basis.sge.service.servico.exception.RegraNegocioException;
import com.basis.sge.service.servico.mapper.PerguntaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PerguntaServico {

    private final PerguntaRepositorio perguntaRepositorio;
    private final PerguntaMapper perguntaMapper;

    public List<PerguntaDTO> listar() {
        List<Pergunta> perguntas = perguntaRepositorio.findAll();
        return perguntaMapper.toDto(perguntas);
    }

    public PerguntaDTO obterPorId(Integer id) {
        Pergunta pergunta = obter(id);
        return perguntaMapper.toDto(pergunta);
    }
    
    private Pergunta obter(Integer id) {
        return perguntaRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Pergunta não encontrada"));
    }

    public PerguntaDTO salvar(PerguntaDTO perguntaDTO) {
        Pergunta pergunta = perguntaMapper.toEntity(perguntaDTO);
        perguntaRepositorio.save(pergunta);
        return perguntaMapper.toDto(pergunta);
    }

    public PerguntaDTO editar(PerguntaDTO perguntaDTO) {
        Pergunta pergunta = perguntaMapper.toEntity(perguntaDTO);
        perguntaRepositorio.save(pergunta);
        return perguntaMapper.toDto(pergunta);
    }

    public void remover(Integer id) {
        Pergunta pergunta = obter(id);
        perguntaRepositorio.delete(pergunta);
    }

}
