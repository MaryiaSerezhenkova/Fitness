package by.academy.fitness.service.interf;

import by.academy.fitness.domain.dto.FileDTO;

public interface IReportService {
	FileDTO downloadReport(String type);

}
