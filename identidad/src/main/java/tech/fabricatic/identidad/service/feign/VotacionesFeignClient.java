package tech.fabricatic.identidad.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("votaciones")
public interface VotacionesFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "api/v1/votos/hello-world", consumes = "application/json")
    public String feignWorking();
    
}
