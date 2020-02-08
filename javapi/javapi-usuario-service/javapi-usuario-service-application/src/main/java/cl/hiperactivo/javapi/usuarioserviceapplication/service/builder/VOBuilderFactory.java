package cl.hiperactivo.javapi.usuarioserviceapplication.service.builder;

import cl.hiperactivo.javapi.usuarioserviceapplication.repository.model.Usuario;

public class VOBuilderFactory {

    public VOBuilderFactory() {
    }

    public static UsuarioVOBuilder getUsuarioVOBuilder(Usuario usuario) {
        return new UsuarioVOBuilder().fromUsuario(usuario);
    }

}
