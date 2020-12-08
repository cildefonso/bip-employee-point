package com.bip.api.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UfCacheService {

	private static final Logger log = LoggerFactory.getLogger(UfCacheService.class);

	@Cacheable("lisUfCache")
	public String lisUfCache() {
		StringBuilder lisUfs = new StringBuilder();
		lisUfs.append("Acre>").append("Alagoas>").append("Amapá>").append("Amazonas>").append("Bahia>").append("Ceará>");
		lisUfs.append("Distrito Federal>").append("Espírito Santo>").append("Goiás").append("Maranhão");
		lisUfs.append("Mato Grosso").append("Mato Grosso do Sul").append("Minas Gerais").append("Pará");
		lisUfs.append("Paraíba").append("Paraná").append("Pernambuco").append("Piauí");
		lisUfs.append("Rio de Janeiro").append("Rio Grande do Norte").append("Rio Grande do Sul");
		lisUfs.append("Rondônia").append("Roraima").append("Santa Catarina").append("São Paulo");
		lisUfs.append("Sergipe").append("Tocantins");

		String converte = lisUfs.toString();
		System.out.println("### Executando o serviço..."+converte);
		log.info("### Executando o serviço..."+converte);
		return converte;
	}
}
