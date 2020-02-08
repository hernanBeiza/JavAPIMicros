package cl.hiperactivo.javapi.gateway.client;

import cl.hiperactivo.javapi.nota.api.NotaAPI;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("nota-service")
@RibbonClient("nota-service")
public interface NotaClient extends NotaAPI {
}
