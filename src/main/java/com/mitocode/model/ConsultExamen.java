package com.mitocode.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "consulta_examen")
@IdClass(ConsultaExamenFK.class)
public class ConsultExamen {

    // TODO: 29/01/24 Este es una forma 1, para representar la implementacio de muchos a muchos donde exista una tabla intermedia y este se cree de manera manual 
    // TODO: 29/01/24 Se podria dejar con (private Integer consulta...) pero se crea una clase mas "ConsultaExamenFK" para que cuando se trate, este pueda devolver el contenido de ambas y se mas facil las consultas, tambien para evitar q una clase este dentro de otra
    // TODO: 29/01/24 @IdClass(ConsultaExamenFK.class) -> es para decirle q quien lo esta definiendo a consultaExamen es esa clase
    @Id
    private Consulta consulta;
    @Id
    private Examen examen;
}
