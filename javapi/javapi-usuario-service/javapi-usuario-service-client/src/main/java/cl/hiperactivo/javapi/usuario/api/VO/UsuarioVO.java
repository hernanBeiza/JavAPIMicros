package cl.hiperactivo.javapi.usuario.api.VO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class UsuarioVO implements Serializable {

    private Long  idUsuario;
    private String  usuario;
    @JsonIgnore
    private String  contrasena;
    private String  nombre;
    private Long  valid;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getValid() {
        return valid;
    }


    public void setValid(Long valid) {
        this.valid = valid;
    }
}
