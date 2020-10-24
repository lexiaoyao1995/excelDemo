package com.twf.springcloud.ExportPdf.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twf.springcloud.ExportPdf.po.AvailableResult;
import com.twf.springcloud.ExportPdf.service.ExportPdfService;

@RestController
@RequestMapping("/exportPdf/")
public class ExportPdfController {

	@Autowired
	private ExportPdfService exportPdfService;
	
	// 导出pdf
	@RequestMapping("exportPdf")
	public AvailableResult exportPdf(HttpServletRequest request, HttpServletResponse response) {
		return exportPdfService.exportPdf(request,response);
	}
}
