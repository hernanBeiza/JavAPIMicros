package cl.hiperactivo.javapi.usuarioserviceapplication.controller;

import cl.hiperactivo.javapi.usuario.api.UsuarioAPI;
import cl.hiperactivo.javapi.usuario.api.VO.UsuarioVO;
import cl.hiperactivo.javapi.usuarioserviceapplication.service.UsuarioService;
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
public class UsuarioController implements UsuarioAPI {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public List<UsuarioVO> obtener() {
        System.out.println("UsuarioController: obtener(); Obtener lista de usuarios");
        return this.usuarioService.obtener();
    }

    @Override
    public UsuarioVO obtenerConIdUsuario(@PathVariable(name = "idUsuario") Long idUsuario){
        System.out.println("UsuarioController: obtenerConIdUsuario(); Obtener lista de usuarios");
        return this.usuarioService.obtenerConIdUsuario(idUsuario);
    }

    @Override
    public UsuarioVO guardar(@RequestParam(value = "usuario", required = true) String usuario,
                             @RequestParam(value = "contrasena", required = true) String contrasena,
                             @RequestParam(value = "nombre", required = true) String nombre) {
        System.out.println("UsuarioController: guardar(); Guardar un usuario");
        UsuarioVO usuarioVO = new UsuarioVO();
        usuarioVO.setUsuario(usuario);
        usuarioVO.setContrasena(contrasena);
        usuarioVO.setNombre(nombre);
        return this.usuarioService.guardar(usuarioVO);
    }

    @Override
    public UsuarioVO editar(@RequestParam(value = "idUsuario", required = true) Long idUsuario,
                            @RequestParam(value = "usuario", required = true) String usuario,
                            @RequestParam(value = "contrasena", required = true) String contrasena,
                            @RequestParam(value = "nombre", required = true) String nombre,
                            @RequestParam(value = "valid", required = true) Long valid) {
        System.out.println("UsuarioController: editar(); Editar un usuario");
        UsuarioVO usuarioVO = new UsuarioVO();
        usuarioVO.setIdUsuario(idUsuario);
        usuarioVO.setUsuario(usuario);
        usuarioVO.setContrasena(contrasena);
        usuarioVO.setNombre(nombre);
        usuarioVO.setValid(valid);
        return this.usuarioService.editar(usuarioVO);
    }

    @Override
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable(value = "idUsuario") Long idUsuario) {
        Map <String, Object> result = new HashMap<>();
        if(this.usuarioService.eliminar(idUsuario)){
            result.put("result",true);
            result.put("mensajes","Usuario eliminado con éxito");
        } else {
            result.put("result",false);
            result.put("errores","El usuario no se pudo eliminar");
        }
        return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
    }

}
