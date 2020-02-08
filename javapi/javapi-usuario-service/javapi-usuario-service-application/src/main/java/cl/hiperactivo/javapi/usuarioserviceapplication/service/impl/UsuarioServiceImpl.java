package cl.hiperactivo.javapi.usuarioserviceapplication.service.impl;

import cl.hiperactivo.javapi.usuario.api.VO.UsuarioVO;
import cl.hiperactivo.javapi.usuarioserviceapplication.repository.UsuarioDAO;
import cl.hiperactivo.javapi.usuarioserviceapplication.repository.model.Usuario;
import cl.hiperactivo.javapi.usuarioserviceapplication.service.UsuarioService;
import cl.hiperactivo.javapi.usuarioserviceapplication.service.builder.UsuarioVOBuilder;
import cl.hiperactivo.javapi.usuarioserviceapplication.service.builder.VOBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioDAO usuarioDAO;

    @Autowired
    public UsuarioServiceImpl(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public List<UsuarioVO> obtener() {
        System.out.println("UsuarioServiceImpl: obtener();");
        List<Usuario> usuarios = this.usuarioDAO.obtener();
        return this.usuarioDAO.obtener().stream().map(VOBuilderFactory::getUsuarioVOBuilder)
                .map(UsuarioVOBuilder::build).collect(Collectors.toList());
    }

    @Override
    public UsuarioVO obtenerConIdUsuario(Long idUsuario) {
        System.out.println("UsuarioServiceImpl: obtenerConIdUsuario();");
        return this.usuarioDAO.obtenerConIdUsuario(idUsuario).map(VOBuilderFactory::getUsuarioVOBuilder)
            .map(UsuarioVOBuilder::build).orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, String.format("Usuario con idUsuario %d no existe", idUsuario)));
    }

    @Override
    public UsuarioVO guardar(UsuarioVO usuarioVO) {
        System.out.println("UsuarioServiceImpl: guardar();");
        return this.usuarioDAO.guardar(usuarioVO).map(VOBuilderFactory::getUsuarioVOBuilder)
                .map(UsuarioVOBuilder::build).orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, String.format("No se pudo guardar usuario")));
    }

    @Override
    public UsuarioVO editar(UsuarioVO usuarioVO) {
        System.out.println("UsuarioServiceImpl: editar();");
        return this.usuarioDAO.editar(usuarioVO).map(VOBuilderFactory::getUsuarioVOBuilder)
            .map(UsuarioVOBuilder::build).orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, String.format("No se pudo editar usuario con id %d",usuarioVO.getIdUsuario())));
    }

    @Override
    public boolean eliminar(Long idUsuario) {
        return this.usuarioDAO.eliminar(idUsuario);
    }
}
