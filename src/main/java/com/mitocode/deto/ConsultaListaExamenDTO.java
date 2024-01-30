package com.mitocode.deto;

import com.mitocode.model.Consulta;
import com.mitocode.model.Examen;

import javax.validation.constraints.NotNull;
import java.util.List;

// TODO: 30/01/24 no es una entidad por eso no se agrega @Entity solo es una clase de apoyo 
public class ConsultaListaExamenDTO {

	// TODO: 30/01/24 	@NotNull -> no solo se usa en los models 
	// TODO: 30/01/24 notnull -> porq no es obligatorio 
	@NotNull
	private Consulta consulta;
	
	@NotNull
	private List<Examen> lstExamen;
	
	public Consulta getConsulta() {
		return consulta;
	}
	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}
	public List<Examen> getLstExamen() {
		return lstExamen;
	}
	public void setLstExamen(List<Examen> lstExamen) {
		this.lstExamen = lstExamen;
	}
	
	
}
