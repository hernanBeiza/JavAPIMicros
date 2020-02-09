package cl.hiperactivo.javapi.notaserviceapplication.repository;

import cl.hiperactivo.javapi.nota.api.VO.NotaVO;
import cl.hiperactivo.javapi.notaserviceapplication.repository.model.Nota;

import java.util.List;
import java.util.Optional;

public interface NotaDAO {

    List<Nota> obtener();

    Optional<Nota> obtenerConIdNota(Long idNota);

    List<Nota>obtenerConIdUsuario(Long idUsuario);

    Optional<Nota> guardar(NotaVO nota);

    Optional<Nota> editar(NotaVO nota);

    boolean eliminar(Long idNota);
}
