package cl.hiperactivo.javapi.notaserviceapplication.service;

import cl.hiperactivo.javapi.nota.api.VO.NotaVO;

import java.util.List;

public interface NotaService {

    List<NotaVO> obtener();

    NotaVO obtenerConIdNota(Long idNota);

    List<NotaVO> obtenerConIdUsuario(Long idUsuario);

    NotaVO guardar(NotaVO notaVO);

    NotaVO editar(NotaVO notaVO);

    boolean eliminar(Long idNota);
}
