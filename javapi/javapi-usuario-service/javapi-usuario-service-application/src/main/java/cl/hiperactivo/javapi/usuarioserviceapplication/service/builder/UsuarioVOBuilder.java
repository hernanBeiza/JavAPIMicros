package cl.hiperactivo.javapi.usuarioserviceapplication.service.builder;

import cl.hiperactivo.javapi.usuario.api.VO.UsuarioVO;
import cl.hiperactivo.javapi.usuarioserviceapplication.repository.model.Usuario;

public class UsuarioVOBuilder {

    private Usuario usuario;


    UsuarioVOBuilder(){ }

    public UsuarioVOBuilder fromUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public UsuarioVO build() {
        if (usuario == null) {
            return null;
        }
        UsuarioVO usuarioVO = new UsuarioVO();
        usuarioVO.setIdUsuario(usuario.getIdUsuario());
        usuarioVO.setNombre(usuario.getNombre());
        usuarioVO.setUsuario(usuario.getUsuario());
        usuarioVO.setContrasena(usuario.getContrasena());
        usuarioVO.setValid(usuario.getValid());
        return usuarioVO;
    }

}
