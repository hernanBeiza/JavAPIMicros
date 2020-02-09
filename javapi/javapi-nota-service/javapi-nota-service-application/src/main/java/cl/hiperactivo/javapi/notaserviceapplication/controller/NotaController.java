package cl.hiperactivo.javapi.notaserviceapplication.controller;

import cl.hiperactivo.javapi.nota.api.NotaAPI;
import cl.hiperactivo.javapi.nota.api.VO.NotaVO;
import cl.hiperactivo.javapi.notaserviceapplication.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class NotaController implements NotaAPI {

    private final NotaService notaService;

    @Autowired
    public NotaController(NotaService notaService) {
        this.notaService = notaService;
    }

    @Override
    public List<NotaVO> obtener() {
        System.out.println("NotaController: obtener();");
        return this.notaService.obtener();
    }

    @Override
    public NotaVO obtenerConIdNota(@PathVariable Long idNota) {
        System.out.println("NotaController: obtenerConIdNota();");
        return this.notaService.obtenerConIdNota(idNota);
    }

    @Override
    public List<NotaVO> obtenerConIdUsuario(@PathVariable Long idUsuario) {
        System.out.println("NotaController: obtenerConIdUsuario();");
        return this.notaService.obtenerConIdUsuario(idUsuario);
    }

    @Override
    public NotaVO guardar(@RequestParam(value = "idUsuario", required = true) Long idUsuario,
                          @RequestParam(value = "titulo", required = true) String titulo,
                          @RequestParam(value = "cuerpo", required = true) String cuerpo){
        System.out.println("NotaController: guardar();");
        NotaVO notaVO = new NotaVO();
        notaVO.setIdUsuario(idUsuario);
        notaVO.setTitulo(titulo);
        notaVO.setCuerpo(cuerpo);
        return this.notaService.guardar(notaVO);
    }

    @Override
    public NotaVO editar(@RequestParam(value = "idUsuario", required = true) Long idUsuario,
                        @RequestParam(value = "idNota", required = true) Long idNota,
                        @RequestParam(value = "titulo", required = true) String titulo,
                        @RequestParam(value = "cuerpo", required = true) String cuerpo,
                        @RequestParam(value = "valid", required = true) Long valid) {
        System.out.println("NotaController: editar();");
        NotaVO notaVO = new NotaVO();
        notaVO.setIdNota(idNota);
        notaVO.setIdUsuario(idUsuario);
        notaVO.setTitulo(titulo);
        notaVO.setCuerpo(cuerpo);
        notaVO.setValid(valid);
        return this.notaService.editar(notaVO);
    }

    @Override
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable(value = "idNota") Long idNota) {
        Map <String, Object> result = new HashMap<>();
        if(this.notaService.eliminar(idNota)){
            result.put("result",true);
            result.put("mensajes","Nota eliminada con éxito");
        } else {
            result.put("result",false);
            result.put("errores","La nota no se pudo eliminar");
        }
        return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
    }
}
