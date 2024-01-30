package com.mitocode.service;

import com.mitocode.model.Medico;

import java.util.List;

public interface IMedicoService {

    Medico registrar (Medico p) throws Exception;
    Medico modificar (Medico p) throws Exception;
    List<Medico> listar () throws Exception;
    Medico listarPorId (Integer id) throws Exception;
    void eliminar (Integer id) throws Exception;


}
