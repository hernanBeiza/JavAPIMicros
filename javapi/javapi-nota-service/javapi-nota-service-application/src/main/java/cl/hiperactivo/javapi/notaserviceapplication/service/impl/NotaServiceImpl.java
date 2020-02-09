package cl.hiperactivo.javapi.notaserviceapplication.service.impl;

import cl.hiperactivo.javapi.nota.api.VO.NotaVO;
import cl.hiperactivo.javapi.notaserviceapplication.repository.NotaDAO;
import cl.hiperactivo.javapi.notaserviceapplication.service.NotaService;
import cl.hiperactivo.javapi.notaserviceapplication.service.builder.NotaVOBuilder;
import cl.hiperactivo.javapi.notaserviceapplication.service.builder.VOBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotaServiceImpl implements NotaService {

    private final NotaDAO notaDAO;

    @Autowired
    public NotaServiceImpl(NotaDAO notaDAO) {
        this.notaDAO = notaDAO;
    }

    @Override
    public List<NotaVO> obtener() {
        return this.notaDAO.obtener().stream().map(VOBuilderFactory::getNotaVOBuilder)
                .map(NotaVOBuilder::build).collect(Collectors.toList());
    }

    @Override
    public NotaVO obtenerConIdNota(Long idNota) {
        System.out.println("NotaServiceImpl: obtenerConIdNota();");
        return this.notaDAO.obtenerConIdNota(idNota).map(VOBuilderFactory::getNotaVOBuilder)
                .map(NotaVOBuilder::build).orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, String.format("Nota con idNota %d no existe", idNota)));
    }

    @Override
    public List<NotaVO> obtenerConIdUsuario(Long idUsuario) {
        System.out.println("NotaServiceImpl: obtenerConIdUsuario();");
        return this.notaDAO.obtenerConIdUsuario(idUsuario).stream().map(VOBuilderFactory::getNotaVOBuilder)
                .map(NotaVOBuilder::build).collect(Collectors.toList());
    }

    @Override
    public NotaVO guardar(NotaVO notaVO) {
        return this.notaDAO.guardar(notaVO).map(VOBuilderFactory::getNotaVOBuilder)
                .map(NotaVOBuilder::build).orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, String.format("No se pudo guardar usuario")));

    }

    @Override
    public NotaVO editar(NotaVO notaVO) {
        return this.notaDAO.editar(notaVO).map(VOBuilderFactory::getNotaVOBuilder)
                .map(NotaVOBuilder::build).orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, String.format("No se pudo guardar usuario")));
    }

    @Override
    public boolean eliminar(Long idNota) {
        return this.notaDAO.eliminar(idNota);
    }
}
