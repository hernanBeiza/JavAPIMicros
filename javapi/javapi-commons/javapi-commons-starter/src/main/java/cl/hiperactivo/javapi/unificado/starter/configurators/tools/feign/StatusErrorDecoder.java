package cl.hiperactivo.javapi.unificado.starter.configurators.tools.feign;


import cl.hiperactivo.javapi.unificado.utils.util.ErrorBody;
import cl.hiperactivo.javapi.unificado.utils.util.JSONHelper;
import feign.Response;
import feign.Response.Body;
import feign.codec.ErrorDecoder;
import java.io.Reader;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class StatusErrorDecoder implements ErrorDecoder {

  @Override
  public Exception decode(String s, Response response) {
    Body body = response.body();
    try (Reader reader = body.asReader()) {
      ErrorBody restResponse = JSONHelper.fromJSON(IOUtils.toString(reader), ErrorBody.class);
      return new ResponseStatusException(HttpStatus.valueOf(response.status()),
          restResponse != null ? restResponse.getMessage() : "Server Error");
    } catch (Exception e) {
      return new ResponseStatusException(HttpStatus.valueOf(response.status()),
          "Unexpected");
    }
  }


}
