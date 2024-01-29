package com.mitocode.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "menu")
public class Menu {


    @Id
    private Integer idMenu;
    @Column(name = "icono", length = 20)
    private String icono;
    @Column(name = "nombre", length = 20)
    private String nombre;
    @Column(name = "url", length = 50)
    private String url;

    // TODO: 29/01/24 (fetch = FetchType.EAGER) -> Aunque LAZY esta por defecto y sirve cuando tienes mucha informacion, pero se usa  "EAGER" cuando hay poca. Significa que cuando recuperas un Menu de la base de datos, JPA también cargará automáticamente los ROLES asociados a ese menu, pero si fuera muchos datos no se recomendaria por temas  de recursos.
    // TODO: 29/01/24 Es similiar a USUARIO
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "menu_rol",
            joinColumns = @JoinColumn(name = "id_menu", referencedColumnName = "idMenu"),
            inverseJoinColumns = @JoinColumn(name = "id_rol", referencedColumnName = "idRol"))
    private List<Rol> roles;

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
