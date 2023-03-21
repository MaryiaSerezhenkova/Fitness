package by.academy.fitness.writer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public abstract class XLSXWriter<T> {
	protected static final String TEMPORARY_REPORT_FOLDER = "src\\main\\resources\\temp\\";
	protected final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

	protected String writeData(List<T> data) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(getSheetName());
		int rowCounter = 0;
		Row row = sheet.createRow(rowCounter++);
		int columnCounter = 0;
		for (String header : getHeaders()) {
			Cell cell = row.createCell(columnCounter++);
			cell.setCellValue(header);
		}
		writeRows(sheet, data, rowCounter);
		String fileName = getFilePath();
		File file = new File(fileName);
		file.createNewFile();
		try (FileOutputStream os = new FileOutputStream(file)) {
			workbook.write(os);
		} finally {
			workbook.close();
		}
		return fileName;
	}

	protected void addCell(Row row, int i, String value) {
		Cell cell = row.createCell(i);
		cell.setCellValue(value != null ? value : "");
	}

	protected void addCell(Row row, int i, Boolean value) {
		Cell cell = row.createCell(i);
		if (value != null) {
			cell.setCellValue(value);
		} else {
			cell.setCellValue("");
		}
	}

	protected void addCell(Row row, int i, Date value) {
		Cell cell = row.createCell(i);
		if (value != null) {
			cell.setCellValue(value);
		} else {
			cell.setCellValue("");
		}
	}

	protected void addCell(Row row, int i, Long value) {
		Cell cell = row.createCell(i);
		if (value != null) {
			cell.setCellValue(value);
		} else {
			cell.setCellValue("");
		}
	}

	protected void addCell(Row row, int i, Integer value) {
		Cell cell = row.createCell(i);
		if (value != null) {
			cell.setCellValue(value);
		} else {
			cell.setCellValue("");
		}
	}

	protected void addCell(Row row, int i, Double value) {
		Cell cell = row.createCell(i);
		if (value != null) {
			cell.setCellValue(value);
		} else {
			cell.setCellValue("");
		}
	}

	protected void addCell(Row row, int i, LocalDateTime value) {
		Cell cell = row.createCell(i);
		if (value != null) {
			cell.setCellValue(value);
		} else {
			cell.setCellValue("");
		}
	}

	protected void addCell(Row row, int i, UUID value) {
		Cell cell = row.createCell(i);
		if (value != null) {
			cell.setCellValue(value.toString());
		} else {
			cell.setCellValue("");
		}
	}

	public String generateReport(boolean writeEmpty) throws IOException {
		List<T> items = getItems();
		if (items.size() == 0 && !writeEmpty) {
			return null;
		}
		return writeData(items);
	}

	protected abstract List<T> getItems();

	protected abstract String getFilePath();

	protected abstract void writeRows(XSSFSheet sheet, List<T> data, int rowCounter);

	protected abstract String getSheetName();

	protected abstract List<String> getHeaders();

	public String getFilename(String path) {
		if (path == null) {
			return null;
		}
		return path.replace(TEMPORARY_REPORT_FOLDER, "");
	}

	public String getContentType() {

		return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	}

}
