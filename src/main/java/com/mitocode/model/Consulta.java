package com.mitocode.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConsulta;
    // TODO: 28/01/24 Paciente tiene muchas consultas y Una consulta tiene 1 paciente
    // TODO: 28/01/24 Cuando tengo una llave foranea es manyToOne
    // TODO: 28/01/24 idPaciente es la columna para la tabla de consulta y no del id de la tabla Paciente
    // TODO: 28/01/24 Hacen match porq paciente es Entity y busca el id  y lo hace automatico sin hacerlo manualmente
    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false, foreignKey = @ForeignKey(name = "FK_consulta_paciente"))
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_medico", nullable = false, foreignKey = @ForeignKey(name = "FK_consulta_medico"))
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "id_especialidad", nullable = false, foreignKey = @ForeignKey(name = "FK_consulta_especialidad"))
    private Especialidad especialidad;

    @Column(name = "num_consultorio", nullable = true, length = 3)
    private String numConsultorio;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    // TODO: 29/01/24 No va alterar la tabla ni estructura ni nada a nivel de bd, solo es para q internamente apoye al operar con ella en java
    // TODO: 29/01/24 Una consulta tiene muchos detalles: OneToMany
    // TODO: 29/01/24 mappedBy = "consulta"  -> Hace referencia u match a la propiedad o variable "consulta" que esta dentro de la clase de detalleConsulta
    // TODO: 29/01/24  cascade = {CascadeType.ALL} -> Sun una unicidad, Para q lo q le pase a la cabecera le pase al detalle y sea integro y no este suelto en caso de eliminar (si eliminas uso eliminas el otro), insertar ,etc.  - MERGE es para actualizaciones, DETACHE es para eliminar un elemento, REMOVE  es para eliminar todos
    // TODO: 29/01/24 orphanRemoval = true -> Por defecto es false. Si es true, cuando quieras eliminar un elemento dentro del detalle no te va dejar porq sino se quedara huerfano, si se elimina uno se eliminan todo. Lo que le pase a consulta le pasa a detalle.
    // TODO: 29/01/24 Borrado logico es un campo adicional para dar activar o desactivar y cuando le haces la consulta solo te muestra los activos, es para un tema de seguridad para q no se borren toda la tabla.
    @OneToMany(mappedBy = "consulta", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<DetalleConsulta> detalleConsulta;


    public List<DetalleConsulta> getDetalleConsulta() {
        return detalleConsulta;
    }

    public void setDetalleConsulta(List<DetalleConsulta> detalleConsulta) {
        this.detalleConsulta = detalleConsulta;
    }

    public Integer getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Integer idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public String getNumConsultorio() {
        return numConsultorio;
    }

    public void setNumConsultorio(String numConsultorio) {
        this.numConsultorio = numConsultorio;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    // TODO: 29/01/24 Equals hashCode idConsulta -> es para comparar dos clases donde tenegan ambos un campo en comun de comparacion  
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consulta consulta = (Consulta) o;
        return Objects.equals(idConsulta, consulta.idConsulta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idConsulta);
    }
}
