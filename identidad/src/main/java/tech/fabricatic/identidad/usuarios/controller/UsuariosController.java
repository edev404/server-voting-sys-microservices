package tech.fabricatic.identidad.usuarios.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/usuarios")
public class UsuariosController {
    
    @GetMapping("working")
    public ResponseEntity<String> handleServiceIsWorking(){
        return ResponseEntity.status(HttpStatus.OK).body("Service is working");
    }

}
