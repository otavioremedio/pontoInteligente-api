package com.otavio.pontointeligente.api.services;

import java.util.Optional;

import com.otavio.pontointeligente.api.entities.Empresa;

public interface EmpresaService {

	//documentacao
	/**
	 * Retorna uma empresa dado um CNPJ.
	 * 
	 * @param cnpj
	 * @return Optional<Empresa>
	 */
	//não precisa adicionar public, etc
	Optional<Empresa> buscarPorCnpj(String cnpj);
	
	/**
	 * Cadastra uma nova empresa na base de dados.
	 * 
	 * @param empresa
	 * @return Empresa
	 */
	Empresa persistir(Empresa empresa);
	
}
