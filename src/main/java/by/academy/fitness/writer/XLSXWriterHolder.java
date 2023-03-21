package by.academy.fitness.writer;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import by.academy.fitness.domain.entity.Product;

@Component
public class XLSXWriterHolder {
	private Map<String, XLSXWriter<?>> holders = new HashMap<>();
	@Autowired
	@Qualifier("XLSXProductWriter")
	private XLSXWriter<Product> productWriter;

	@PostConstruct
	public void init() {
		holders.put("XLSXProductWriter", productWriter);
	}

	public XLSXWriter<?> getXLSXWriter(String type) {
		return holders.get(type);
	}
}
