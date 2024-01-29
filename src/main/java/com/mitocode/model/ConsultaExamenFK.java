package com.mitocode.model;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ConsultaExamenFK implements Serializable {

    private static final long serialVersionUID = 1L;


    // TODO: 29/01/24 @Embeddable -> para q sea leida para otras clases, es para decirle que ConsultaExamenFK podra ser usada por otra  y que pertenece a una impl de llaves compuestas
    // TODO: 29/01/24 Al no tener Entity no se serealiza por ello debes hacerlo mnualmente. Recordad q esto sirve para q la informacion viaje de java a la bd y permita comunicarse
    // TODO: 29/01/24 ConsultaExamenFK -> sirve para hacer los select , habra otra procedimiento parainsertarlos 
    @Id
    @JoinColumn(name = "id_consulta", nullable = false)
    private Consulta consulta;
    @Id
    @JoinColumn(name = "id_examen", nullable = false)
    private Examen examen;

    // TODO: 29/01/24 Equals hashCode  consulta y examen -> es para comparar las dos clases contra este, donde tenegan ambos un campo en comun de comparacion

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsultaExamenFK that = (ConsultaExamenFK) o;
        return Objects.equals(consulta, that.consulta) && Objects.equals(examen, that.examen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consulta, examen);
    }
}
