package cl.hiperactivo.javapi.usuario.api;

import cl.hiperactivo.javapi.usuario.api.VO.UsuarioVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("usuario")
public interface UsuarioAPI {

    @GetMapping("")
    List<UsuarioVO> obtener();

    @GetMapping("/{idUsuario}")
    UsuarioVO obtenerConIdUsuario(@PathVariable(name = "idUsuario") Long idUsuario);

    @PostMapping("")
    UsuarioVO guardar(@RequestParam(value = "usuario", required = true) String usuario,
                      @RequestParam(value = "contrasena", required = true) String contrasena,
                      @RequestParam(value = "nombre", required = true) String nombre);

    @PutMapping("")
    UsuarioVO editar(@RequestParam(value = "idUsuario", required = true) Long idUsuario,
                     @RequestParam(value = "usuario", required = true) String usuario,
                     @RequestParam(value = "contrasena", required = true) String contrasena,
                     @RequestParam(value = "nombre", required = true) String nombre,
                     @RequestParam(value = "valid", required = true) Long valid);

    @DeleteMapping("/{idUsuario}")
    ResponseEntity<Map<String, Object>> eliminar(@PathVariable(name = "idUsuario") Long idUsuario);
}