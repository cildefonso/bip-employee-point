package com.bip.api.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.bip.api.domain.model.Employee;
import com.bip.api.domain.model.Launch;
import com.bip.api.dtos.LaunchDto;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class Utils {
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * Converte uma entidade lançamento para seu respectivo array de DTO.
	 * 
	 * @param employee
	 * @return LaunchDto
	 */
	public static ArrayList<LaunchDto> converterLancamentoDto(List<Employee> employee) {
		ArrayList<LaunchDto> launchDto = new ArrayList<LaunchDto>();
		
		  int intTamanho = employee.size();
		  for (int intContador = 0; intContador < intTamanho; intContador++) {
			  System.out.printf("Register of the employees %d- %s\n", intContador, employee.get(intContador));
		      System.out.printf("Register of the laucnhs %d- %s\n", intContador, employee.get(intContador).getLaunch());
		      if (employee.get(intContador).getLaunch() != null) { 
//		    	  employee.get(intContador).getLaunch().forEach((item) -> {
//		    		System.out.printf("Register of the launch %d- %s\n", item);
		    	int intTamanhoLaunch = employee.get(intContador).getLaunch().size();
		  		for (int intContadorLaunch = 0; intContadorLaunch < intTamanhoLaunch; intContadorLaunch++) {
		            LaunchDto launchTempDto = new LaunchDto();
		            if (employee.get(intContador).getCpf() != null) {
		            	launchTempDto.setCpf(employee.get(intContador).getCpf().toString());
		            }
					if (employee.get(intContador).getLaunch().get(intContadorLaunch).getId() != null) {
					   launchTempDto.setId(employee.get(intContador).getLaunch().get(intContadorLaunch).getId());
					}
					if (employee.get(intContador).getLaunch().get(intContadorLaunch).getTypelauch() != null) {
						launchTempDto.setTypelauch(employee.get(intContador).getLaunch().get(intContadorLaunch).getTypelauch().toString());
					}
					if (employee.get(intContador).getLaunch().get(intContadorLaunch).getDescription() != null) {
						launchTempDto.setDescription(employee.get(intContador).getLaunch().get(intContadorLaunch).getDescription().toString());
					}
					if (employee.get(intContador).getLaunch().get(intContadorLaunch).getLocation() != null) {
						launchTempDto.setLocation(employee.get(intContador).getLaunch().get(intContadorLaunch).getLocation().toString());
					}
					if (employee.get(intContador).getLaunch().get(intContadorLaunch).getDatelaunch() != null) {
						launchTempDto.setDatelaunch(dateFormat.format(employee.get(intContador).getLaunch().get(intContadorLaunch).getDatelaunch()));
					}		   
						launchDto.add(launchTempDto);
		  		}
			 }
				
		  }
		
		return launchDto;
	}
	
	public static ByteArrayInputStream generatePdf(ArrayList<Launch> list) throws com.itextpdf.text.DocumentException {
		Document doc = new Document();
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		table.setWidths(new int[]{ 1, 2 });
		
		PdfPCell header;
		
		header = new PdfPCell(new Phrase("ID"));
		header.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(header);

		header = new PdfPCell(new Phrase("DESCRIPTION"));
		header.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(header);
		
		for (Launch data: list) {
			PdfPCell body;				

			body = new PdfPCell(new Phrase(data.getId().toString()));
			body.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(body);

			body = new PdfPCell(new Phrase(data.getDescription()));
			body.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(body);
		}
		
		PdfWriter.getInstance(doc, byteArrayOutputStream);
		doc.open();
		doc.add(table);
		doc.close();
		return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
	}
}
