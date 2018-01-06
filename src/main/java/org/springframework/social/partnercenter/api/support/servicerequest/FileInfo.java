package org.springframework.social.partnercenter.api.support.servicerequest;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(NON_NULL)
public class FileInfo {
	private String id;
	private String comment;
	private String fileExtension;
	private String fileNameWithoutExtension;
	private Long fileSize;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public String getFileNameWithoutExtension() {
		return fileNameWithoutExtension;
	}

	public void setFileNameWithoutExtension(String fileNameWithoutExtension) {
		this.fileNameWithoutExtension = fileNameWithoutExtension;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
}
