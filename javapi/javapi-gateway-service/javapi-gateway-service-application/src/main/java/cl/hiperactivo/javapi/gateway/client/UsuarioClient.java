package cl.hiperactivo.javapi.gateway.client;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;

import cl.hiperactivo.javapi.usuario.api.UsuarioAPI;

@FeignClient("usuario-service")
@RibbonClient("usuario-service")
public interface UsuarioClient extends UsuarioAPI {
}
