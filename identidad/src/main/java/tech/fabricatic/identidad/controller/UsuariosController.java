package tech.fabricatic.identidad.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tech.fabricatic.identidad.service.feign.VotacionesFeignClient;

@Validated
@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/usuarios")
public class UsuariosController {

    private final VotacionesFeignClient votacionesFeignClient;
    

    @GetMapping("feign-working")
    public String feignWorking(){
        return votacionesFeignClient.feignWorking();
    }

    @GetMapping("hello-world")
    public String helloWorld(){
        System.out.println("feign request :)");
        return "hello from identidad microservice";
    }

}
