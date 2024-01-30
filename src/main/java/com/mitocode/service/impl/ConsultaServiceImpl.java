package com.mitocode.service.impl;

import com.mitocode.deto.ConsultaListaExamenDTO;
import com.mitocode.model.Consulta;
import com.mitocode.repo.IConsultaExamenRepo;
import com.mitocode.repo.IConsultaRepo;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.IConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConsultaServiceImpl extends CRUDImpl<Consulta, Integer> implements IConsultaService {

	@Autowired
	private IConsultaRepo repo;

	@Autowired
	private IConsultaExamenRepo ceRepo;

	@Override
	protected IGenericRepo<Consulta, Integer> getRepo() {
		return repo;
	}

	// TODO: 30/01/24  1:13
// TODO: 30/01/24 @Transactional  -> si uno no se registra no se registra nada, representa como un bloque el metodo 
	@Transactional
	@Override
	public Consulta registrarTransaccional(ConsultaListaExamenDTO dto) {

		/*for(DetalleConsulta det : consulta.getDetalleConsulta()) {
			det.setConsulta(consulta);
		}*/
		dto.getConsulta().getDetalleConsulta().forEach(det -> det.setConsulta(dto.getConsulta()));

		repo.save(dto.getConsulta());

		dto.getLstExamen().forEach(ex -> ceRepo.registrar(dto.getConsulta().getIdConsulta(), ex.getIdExamen()));

		return dto.getConsulta();
	}



}
