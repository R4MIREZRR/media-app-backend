package com.mitocode.service.impl;

import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.ICRUD;

import java.util.List;

// TODO: 30/01/24  ICRUDImpl para abstraer los metodos en comun del las implementaciones de los services 
public abstract class CRUDImpl<T, ID> implements ICRUD<T, ID> {

	// TODO: 30/01/24 ALRERT! Se debe repasar esto, sobre abstract
	protected abstract IGenericRepo<T, ID> getRepo();
	
	@Override
	public T registrar(T t) throws Exception {
		return getRepo().save(t);
	}

	@Override
	public T modificar(T t) throws Exception {
		return getRepo().save(t);
	}

	@Override
	public List<T> listar() throws Exception {
		return getRepo().findAll();
	}

	@Override
	public T listarPorId(ID id) throws Exception {
		return getRepo().findById(id).orElse(null);
	}

	@Override
	public void eliminar(ID id) throws Exception {
		getRepo().deleteById(id);
	}

}
