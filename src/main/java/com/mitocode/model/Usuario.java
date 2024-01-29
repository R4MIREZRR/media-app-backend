package com.mitocode.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    private Integer idUsuario;
    @Column(name = "nombre", nullable = false, unique = true)
    private String username;
    @Column(name = "clave", nullable = false)
    private String password;
    @Column(name = "estado", nullable = false)
    private boolean enable;

    // TODO: 29/01/24 Este la forma 2 para representar la implementacio de muchos a muchos y la menos recomendable debido q no te permite realizar muchas cosas o no tiene flexibilidad
    // TODO: 29/01/24  "id_usuario" y "id_rol" -> son los campos de las columnas de la tabla intermedia
    // TODO: 29/01/24 idUsuario y idRol les proporcionara los valores, donde "idUsuario" se lo dara desde la misma clase de donde estamos y "idRol" de la tabla "Rol" por eso se pone inverseJoinColumns y se detalla mas abajo la clase
    // TODO: 29/01/24 Con esta impl ya se crea la tabla intermedia nombre "usuario_rol", aunque No tiene flexibilidad para detallar los primary key, etc, solo haces la referencia de clases y ya nada mas
    // TODO: 29/01/24 (fetch = FetchType.EAGER) -> Aunque LAZY esta por defecto y sirve cuando tienes mucha informacion, pero se usa  "EAGER" cuando hay poca. Significa que cuando recuperas un ROL de la base de datos, JPA también cargará automáticamente los ROLES asociados a ese USUARIO, pero si fuera muchos datos no se recomendaria portemas  de recursos.
    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol",
        joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "idUsuario"),
        inverseJoinColumns = @JoinColumn(name = "id_rol", referencedColumnName = "idRol"))
    private List<Rol> rol;

    public List<Rol> getRol() {
        return rol;
    }

    public void setRol(List<Rol> rol) {
        this.rol = rol;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
