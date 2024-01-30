package com.mitocode.service.impl;

import com.mitocode.model.Paciente;
import com.mitocode.repo.IPacienteRepo;
import com.mitocode.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements IPacienteService {

    @Autowired
    private IPacienteRepo repo;


    @Override
    public Paciente registrar(Paciente p) throws Exception {
        return repo.save(p);
    }

    @Override
    public Paciente modificar(Paciente p) throws Exception {
        return repo.save(p);
    }

    @Override
    public List<Paciente> listar() throws Exception {
        return repo.findAll();
    }

    @Override
    public Paciente listarPorId(Integer id) throws Exception {
        Optional<Paciente> op = repo.findById(id);
        return op.isPresent() ? op.get() : new Paciente();
    }

    @Override
    public void eliminar(Integer id) throws Exception {
        repo.deleteById(id);
    }
}
