package com.basis.sge.service.servico;

import com.basis.sge.service.dominio.Usuario;
import com.basis.sge.service.repositorio.UsuarioRepositorio;
import com.basis.sge.service.servico.dto.EmailDTO;
import com.basis.sge.service.servico.dto.UsuarioDTO;
import com.basis.sge.service.servico.exception.RegraNegocioException;
import com.basis.sge.service.servico.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioServico {

    private final UsuarioRepositorio usuarioRepositorio;
    private final UsuarioMapper usuarioMapper;
    private final ProdutorServico produtorServico;

    public List<UsuarioDTO> listar() {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        return usuarioMapper.toDto(usuarios);
    }

    public UsuarioDTO obterPorId(Integer id) {
        Usuario usuario = obter(id);
        return usuarioMapper.toDto(usuario);
    }

    private Usuario obter(Integer id) {
        return usuarioRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado"));
    }

    private void validarEmail(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepositorio.findByEmail(usuarioDTO.getEmail());
        if(usuario != null && !usuario.getId().equals(usuarioDTO.getId())) {
            throw new RegraNegocioException("Email já cadastrado");
        }
    }

    private void validarCpf(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepositorio.findByCpf(usuarioDTO.getCpf());
        if(usuario != null && !usuario.getId().equals(usuarioDTO.getId())) {
            throw new RegraNegocioException("CPF já cadastrado");
        }
    }

    public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {

        validarEmail(usuarioDTO);
        validarCpf(usuarioDTO);

        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario.setChave(UUID.randomUUID().toString());
        usuarioRepositorio.save(usuario);

        EmailDTO emailDTO = gerarEmailCriarUsuario(usuario);
        produtorServico.enviarEmail(emailDTO);

        return usuarioMapper.toDto(usuario);
    }

    private EmailDTO gerarEmailCriarUsuario(Usuario usuario) {
        EmailDTO emailDTO = new EmailDTO();

        emailDTO.setAssunto("Cadastro de Usuário");
        emailDTO.setCorpo("Você se cadastrou com sucesso na plataforma de eventos, esta é sua chave de inscrição aos eventos: <b>" + usuario.getChave() + "</b>");
        emailDTO.setDestinatario(usuario.getEmail());

        return emailDTO;
    }

    public UsuarioDTO editar(UsuarioDTO usuarioDTO) {

        validarEmail(usuarioDTO);
        validarCpf(usuarioDTO);

        Usuario usuarioSalvo = obter(usuarioDTO.getId());
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario.setChave(usuarioSalvo.getChave());
        usuarioRepositorio.save(usuario);
        return usuarioMapper.toDto(usuario);
    }

    public void remover(Integer id) {
        Usuario usuario = obter(id);
        usuarioRepositorio.delete(usuario);
    }

}
