package by.academy.fitness.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.academy.fitness.domain.dto.FileDTO;
import by.academy.fitness.service.interf.IReportService;
import by.academy.fitness.writer.XLSXWriter;
import by.academy.fitness.writer.XLSXWriterHolder;

@Service
public class ReportService implements IReportService {
	@Autowired
	private XLSXWriterHolder holder;

	@Override
	public FileDTO downloadReport(String type) {
		if (type != null) {
			XLSXWriter<?> writer = holder.getXLSXWriter(type);
			try {
				String filepath = writer.generateReport(true);
				FileDTO fileDto = new FileDTO ();
				File file = new File (filepath);
				fileDto.setBlob(Files.readAllBytes(file.toPath()));
				fileDto.setFilename(writer.getFilename(filepath));
				fileDto.setContentType(writer.getContentType());
				return fileDto;
				
			}catch (IOException e) {
				return null;
			}
			
		} else {
			return null;
		}
	}

}
