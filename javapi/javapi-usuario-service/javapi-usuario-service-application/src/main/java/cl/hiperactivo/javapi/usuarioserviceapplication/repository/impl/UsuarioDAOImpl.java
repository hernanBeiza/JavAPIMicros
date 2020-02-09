package cl.hiperactivo.javapi.usuarioserviceapplication.repository.impl;

import cl.hiperactivo.javapi.usuario.api.VO.UsuarioVO;
import cl.hiperactivo.javapi.usuarioserviceapplication.repository.UsuarioDAO;
import cl.hiperactivo.javapi.usuarioserviceapplication.repository.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioDAOImpl implements UsuarioDAO {

    private final EntityManager entityManager;

    @Autowired
    public UsuarioDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<Usuario> obtener() {
        return this.entityManager.createQuery("from Usuario", Usuario.class).getResultList();
    }

    @Override
    @Transactional
    public Optional<Usuario> obtenerConIdUsuario(@NotNull Long idUsuario) {
        Usuario usuario = this.entityManager.find(Usuario.class,idUsuario);
        return Optional.ofNullable(usuario);
    }

    @Override
    @Transactional
    public Optional<Usuario> guardar(UsuarioVO usuarioVO) {
        Usuario usuario = new Usuario();
        usuario.setUsuario(usuarioVO.getUsuario());
        usuario.setContrasena(usuarioVO.getContrasena());
        usuario.setNombre(usuarioVO.getNombre());
        usuario.setValid(Long.valueOf(2));
        try {
            this.entityManager.persist(usuario);
        } catch(Exception e){
            System.out.println(e.getLocalizedMessage());
        }
        return Optional.ofNullable(usuario);
    }

    @Override
    @Transactional
    public Optional<Usuario> editar(UsuarioVO usuarioVO) {
        Optional<Usuario> usuarioExiste = this.obtenerConIdUsuario(usuarioVO.getIdUsuario());
        if(usuarioExiste.isPresent()){
            Usuario usuario = usuarioExiste.get();
            usuario.setIdUsuario(usuarioVO.getIdUsuario());
            usuario.setUsuario(usuarioVO.getUsuario());
            usuario.setContrasena(usuarioVO.getContrasena());
            usuario.setNombre(usuarioVO.getNombre());
            usuario.setValid(usuarioVO.getValid());
            try {
                this.entityManager.merge(usuario);
                return Optional.ofNullable(usuario);
            } catch(Exception e){
                System.out.println(e.getLocalizedMessage());
            }
        }
        return usuarioExiste;
    }

    @Override
    @Transactional
    public boolean eliminar(Long idUsuario){
        Optional<Usuario> usuario = this.obtenerConIdUsuario(idUsuario);
        if(usuario.isPresent()){
            try {
                this.entityManager.remove(usuario.get());
                return true;
            } catch(Exception e){
                System.out.println(e.getLocalizedMessage());
                return false;
            }
        } else {
            return false;
        }
    }
}
