package cl.hiperactivo.javapi.notaserviceapplication.controller;

import cl.hiperactivo.javapi.nota.api.NotaAPI;
import cl.hiperactivo.javapi.nota.api.VO.NotaVO;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotaController implements NotaAPI {

    @Override
    public List<NotaVO> list() {
        System.out.println("NotaController: list(); Obtener lista de notas");
        return null;
    }

}
