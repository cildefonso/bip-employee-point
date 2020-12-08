package com.bip.api.domain.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.bip.api.domain.model.Employee;
import com.bip.api.domain.model.ReportChart;

@Transactional(readOnly = true)
//@NamedQueries({
//		@NamedQuery(name = "LancamentoRepository.findByFuncionarioId", 
//				query = "SELECT lanc FROM Lancamento lanc WHERE lanc.funcionario.id = :funcionarioId") })
public interface LancamentoRepository extends MongoRepository<Employee, String> {//JpaRepository<Lancamento, Long> {

	Employee findBy_id(ObjectId _id);
	List<Employee> findBy_id(@Param("Id") String funcionarioId);
	Page<Employee> findBy_id(@Param("Id") String funcionarioId, Pageable pageable);
	Employee findByEmail(String email);
	Employee findByCpf(String cpf);
	//@Query(value= "select new com.cildefonso.net.relatorio.model.ReportChart(state.id, state.name,(select count(*) from City city where city.state_id = state.id)) from State state")
	public List<ReportChart> countCitiesByLaunch();
	
}
