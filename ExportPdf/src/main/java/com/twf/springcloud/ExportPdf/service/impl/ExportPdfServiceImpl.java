package com.twf.springcloud.ExportPdf.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.twf.springcloud.ExportPdf.po.AvailableResult;
import com.twf.springcloud.ExportPdf.po.User;
import com.twf.springcloud.ExportPdf.service.ExportPdfService;
import com.twf.springcloud.ExportPdf.utils.CreatePdf;

@Service
public class ExportPdfServiceImpl implements ExportPdfService{

	Logger logger = LoggerFactory.getLogger(ExportPdfServiceImpl.class);
	
	@Override
	public AvailableResult exportPdf(HttpServletRequest request, HttpServletResponse response) {
		try {
			logger.info(">>>>>>>>>>开始导出pdf>>>>>>>>>>");

			String[] head = { "姓名", "性别", "年龄", "手机号", "地址","爱好" };

			List<User> list = new ArrayList<>();
			list.add(new User("唐三藏", "男", 30, "13411111111", "东土大唐", "取西经"));
			list.add(new User("孙悟空", "男", 29, "13411111112", "菩提院", "打妖怪"));
			list.add(new User("猪八戒", "男", 28, "13411111113", "高老庄", "偷懒"));
			list.add(new User("沙悟净", "男", 27, "13411111114", "流沙河", "挑担子"));
			
			String filePath = new CreatePdf().generatePDFs(head, list);
			System.out.println(filePath);

			logger.info(">>>>>>>>>>结束导出pdf>>>>>>>>>>");
			return AvailableResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(">>>>>>>>>>导出pdf 异常，原因为：" + e.getMessage());
			return AvailableResult.errorException(e.getMessage());
		}
	}

}
