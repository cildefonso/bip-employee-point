package com.bip.api.domain.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.bip.api.domain.model.Employee;
import com.bip.api.domain.model.Launch;
import com.bip.api.domain.model.ReportChart;

public interface LancamentoService {

    Employee insert(Employee employee);
    Employee upDate(Employee employee);
    String deletar(Employee employee);
    long count();
    List<Employee> findAll();
    
	/**
	 * Busca e retorna um funcionário dado um CPF.
	 * 
	 * @param cpf
	 * @return Employee
	 */
    Employee findByCpf(String cpf);
	 
	/**
	 * Busca e retorna um funcionário dado um email.
	 * 
	 * @param email
	 * @return Employee
	 */
	 Employee findByEmail(String email);
	
	/**
	 * Busca e retorna um funcionário por ID.
	 * 
	 * @param id
	 * @return Employee
	 */
	 Employee findBy_id(String id);
	 
	 public List<ReportChart> countCitiesByLaunch();
	
	/**
	 * Retorna uma lista paginada de lançamentos de um determinado funcionário.
	 * 
	 * @param funcionarioId
	 * @param pageRequest
	 * @return Page<Lancamento>
	 */
	Page<Employee> buscarPorId(String funcionarioId, PageRequest pageRequest);
	
	/**
	 * Retorna um lançamento por ID.
	 * 
	 * @param cpf
	 * @return Optional<Lancamento>
	 */
	//Optional<Launch> buscarPorCPF(String CPF);
	
	/**
	 * Retorna um lançamento por ID.
	 * 
	 * @param id
	 * @return Optional<Lancamento>
	 */
	Optional<Employee> buscarPorId(String id);
	
}
