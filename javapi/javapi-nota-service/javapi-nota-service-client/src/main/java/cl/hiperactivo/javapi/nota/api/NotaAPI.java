package cl.hiperactivo.javapi.nota.api;

import cl.hiperactivo.javapi.nota.api.VO.NotaVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("nota")
public interface NotaAPI {

    @GetMapping("")
    List<NotaVO> obtener();

    @GetMapping("/{idNota}")
    NotaVO obtenerConIdNota(@PathVariable(name = "idNota") Long idNota);

    @GetMapping("/usuario/{idUsuario}")
    List<NotaVO> obtenerConIdUsuario(@PathVariable(name="idUsuario") Long idUsuario);

    @PostMapping("")
    NotaVO guardar(@RequestParam(value = "idUsuario", required = true) Long idUsuario,
                   @RequestParam(value = "titulo", required = true) String titulo,
                   @RequestParam(value = "cuerpo", required = true) String cuerpo);

    @PutMapping("")
    NotaVO editar(@RequestParam(value = "idUsuario", required = true) Long idUsuario,
                  @RequestParam(value = "idNota", required = true) Long idNota,
                  @RequestParam(value = "titulo", required = true) String titulo,
                  @RequestParam(value = "cuerpo", required = true) String cuerpo,
                  @RequestParam(value = "valid", required = true) Long valid);

    @DeleteMapping("/{idNota}")
    ResponseEntity<Map<String, Object>> eliminar(@PathVariable(name = "idNota") Long idNota);
}
