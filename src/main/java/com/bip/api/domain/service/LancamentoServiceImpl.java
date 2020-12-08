package com.bip.api.domain.service;

import java.util.List;
import java.util.Optional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bip.api.domain.model.Employee;
import com.bip.api.domain.model.ReportChart;
import com.bip.api.domain.repository.LancamentoRepository;
import com.bip.domain.exception.NegocioException;

@Service
public class LancamentoServiceImpl implements LancamentoService {

	private static final Logger log = LoggerFactory.getLogger(LancamentoServiceImpl.class);

	@Autowired
	private LancamentoRepository lancamentoRepository;

	private Employee employeeSuccess;
	

	public Page<Employee> buscarPorId(String funcionarioId, PageRequest pageRequest) {
		log.info("Buscando lançamentos para o funcionário ID {}", funcionarioId);
		return this.lancamentoRepository.findBy_id(funcionarioId, pageRequest);
	}
	
	@Cacheable("Id")
	public Optional<Employee> buscarPorId(String id) {
		log.info("Buscando um lançamento pelo ID {}", id);
		return Optional.ofNullable(this.lancamentoRepository.findOne(id));
	}
	
//	@Cacheable("cpf")
//	public Optional<Launch> buscarPorCPF(String cpf) {
//		log.info("Buscando um lançamento pelo cpf {}", cpf);
//		return Optional.ofNullable(this.lancamentoRepository.findByCpf(cpf));
//	}
	
	@CachePut("cpf")
	public Employee persistir(Employee employee) {
		log.info("Persistindo o lançamento: {}", employee);
		return this.lancamentoRepository.save(employee);
	}
	
	
	public Employee insert(Employee employee) {
		//Employee employeeSuccess = new Employee();
		employeeSuccess = lancamentoRepository.save(employee);
		
		if (employeeSuccess == null ) {
			throw new NegocioException("Não foi possível incluir o lançamento com sucesso. ");
		}
	
		return employeeSuccess;
	}
	
	public Employee upDate(Employee employee) {
		employeeSuccess = new Employee();
		employeeSuccess = lancamentoRepository.findByCpf(employee.getCpf());
		
		if (employeeSuccess == null && employeeSuccess.equals(employee)) {
			throw new NegocioException("Este empregado não está cadastrada. ");
		}
	
		return employeeSuccess;
	}
	
	
	public String deletar(Employee employee) {
		log.info("Excluíndo funcionário", employee);
		String message = "Lançamento excluído com sucesso. ";
		employeeSuccess = lancamentoRepository.findByCpf(employee.getCpf());
		if (employeeSuccess == null && employeeSuccess.equals(employee)) {
			throw new NegocioException("Este empregado não está cadastrada. ");
		}
		lancamentoRepository.save(employee);
		return message;
	}
   
    public List<Employee> findAll() {
      log.info("Buscar todos os funcionário");
      return lancamentoRepository.findAll();
    }

    public long count() {
       return lancamentoRepository.count();
    }
   
    public Employee findByEmail(String email) {
    	log.info("Buscar o ID do funcionário. ");
	      return lancamentoRepository.findByEmail(email);
    }
	
    public Employee findByCpf(String cpf) {
    	log.info("Buscar o ID do funcionário. ");
	      return lancamentoRepository.findByCpf(cpf);
    }
    
	@Override
	public List<ReportChart> countCitiesByLaunch(){
		return lancamentoRepository.countCitiesByLaunch();
	}

	@Override
	public Employee findBy_id(String id) {
		// TODO Auto-generated method stub
		return null;
	}

//    public List<Employee> findEmployeeByCpfBetween(int cpfGT, int cpfLT) {
//    	log.info("Buscar o ID do funcionário. ");
//	      return lancamentoRepository.findEmployeeByCpfBetween(cpfGT,cpfLT);
//    }
//    
//    public List<Employee> findUsersByFullname(String fullname) {
//    	log.info("Buscar o ID do funcionário. ");
//	      return lancamentoRepository.findUsersByFullname(fullname);
//    }
//
//    public List<Employee> findUsersByRegexpFullname(String regexp) {
//    	log.info("Buscar o ID do funcionário. ");
//	      return lancamentoRepository.findUsersByRegexpFullname(regexp);
//    }
//    
//    public Boolean isBooksAvailableByFullname(String cpf) {
//    	log.info("Buscar o ID do funcionário. ");
//	      return lancamentoRepository.isBooksAvailableByFullname(cpf);
//    }

//	@Override
//	public Employee list(Employee employee) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
