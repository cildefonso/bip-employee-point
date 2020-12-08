package com.bip.api.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.bip.api.domain.model.Employee;
import com.bip.api.domain.model.ReportChart;
import com.bip.api.domain.service.LancamentoService;
import com.bip.api.dtos.LaunchDto;
import com.bip.api.response.Response;
import com.bip.api.utils.Utils;
import com.itextpdf.text.DocumentException;

@Controller
@RequestMapping("/reports")
public class ReportController {

	private static final Logger log = LoggerFactory.getLogger(ReportController.class);

	@Autowired
	LancamentoService lancamentoService;
	
	public ReportController(LancamentoService lancamentoService) {

		this.lancamentoService = lancamentoService;
	}

	
	@GetMapping(value = "/html", headers = "X-API-Version=v1", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<LaunchDto>> html(@RequestParam(required = false, value = "employee") String cpf, Model model) {
		Response<LaunchDto> response = new Response<LaunchDto>();
		List<Employee> employee = this.lancamentoService.findAll();
		//model.addAttribute("employees", employee);
		if (employee == null) {
			  //log.error("Erro validando lançamento: {}", result.getAllErrors());
			  //result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			  return ResponseEntity.badRequest().body(response);
    	}
		//ArrayList<LaunchDto> launchDto = Utils.converterLancamentoDto(employee);
		//model.addAttribute("list", launchDto);
		response.setDataArray(Utils.converterLancamentoDto(employee));
		return ResponseEntity.ok(response);

	}
	
	@GetMapping("/chart")
	public String chart(Model model) {
	    List<ReportChart> list = this.lancamentoService.countCitiesByLaunch();
		model.addAttribute("labels", list.stream().map(ReportChart::getName).collect(Collectors.toList()));
		model.addAttribute("data", list.stream().map(ReportChart::getTotal).collect(Collectors.toList()));
		return "report/chart";
	}

	@GetMapping("/pdf")
	public ResponseEntity<InputStreamResource> pdf (@RequestParam(required = false, value="employee") String cpf, Model model) throws DocumentException{
		List<Employee> employee = this.lancamentoService.findAll();
		model.addAttribute("employees", employee);
		
		if (employee == null) {
			return null;
		}
		Employee employeeLaunch = this.lancamentoService.findByCpf(cpf);
		ByteArrayInputStream pdf = Utils.generatePdf(employeeLaunch.getLaunch());
		return ResponseEntity.ok().header("Content-Disposition", "inline; filename=report.pdf").contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(pdf));
	}
	
	@GetMapping("/csv")
	public void csv(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("Content-Disposition", "attachment; filename=csv.csv");
		String [] header = {"id","fullname"};
		ICsvBeanWriter csvBeanWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		csvBeanWriter.writeHeader(header);
		List<Employee> employees = this.lancamentoService.findAll();
		for(Employee employee : employees) {
			csvBeanWriter.write(employee, header);
		}
		csvBeanWriter.close();
	}
}
