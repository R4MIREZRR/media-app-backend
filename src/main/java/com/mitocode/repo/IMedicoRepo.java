package com.mitocode.repo;

import com.mitocode.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface IMedicoRepo extends JpaRepository<Medico, Integer> {
}
