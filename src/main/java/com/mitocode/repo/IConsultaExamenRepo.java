package com.mitocode.repo;

import com.mitocode.model.ConsultaExamen;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IConsultaExamenRepo extends IGenericRepo<ConsultaExamen, Integer>{

	// TODO: 30/01/24 Es un sql nativo las columas deben llamarse tal cual de la bd de lo contrario seria de la clase
	// TODO: 30/01/24 @Modifying -> para q realice la modificacion a la bd de lo contrario no hara 
	//SQL | DML Data Manipulation Language INSERT UPDATE DELETE
//	@Transactional
	@Modifying
	@Query(value = "INSERT INTO consulta_examen(id_consulta, id_examen) VALUES (:idConsulta, :idExamen)", nativeQuery = true)
	Integer registrar(@Param("idConsulta") Integer idConsulta, @Param("idExamen") Integer idExamen);
}
