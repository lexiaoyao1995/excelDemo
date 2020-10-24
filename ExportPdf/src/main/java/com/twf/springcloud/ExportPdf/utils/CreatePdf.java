package com.twf.springcloud.ExportPdf.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.twf.springcloud.ExportPdf.po.User;

public class CreatePdf {

	Document document = new Document();// 建立一个Document对象

	private static Font headfont;// 设置字体大小
	private static Font keyfont;// 设置字体大小
	private static Font textfont;// 设置字体大小

	static {
		// 中文格式
		BaseFont bfChinese;
		try {
			// 设置中文显示
			bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			headfont = new Font(bfChinese, 10, Font.BOLD);// 设置字体大小
			keyfont = new Font(bfChinese, 8, Font.BOLD);// 设置字体大小
			textfont = new Font(bfChinese, 8, Font.NORMAL);// 设置字体大小
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 文成文件
	 * 
	 * @param file
	 *            待生成的文件名
	 */
	public CreatePdf(File file) {
		document.setPageSize(PageSize.A3);// 设置页面大小
		try {
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public CreatePdf() {

	}

	public void initFile(File file) {
		document.setPageSize(PageSize.A3);// 设置页面大小
		try {
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	int maxWidth = 842;

	/**
	 * 为表格添加一个内容
	 * 
	 * @param value
	 *            值
	 * @param font
	 *            字体
	 * @param align
	 *            对齐方式
	 * @return 添加的文本框
	 */
	public PdfPCell createCell(String value, Font font, int align) {
		PdfPCell cell = new PdfPCell();
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(align);
		cell.setPhrase(new Phrase(value, font));
		return cell;
	}

	/**
	 * 为表格添加一个内容
	 * 
	 * @param value
	 *            值
	 * @param font
	 *            字体
	 * @return 添加的文本框
	 */
	public PdfPCell createCell(String value, Font font) {
		PdfPCell cell = new PdfPCell();
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPhrase(new Phrase(value, font));
		return cell;
	}

	/**
	 * 为表格添加一个内容
	 * 
	 * @param value
	 *            值
	 * @param font
	 *            字体
	 * @param align
	 *            对齐方式
	 * @param colspan
	 *            占多少列
	 * @return 添加的文本框
	 */
	public PdfPCell createCell(String value, Font font, int align, int colspan) {
		PdfPCell cell = new PdfPCell();
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(align);
		cell.setColspan(colspan);
		cell.setPhrase(new Phrase(value, font));
		return cell;
	}

	/**
	 * 为表格添加一个内容
	 * 
	 * @param value
	 *            值
	 * @param font
	 *            字体
	 * @param align
	 *            对齐方式
	 * @param colspan
	 *            占多少列
	 * @param boderFlag
	 *            是否有有边框
	 * @return 添加的文本框
	 */
	public PdfPCell createCell(String value, Font font, int align, int colspan, boolean boderFlag) {
		PdfPCell cell = new PdfPCell();
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(align);
		cell.setColspan(colspan);
		cell.setPhrase(new Phrase(value, font));
		cell.setPadding(3.0f);
		if (!boderFlag) {
			cell.setBorder(0);
			cell.setPaddingTop(15.0f);
			cell.setPaddingBottom(8.0f);
		}
		return cell;
	}

	/**
	 * 创建一个表格对象
	 * 
	 * @param colNumber
	 *            表格的列数
	 * @return 生成的表格对象
	 */
	public PdfPTable createTable(int colNumber) {
		PdfPTable table = new PdfPTable(colNumber);
		try {
			table.setTotalWidth(maxWidth);
			table.setLockedWidth(true);
			table.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setBorder(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return table;
	}

	public PdfPTable createTable(float[] widths) {
		PdfPTable table = new PdfPTable(widths);
		try {
			table.setTotalWidth(maxWidth);
			table.setLockedWidth(true);
			table.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setBorder(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return table;
	}

	public PdfPTable createBlankTable() {
		PdfPTable table = new PdfPTable(1);
		table.getDefaultCell().setBorder(0);
		table.addCell(createCell("", keyfont));
		table.setSpacingAfter(20.0f);
		table.setSpacingBefore(20.0f);
		return table;
	}

	/**
	 * 
	 * @param head
	 *            头部
	 * @param list
	 *            数据内容
	 * @param colNum
	 *            列数
	 */
	public <T> void generatePDF(String[] head, List<User> list, int colNum) {

		// 创建一个只有colNum列的表格
		PdfPTable table = createTable(colNum);

		// 添加备注,靠左，不显示边框
		table.addCell(createCell("用户表：", keyfont, Element.ALIGN_LEFT, colNum, false));

		// 设置表头
		for (int i = 0; i < colNum; i++) {
			table.addCell(createCell(head[i], keyfont, Element.ALIGN_CENTER));
		}

		if (null != list && list.size() > 0) {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				User user = list.get(i);
				table.addCell(createCell(user.getName(), textfont));
				table.addCell(createCell(user.getSex(), textfont));
				table.addCell(createCell(String.valueOf(user.getAge()), textfont));
				table.addCell(createCell(user.getPhoneNo(), textfont));
				table.addCell(createCell(user.getAddress(), textfont));
				table.addCell(createCell(user.getHobby(), textfont));
			}
		}

		try {
			// 将表格添加到文档中
			document.add(table);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		// 关闭流
		document.close();
	}

	/**
	 * 提供外界调用的接口，生成以head为表头，list为数据的pdf
	 * 
	 * @param head
	 *            //数据表头
	 * @param list
	 *            //数据
	 * @return //excel所在的路径
	 */
	public <T> String generatePDFs(String[] head, List<User> list) {
		final String FilePath = "pdfPath";
		String saveFilePathAndName = "";

		// 获得存储的根目录
		String savePath = new GetFilePlace().getFileDirFromProperties(FilePath);

		// 获得当天存储的路径,不存在则生成当天的文件夹
		String realSavePath = new GenerateFold().getFold(savePath);

		saveFilePathAndName = new GenerateFileName().generateFileName(realSavePath, "pdf");

		File file = new File(saveFilePathAndName);
		try {
			file.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		initFile(file);
		try {
			file.createNewFile(); // 生成一个pdf文件
		} catch (IOException e) {
			e.printStackTrace();
		}
		new CreatePdf(file).generatePDF(head, list, head.length);

		return saveFilePathAndName;
	}
}
