package cl.hiperactivo.javapi.notaserviceapplication.repository.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "nota")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idnota")
    private Long idNota;
    @Column(name = "idusuario")
    private Long idUsuario;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "cuerpo")
    private String cuerpo;
    @Column(name = "timestamp")
    private Date timestamp;
    @Column(name = "valid")
    private Long valid;

    public Nota() {
    }

    public Long getIdNota() {
        return idNota;
    }

    public void setIdNota(Long idNota) {
        this.idNota = idNota;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Long getValid() {
        return valid;
    }

    public void setValid(Long valid) {
        this.valid = valid;
    }
}