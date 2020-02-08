package cl.hiperactivo.javapi.usuarioserviceapplication.service;

import cl.hiperactivo.javapi.usuario.api.VO.UsuarioVO;

import java.util.List;

public interface UsuarioService {

    List<UsuarioVO> obtener();

    UsuarioVO obtenerConIdUsuario(Long idUsuario);

    UsuarioVO guardar(UsuarioVO usuarioVO);

    UsuarioVO editar(UsuarioVO usuarioVO);

    boolean eliminar(Long idUsuario);

}
