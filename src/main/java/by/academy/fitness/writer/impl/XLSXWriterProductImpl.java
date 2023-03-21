package by.academy.fitness.writer.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.academy.fitness.dao.ProductDao;
import by.academy.fitness.domain.entity.Product;
import by.academy.fitness.domain.entity.Product.UNIT;
import by.academy.fitness.writer.XLSXWriter;

@Component("XLSXProductWriter")
public class XLSXWriterProductImpl extends XLSXWriter<Product> {
	private final static List<String> headers = List.of("uuid", "dtCreate", "dtUpdate", "name", "weight", "unit",
			"colories", "proteins", "fats", "carbohydrates", "userId");
	@Autowired
	private ProductDao productDao;

	@Override
	protected String getFilePath() {

		return TEMPORARY_REPORT_FOLDER + "Products_" + LocalDateTime.now().format(DATE_FORMATTER) + ".xlsx";
	}

	@Override
	protected void writeRows(XSSFSheet sheet, List<Product> data, int rowCounter) {
		for (Product product : data) {
			Row row = sheet.createRow(rowCounter++);
			int columnCounter = 0;
			addCell(row, columnCounter++, product.getUuid());
			addCell(row, columnCounter++, product.getDtCreate());
			addCell(row, columnCounter++, product.getDtUpdate());
			addCell(row, columnCounter++, product.getName());
			addCell(row, columnCounter++, product.getWeight());
			addCell(row, columnCounter++, product.getUnit());
			addCell(row, columnCounter++, product.getColories());
			addCell(row, columnCounter++, product.getProteins());
			addCell(row, columnCounter++, product.getFats());
			addCell(row, columnCounter++, product.getCarbohydrates());
			addCell(row, columnCounter++, product.getUserId());
		}

	}

	protected void addCell(Row row, int i, UNIT value) {
		Cell cell = row.createCell(i);
		if (value != null) {
			cell.setCellValue(value.name());
		} else {
			cell.setCellValue("");
		}
	}

	@Override
	protected String getSheetName() {
		return "Product";
	}

	@Override
	protected List<String> getHeaders() {
		return headers;
	}

	@Override
	protected List<Product> getItems() {
		return productDao.findAll(null, null, null, null);
	}

}
