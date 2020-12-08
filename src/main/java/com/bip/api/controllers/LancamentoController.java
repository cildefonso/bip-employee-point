package com.bip.api.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.EnumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bip.api.domain.model.Employee;
import com.bip.api.domain.model.Launch;
import com.bip.api.domain.service.LancamentoService;
import com.bip.api.domain.service.UfCacheService;
import com.bip.api.dtos.LaunchDto;
import com.bip.api.enums.OperationEnum;
import com.bip.api.enums.TipoEnum;
import com.bip.api.response.Response;
import com.bip.api.utils.Utils;

//
@RestController
@EnableCaching
@RequestMapping("/api/lancamento-pf")
@CrossOrigin(origins = "*")
public class LancamentoController {

	private static final Logger log = LoggerFactory.getLogger(LancamentoController.class);
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



	@Autowired
	private UfCacheService ufCacheService;
	
	@Autowired
	LancamentoService lancamentoService;
	
	@Value("${paginacao.qtd_por_pagina}")
	private int qtdPorPagina;

	public LancamentoController() {
	}

	/**
	 * Retorna a listagem de lançamentos de um funcionário.
	 * 
	 * @param id
	 * @return ResponseEntity<Response<LancamentoDto>>
	 */
	@GetMapping(value = "/funcionario/{id:.+}", headers = "X-API-Version=v1", produces=MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ADMIN','GERENTE','FUNCIONARIO')")
	public ResponseEntity<Response<Page<LaunchDto>>> listarPorId(
			@PathVariable("id") String Id,
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {
		log.info("Buscando lançamentos por ID do funcionário: {}, página: {}", Id, pag);
		Response<Page<LaunchDto>> response = new Response<Page<LaunchDto>>();

//		PageRequest pageRequest = new PageRequest(pag, this.qtdPorPagina, Direction.valueOf(dir), ord);
//		Page<Launch> lancamentos = this.lancamentoService.buscarPorId(Id, pageRequest);
//		Page<LaunchDto> launchDto = lancamentos.map(lancamento -> this.converterLancamentoDto(lancamento));
//
//		response.setData(launchDto);
		return ResponseEntity.ok(response);
	}


	/**
	 * Retorna um lançamento por CPF.
	 * 
	 * @param cpf
	 * @return ResponseEntity<Response<LancamentoDto>>
	 */
	@GetMapping(value = "/{cpf:.+}", headers = "X-API-Version=v1", produces=MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ADMIN','GERENTE','FUNCIONARIO')")
	public ResponseEntity<Response<LaunchDto>> listarPorCpf(@PathVariable("cpf") String cpf) {
		log.info("Buscando lançamento por CPF: {}", cpf);
		ArrayList<LaunchDto> launchDto = new ArrayList<LaunchDto>();
		Response<LaunchDto> response = new Response<LaunchDto>();
		Employee employee = this.lancamentoService.findByCpf(cpf);
		if (employee.getLaunch() == null) {
				log.info("Lançamento não encontrado para o CPF: {}", cpf);
				response.getErrors().add("Lançamento não encontrado para o CPF " + cpf);
				return ResponseEntity.badRequest().body(response);
		
		}
		int intTamanho = employee.getLaunch().size();
		for (int intContador = 0; intContador < intTamanho; intContador++) {
			LaunchDto launchTempDto = new LaunchDto();
			if (employee.getCpf() != null) {
				launchTempDto.setCpf(employee.getCpf());
			}
			if (employee.getLaunch().get(intContador).getId() != null) {
		       launchTempDto.setId(employee.getLaunch().get(intContador).getId());
			}
			if (employee.getLaunch().get(intContador).getTypelauch() != null) {
				launchTempDto.setTypelauch(employee.getLaunch().get(intContador).getTypelauch().toString());
			}
			if (employee.getLaunch().get(intContador).getDescription() != null) {
				launchTempDto.setDescription(employee.getLaunch().get(intContador).getDescription().toString());
			}
			if (employee.getLaunch().get(intContador).getLocation() != null) {
				launchTempDto.setLocation(employee.getLaunch().get(intContador).getLocation().toString());
			}
			if (employee.getLaunch().get(intContador).getDatelaunch() != null) {
				launchTempDto.setDatelaunch(this.dateFormat.format(employee.getLaunch().get(intContador).getDatelaunch()));
			}		   
			launchDto.add(launchTempDto);
		}
	    response.setDataArray(launchDto);
		return ResponseEntity.ok(response);
	}
	
//	/**
//	 * Converte uma entidade lançamento para seu respectivo array de DTO.
//	 * 
//	 * @param employee
//	 * @return LaunchDto
//	 */
//	private ArrayList<LaunchDto> converterLancamentoDto(List<Employee> employee) {
//		ArrayList<LaunchDto> launchDto = new ArrayList<LaunchDto>();
//		
//		  int intTamanho = employee.size();
//		  for (int intContador = 0; intContador < intTamanho; intContador++) {
//			  System.out.printf("Register of the employees %d- %s\n", intContador, employee.get(intContador));
//		      System.out.printf("Register of the laucnhs %d- %s\n", intContador, employee.get(intContador).getLaunch());
//		      if (employee.get(intContador).getLaunch() != null) { 
////		    	  employee.get(intContador).getLaunch().forEach((item) -> {
////		    		System.out.printf("Register of the launch %d- %s\n", item);
//		    	int intTamanhoLaunch = employee.get(intContador).getLaunch().size();
//		  		for (int intContadorLaunch = 0; intContadorLaunch < intTamanhoLaunch; intContadorLaunch++) {
//		            LaunchDto launchTempDto = new LaunchDto();
//		            if (employee.get(intContador).getCpf() != null) {
//		            	launchTempDto.setCpf(employee.get(intContador).getCpf().toString());
//		            }
//					if (employee.get(intContador).getLaunch().get(intContadorLaunch).getId() != null) {
//					   launchTempDto.setId(employee.get(intContador).getLaunch().get(intContadorLaunch).getId());
//					}
//					if (employee.get(intContador).getLaunch().get(intContadorLaunch).getTypelauch() != null) {
//						launchTempDto.setTypelauch(employee.get(intContador).getLaunch().get(intContadorLaunch).getTypelauch().toString());
//					}
//					if (employee.get(intContador).getLaunch().get(intContadorLaunch).getDescription() != null) {
//						launchTempDto.setDescription(employee.get(intContador).getLaunch().get(intContadorLaunch).getDescription().toString());
//					}
//					if (employee.get(intContador).getLaunch().get(intContadorLaunch).getLocation() != null) {
//						launchTempDto.setLocation(employee.get(intContador).getLaunch().get(intContadorLaunch).getLocation().toString());
//					}
//					if (employee.get(intContador).getLaunch().get(intContadorLaunch).getDatelaunch() != null) {
//						launchTempDto.setDatelaunch(this.dateFormat.format(employee.get(intContador).getLaunch().get(intContadorLaunch).getDatelaunch()));
//					}		   
//						launchDto.add(launchTempDto);
//		  		}
//			 }
//				
//		  }
//		
//		return launchDto;
//	}
	
	/**
	 * Retorna todos lançamentos.
	 * 
	 * @return ResponseEntity<Response<LaunchDto>>
	 */
	@GetMapping(value = "/", headers = "X-API-Version=v1", produces=MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ADMIN','GERENTE','FUNCIONARIO')")
	public ResponseEntity<Response<LaunchDto>> listarPorTodos() {

		Response<LaunchDto> response = new Response<LaunchDto>();
		List<Employee> employee = this.lancamentoService.findAll();
//		if (result.hasErrors()) {
//		  log.error("Erro validando lançamento: {}", result.getAllErrors());
//		  result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
//		  return ResponseEntity.badRequest().body(response);
//	    }
		response.setDataArray(Utils.converterLancamentoDto(employee));
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Adiciona um novo lançamento.
	 * 
	 * @param lancamento
	 * @param result
	 * @return ResponseEntity<Response<LancamentoDto>>
	 * @throws ParseException 
	 */
	@PostMapping(value = "/", headers = "X-API-Version=v1", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAnyRole('ADMIN','GERENTE','FUNCIONARIO')")
	public ResponseEntity<Response<LaunchDto>> adicionar(@Valid @RequestBody LaunchDto launchDto,
			BindingResult result) throws ParseException {
		log.info("Adicionando lançamento: {}", launchDto.toString());
		Response<LaunchDto> response = new Response<LaunchDto>();
		Employee employee = this.converterDtoParaLancamento(launchDto,OperationEnum.REGISTER, result);
		if (result.hasErrors()) {
			log.error("Erro ao validar o lançamento: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		this.lancamentoService.insert(employee);
		response.setData(launchDto);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Atualiza os dados de um lançamento.
	 * 
	 * @param id
	 * @param lancamentoDto
	 * @return ResponseEntity<Response<Lancamento>>
	 * @throws ParseException 
	 */
	@PutMapping(value = "/", headers = "X-API-Version=v1", produces=MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ADMIN','GERENTE','FUNCIONARIO')")
	public ResponseEntity<Response<LaunchDto>> atualizar(@Valid @RequestBody LaunchDto launchDto, BindingResult result) throws ParseException {
		log.info("Atualizando lançamento: {}", launchDto.toString());
		Response<LaunchDto> response = new Response<LaunchDto>();
		Employee employee = this.converterDtoParaLancamento(launchDto,OperationEnum.UPDATE, result);
		if (result.hasErrors()) {
			log.error("Erro ao validar o lançamento: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		this.lancamentoService.upDate(employee);
		response.setData(launchDto);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Remove um lançamento por ID.
	 * 
	 * @param id
	 * @return ResponseEntity<Response<Lancamento>>
	 * @throws ParseException 
	 */
	@DeleteMapping(value = "/", headers = "X-API-Version=v1", produces=MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ADMIN','GERENTE','FUNCIONARIO')")
	public ResponseEntity<Response<String>> remover(@Valid @RequestBody LaunchDto launchDto, BindingResult result) throws ParseException {
		log.info("Removendo lançamento: {}", launchDto.getId());
		Response<String> response = new Response<String>();
		Employee employee = this.converterDtoParaLancamento(launchDto,OperationEnum.DELETE, result);
		if (result.hasErrors()) {
			log.error("Erro ao remover devido ao lançamento ID: {} ser inválido.", result.getAllErrors());
			response.getErrors().add("Erro ao remover lançamento. Registro não encontrado para o id " + launchDto.getId());
			return ResponseEntity.badRequest().body(response);
		}
		this.lancamentoService.deletar(employee);
		return ResponseEntity.ok(new Response<String>());
	}
	/**
	 * Converte um LancamentoDto para uma entidade Lancamento.
	 * 
	 * @param launchDto
	 * @param result
	 * @return Employee
	 * @throws ParseException 
	 */
	private Employee converterDtoParaLancamento(LaunchDto launchDto, OperationEnum operationEnum, BindingResult result) throws ParseException {
		ArrayList<Launch> launch = new ArrayList<Launch>();
		Employee employee =  new Employee();
		Integer lastNumber = 1;
		Integer intIndex = 0;
		if (launchDto.getCpf() != null) {
			employee = this.lancamentoService.findByCpf(launchDto.getCpf());
			if (employee == null) {
				result.addError(new ObjectError("lancamento", "Funcionário não encontrado."));
			} else {
				switch (operationEnum) {
					case REGISTER:
						if (employee.getLaunch() != null ) {
							launch.addAll(employee.getLaunch());
							lastNumber = launch.size() + 1;
						}
						Launch launchInsert = new Launch();
						launchInsert.setId(lastNumber);
						launchInsert.setValuehour(launchDto.getValuehour());
						launchInsert.setDescription(launchDto.getDescription());
						launchInsert.setLocation(launchDto.getLocation().toString());
						launchInsert.setDatelaunch(this.dateFormat.parse(launchDto.getDatelaunch().toString()));
						if (EnumUtils.isValidEnum(TipoEnum.class, launchDto.getTypelauch().toString())) {
							launchInsert.setTypelauch(TipoEnum.valueOf(launchDto.getTypelauch().toString()));
						} else {
							result.addError(new ObjectError("tipo", "Tipo inválido."));
						}
						if (launch.size() > 1) {
						   intIndex = launch.size();
						}
						launch.add(intIndex, launchInsert);
						employee.setLaunch(launch);
						break;
					case UPDATE:
//						employee.getLaunch().forEach((item) -> {
//				            System.out.println(item);
//				        });
						for (int index = 0; index < employee.getLaunch().size(); index++){
	                        System.out.println("Indice " + index);
	                        Launch launchUpdate = new Launch();
	                        if (employee.getLaunch().get(index).getId() == launchDto.getId()) {
	                        	launchUpdate.setId(launchDto.getId());
				            	launchUpdate.setValuehour(launchDto.getValuehour());
				            	launchUpdate.setDescription(launchDto.getDescription());
				            	launchUpdate.setLocation(launchDto.getLocation().toString());
				            	launchUpdate.setDatelaunch(this.dateFormat.parse(launchDto.getDatelaunch().toString()));
								if (EnumUtils.isValidEnum(TipoEnum.class, launchDto.getTypelauch().toString())) {
									launchUpdate.setTypelauch(TipoEnum.valueOf(launchDto.getTypelauch().toString()));
								} else {
									result.addError(new ObjectError("tipo", "Tipo inválido."));
								}
								launch.add(index, launchUpdate);
				            }
				            else {
				            	
				            	launchUpdate.setId(employee.getLaunch().get(index).getId());
				            	launchUpdate.setValuehour(employee.getLaunch().get(index).getValuehour());
				            	launchUpdate.setDescription(employee.getLaunch().get(index).getDescription());
				            	launchUpdate.setLocation(employee.getLaunch().get(index).getLocation().toString());
				            	launchUpdate.setDatelaunch(employee.getLaunch().get(index).getDatelaunch());
								if (EnumUtils.isValidEnum(TipoEnum.class, employee.getLaunch().get(index).getTypelauch().toString())) {
									launchUpdate.setTypelauch(TipoEnum.valueOf(employee.getLaunch().get(index).getTypelauch().toString()));
								} else {
									result.addError(new ObjectError("tipo", "Tipo inválido."));
								}
								launch.add(index, launchUpdate);
				            }
	                    }
						employee.setLaunch(launch);
						break;
					case DELETE:
						for (int index = 0; index < employee.getLaunch().size(); index++){
							System.out.println("Indice " + index);
	                        Launch launchDelete = new Launch();
	                        if (employee.getLaunch().get(index).getId() != launchDto.getId()) {
	                        	launchDelete.setId(employee.getLaunch().get(index).getId());
	                        	launchDelete.setValuehour(employee.getLaunch().get(index).getValuehour());
	                        	launchDelete.setDescription(employee.getLaunch().get(index).getDescription());
	                        	launchDelete.setLocation(employee.getLaunch().get(index).getLocation().toString());
				               	launchDelete.setDatelaunch(employee.getLaunch().get(index).getDatelaunch());
				            	if (EnumUtils.isValidEnum(TipoEnum.class, employee.getLaunch().get(index).getTypelauch().toString())) {
				            		launchDelete.setTypelauch(TipoEnum.valueOf(employee.getLaunch().get(index).getTypelauch().toString()));
								} else {
									result.addError(new ObjectError("tipo", "Tipo inválido."));
								}
								launch.add(index, launchDelete);
				            }
	                    }
						employee.setLaunch(launch);
						break;
			    }
			}
		}
	
		return employee;
	}
}

//Map<String, Integer> items = new HashMap<>();
//
//items.put("coins", 3);
//items.put("pens", 2);
//items.put("keys", 1);
//items.put("sheets", 12);
//
//items.forEach((k, v) -> {
//    System.out.printf("%s : %d%n", k, v);
//});
