package com.otavio.pontointeligente.api.repositories;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.otavio.pontointeligente.api.entities.Empresa;
import com.otavio.pontointeligente.api.entities.Funcionario;
import com.otavio.pontointeligente.api.entities.Lancamento;
import com.otavio.pontointeligente.api.enums.PerfilEnum;
import com.otavio.pontointeligente.api.enums.TipoEnum;
import com.otavio.pontointeligente.api.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LancamentoRepositoryTest{
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	private Long funcionarioId;
	
	@Before
	public void setUp() throws Exception{
		Empresa empresa = this.empresaRepository.save(obterDadosEmpresa());
		Funcionario funcionario = this.funcionarioRepository.save(obterDadosFuncionario(empresa));		
		this.funcionarioId = funcionario.getId();
		
		this.lancamentoRepository.save(obterDadosLancamentos(funcionario));
		this.lancamentoRepository.save(obterDadosLancamentos(funcionario));
	}
	
	@After
	public final void tearDown(){
		this.empresaRepository.deleteAll();
		this.funcionarioRepository.deleteAll();
		this.lancamentoRepository.deleteAll();
	}
	
	@Test
	public void testBuscarLancamentosPorFuncionarioId(){
		List<Lancamento> lancamentos = this.lancamentoRepository.findByFuncionarioId(funcionarioId);
		assertEquals(2, lancamentos.size());
	}
	
	@Test
	public void testBuscarLancamentosPorFuncionarioIdPaginado(){
		PageRequest page = new PageRequest(0, 10);
		Page<Lancamento> lancamentos = this.lancamentoRepository.findByFuncionarioId(funcionarioId, page);
		assertEquals(2, lancamentos.getTotalElements());
	}
	
	
	private Lancamento obterDadosLancamentos(Funcionario funcionario){
		Lancamento lancamento = new Lancamento();
		lancamento.setData(new Date());
		lancamento.setDescricao("teste" + new Random().nextInt());
		lancamento.setFuncionario(funcionario);
		lancamento.setLocalizacao("sei la");
		lancamento.setTipo(TipoEnum.INICIO_TRABALHO);
		
		return lancamento;
	}
	
	private Funcionario obterDadosFuncionario(Empresa empresa){
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Fulano de tal");
		funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
		funcionario.setSenha(PasswordUtils.geraBCrypt("123456"));
		funcionario.setCpf("28669696999");
		funcionario.setEmail("xupisco@com.br");
		funcionario.setEmpresa(empresa);
		
		return funcionario;
	}
	
	private Empresa obterDadosEmpresa(){
		Empresa empresa = new Empresa();
		empresa.setRazaoSocial("Empresa de exemplo");
		empresa.setCnpj("51463645000100");
		return empresa;
	}
}
