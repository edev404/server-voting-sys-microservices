package tech.edev404.votos.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tech.edev404.votos.model.Candidato;

@FeignClient("candidatos")
public interface CandidatosFeignClient {
    
    @RequestMapping(method = RequestMethod.GET, value="api/v1/candidatos/exist-by-id", consumes = "application/json")
    ResponseEntity<Boolean> handleCandidatoExistById(@RequestBody Candidato candidatoFeign);

}
