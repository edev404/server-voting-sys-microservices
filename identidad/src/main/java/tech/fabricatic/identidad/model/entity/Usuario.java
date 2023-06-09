package tech.fabricatic.identidad.model.entity;


import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.fabricatic.identidad.model.details.Authority;
import tech.fabricatic.identidad.model.details.UserType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usuarios", indexes = @Index(name="usuarios_unique", columnList = "username, idCentro", unique = true))
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String nombre;

    @Column(length = 100)
    private String apellido;

    @Column(length = 30)
    private String username;

    @Column(length = 250)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Enumerated(EnumType.STRING)
	private Authority authority;

    @Lob
    @Column(nullable = true)
    private String userData;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "idCentro")
    private Centro centro;

    @ColumnDefault(value = "true")
    private Boolean enabled;

    @ColumnDefault(value = "true")
    private Boolean accountNonLocked;

    @ColumnDefault(value = "true")
    private Boolean credentialsNonExpired;

    @ColumnDefault(value = "true")
    private Boolean accountNonExpired;

    @CreationTimestamp
    private LocalDateTime creationDateTime;

    @UpdateTimestamp
    private LocalDateTime lastModified;
   
}