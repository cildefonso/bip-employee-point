package com.bip.api.repositories;

import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.bip.api.domain.model.Employee;
import com.bip.api.domain.model.Launch;
import com.bip.api.domain.model.User;
import com.bip.api.domain.repository.LancamentoRepository;
import com.bip.api.enums.PerfilEnum;
import com.bip.api.enums.TipoEnum;
import com.bip.api.utils.SenhaUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LancamentoRepositoryTest {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	private String funcionarioId;

//	@Before
//	public void setUp() throws Exception {
//		//Empresa empresa = this.empresaRepository.save(obterDadosEmpresa());
//		
//		Funcionario funcionario = this.funcionarioRepository.save(obterDadosFuncionario(empresa));
//		this.funcionarioId = funcionario.getId();
//		
//		this.lancamentoRepository.save(obterDadosLancamentos(funcionario));
//		this.lancamentoRepository.save(obterDadosLancamentos(funcionario));
//	}

//	@After
//	public void tearDown() throws Exception {
//		this.empresaRepository.deleteAll();
//	}

	@Test
	public void testBuscarLancamentosPorFuncionarioId() {
		List<Employee> lancamentos = this.lancamentoRepository.findBy_id(funcionarioId);
		
		assertEquals(2, lancamentos.size());
	}
	
	@Test
	public void testBuscarLancamentosPorFuncionarioIdPaginado() {
		PageRequest page = new PageRequest(0, 10);
		Page<Employee> lancamentos = this.lancamentoRepository.findBy_id(funcionarioId, page);
		
		assertEquals(2, lancamentos.getTotalElements());
	}
	
	private Launch obterDadosLancamentos(String cpf) {
		Launch lancameto = new Launch();
		lancameto.setDatelaunch(new Date());
		lancameto.setTypelauch(TipoEnum.INICIO_ALMOCO);
	//	lancameto.setCpf(cpf);
		return lancameto;
	}

	private User obterDadosFuncionario() throws NoSuchAlgorithmException {
		User user = new User();
		
		user.setUserTypeAccess(PerfilEnum.ROLE_USUARIO);
		user.setPassword(SenhaUtils.gerarBCrypt("123456"));
		user.setEmail("email@email.com");
		return user;
	}

//	private Empresa obterDadosEmpresa() {
//		Empresa empresa = new Empresa();
//		empresa.setRazaoSocial("Empresa de exemplo");
//		empresa.setCnpj("51463645000100");
//		return empresa;
//	}

}
