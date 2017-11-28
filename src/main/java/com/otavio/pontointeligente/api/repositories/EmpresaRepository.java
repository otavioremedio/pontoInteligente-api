package com.otavio.pontointeligente.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.otavio.pontointeligente.api.entities.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
	//Transactionl = só consulta, não bloqueia banco
	@Transactional(readOnly = true)
	Empresa findByCnpj(String cnpj);	
}
