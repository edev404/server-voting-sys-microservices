package tech.fabricatic.votaciones.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("identidad")
public interface IdentidadFeignClient {

    @RequestMapping(method = RequestMethod.GET, path = "api/v1/usuarios/hello-world", consumes = "application/json")
    public String feignWorking();
    
}
