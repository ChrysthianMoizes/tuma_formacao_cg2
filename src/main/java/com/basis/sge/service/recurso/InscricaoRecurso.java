package com.basis.sge.service.recurso;

import com.basis.sge.service.servico.InscricaoServico;
import com.basis.sge.service.servico.dto.InscricaoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/inscricoes")
@RequiredArgsConstructor
public class InscricaoRecurso {

    private final InscricaoServico inscricaoServico;

    @GetMapping
    public ResponseEntity<List<InscricaoDTO>> listar() {
        List<InscricaoDTO> list = inscricaoServico.listar();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InscricaoDTO> obterPorId(@PathVariable Integer id) {
        InscricaoDTO entidadeDTO = inscricaoServico.obterPorId(id);
        return ResponseEntity.ok(entidadeDTO);
    }

    @PostMapping
    public ResponseEntity<InscricaoDTO> salvar(@RequestBody InscricaoDTO entidadeDTO) {
        InscricaoDTO entidade = inscricaoServico.salvar(entidadeDTO);
        return ResponseEntity.ok(entidade);
    }

    @PutMapping
    public ResponseEntity<InscricaoDTO> editar(@RequestBody InscricaoDTO entidadeDTO) {
        InscricaoDTO entidade = inscricaoServico.salvar(entidadeDTO);
        return ResponseEntity.ok(entidade);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        inscricaoServico.remover(id);
        return ResponseEntity.ok().build();
    }


}
