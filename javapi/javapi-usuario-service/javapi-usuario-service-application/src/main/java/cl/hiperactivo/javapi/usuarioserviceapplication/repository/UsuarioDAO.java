package cl.hiperactivo.javapi.usuarioserviceapplication.repository;

import cl.hiperactivo.javapi.usuario.api.VO.UsuarioVO;
import cl.hiperactivo.javapi.usuarioserviceapplication.repository.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioDAO {

    List<Usuario> obtener();

    Optional<Usuario> obtenerConIdUsuario(Long idUsuario);

    Optional<Usuario> guardar(UsuarioVO usuarioVO);

    Optional<Usuario> editar(UsuarioVO usuarioVO);

    boolean eliminar(Long idUsuario);

}
