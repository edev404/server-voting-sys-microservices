package tech.edev404.votos.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient("votaciones")
public interface VotacionesFeignClient {

    @RequestMapping(method = RequestMethod.GET, value="api/v1/votaciones/exist-by-id", consumes = "application/json")
    ResponseEntity<Boolean> handleVotacionExistById(@RequestBody Integer idVotacion);
    
}
