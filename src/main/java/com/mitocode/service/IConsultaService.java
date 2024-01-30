package com.mitocode.service;

import com.mitocode.deto.ConsultaListaExamenDTO;
import com.mitocode.model.Consulta;

public interface IConsultaService extends ICRUD<Consulta, Integer> {

    // TODO: 30/01/24  
    Consulta registrarTransaccional(ConsultaListaExamenDTO consulta);

}
