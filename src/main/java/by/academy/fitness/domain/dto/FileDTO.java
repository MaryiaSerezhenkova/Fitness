package by.academy.fitness.domain.dto;

import java.util.Arrays;
import java.util.Objects;

public class FileDTO {
	private String filename;
	private String contentType;
	private byte[] blob;
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public byte[] getBlob() {
		return blob;
	}
	public void setBlob(byte[] blob) {
		this.blob = blob;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(blob);
		result = prime * result + Objects.hash(contentType, filename);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileDTO other = (FileDTO) obj;
		return Arrays.equals(blob, other.blob) && Objects.equals(contentType, other.contentType)
				&& Objects.equals(filename, other.filename);
	}

}
