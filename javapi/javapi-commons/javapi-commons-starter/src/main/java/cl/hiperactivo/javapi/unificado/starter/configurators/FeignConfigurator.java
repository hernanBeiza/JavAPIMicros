package cl.hiperactivo.javapi.unificado.starter.configurators;

import cl.hiperactivo.javapi.unificado.starter.configurators.tools.feign.StatusErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfigurator extends FeignClientsConfiguration {

  @Autowired
  private ObjectFactory<HttpMessageConverters> messageConverters;


  @Bean
  public ErrorDecoder errorDecoder() {
    return new StatusErrorDecoder();
  }


}
