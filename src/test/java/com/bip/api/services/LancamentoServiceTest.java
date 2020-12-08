package com.bip.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.bip.api.domain.model.Employee;
import com.bip.api.domain.model.Launch;
import com.bip.api.domain.repository.LancamentoRepository;
import com.bip.api.domain.service.LancamentoService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LancamentoServiceTest {

	@MockBean
	private LancamentoRepository lancamentoRepository;

	@Autowired
	private LancamentoService lancamentoService;

	@Before
	public void setUp() throws Exception {
		BDDMockito
				.given(this.lancamentoRepository.findBy_id(Mockito.anyString(), Mockito.any(PageRequest.class)))
				.willReturn(new PageImpl<Employee>(new ArrayList<Employee>()));
		BDDMockito.given(this.lancamentoRepository.findOne(Mockito.anyString())).willReturn(new Employee());
		BDDMockito.given(this.lancamentoRepository.save(Mockito.any(Employee.class))).willReturn(new Employee());
	}

	@Test
	public void testBuscarLancamentoPorFuncionarioId() {
		Page<Employee> lancamento = this.lancamentoService.buscarPorId(1L+"", new PageRequest(0, 10));

		assertNotNull(lancamento);
	}

	@Test
	public void testBuscarLancamentoPorId() {
		Optional<Employee> lancamento = this.lancamentoService.buscarPorId(1L+"");

		assertTrue(lancamento.isPresent());
	}

	@Test
	public void testPersistirLancamento() {
		Employee lancamento = this.lancamentoService.insert(new Employee());

		assertNotNull(lancamento);
	}

}
