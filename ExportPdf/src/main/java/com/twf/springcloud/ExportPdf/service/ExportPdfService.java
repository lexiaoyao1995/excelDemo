package com.twf.springcloud.ExportPdf.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twf.springcloud.ExportPdf.po.AvailableResult;

public interface ExportPdfService {

	AvailableResult exportPdf(HttpServletRequest request, HttpServletResponse response);
}
