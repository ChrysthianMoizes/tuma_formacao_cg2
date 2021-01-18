package com.basis.sge.service.servico.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class InscricaoDTO implements Serializable {

    private Integer id;

    private Integer idTipoSituacao;

    private Integer idUsuario;

    private Integer idEvento;

    private List<InscricaoRespostaDTO> respostas;
}
