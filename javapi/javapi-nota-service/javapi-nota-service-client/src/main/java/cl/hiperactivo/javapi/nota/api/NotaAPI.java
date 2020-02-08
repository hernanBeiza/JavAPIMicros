package cl.hiperactivo.javapi.nota.api;

import cl.hiperactivo.javapi.nota.api.VO.NotaVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("nota")
public interface NotaAPI {

    @GetMapping("")
    List<NotaVO> list();

}
