package tech.fabricatic.identidad.model.details;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import tech.fabricatic.identidad.model.entity.Usuario;

public class UserData {

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @JoinColumn(name="idUsuario")
    private Usuario usuario;

    @Column(length = 15)
    private String celular;

    @Column(length = 250)
    private String correoElectronico;

    @Column(length = 20)
    private String ficha;

    @Column(length = 250)
    private String programa;
    
}
