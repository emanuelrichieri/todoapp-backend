package com.emanuelrichieri.todoapp.dto;

public class TaskDTO implements IEntityDTO {

	private Long id;
	private String description;
	private String status;
	private String attachedFile;
	private String filename;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getAttachedFile() {
		return attachedFile;
	}
	public void setAttachedFile(String file) {
		this.attachedFile = file;
	}	
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	

	@Override
	public String toString() {
		return "{ "
				+ " id = " + this.getId() + ", "
				+ " status = \"" + this.getStatus() + "\" , "
				+ " description = \"" + this.getDescription() + "\", "
				+ " attachedFile = \"" + this.getAttachedFile() + "\" "
				+ " filename = \"" + this.getFilename() + "\" "
			+ " }";
	}
	
}
