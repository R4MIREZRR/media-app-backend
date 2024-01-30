package com.mitocode.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

// TODO: 30/01/24 @NoRepositoryBean -> sin esto te dara error de objetc ya q no esta definido el 'T'
@NoRepositoryBean
public interface IGenericRepo<T, ID> extends JpaRepository<T, ID>{
	
}
