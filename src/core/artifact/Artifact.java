package core.artifact;

public class Artifact {
	public Artifact(String parentFolder, String name) {
		this.parentFolder = parentFolder;
		this.namePrefix = name;
		this.nameSufix = "";
		this.extension = "";
	}
	
	public Artifact(String parentFolder, String namePrefix, String nameSufix, String extension) {
		this.parentFolder = parentFolder;
		this.namePrefix = namePrefix;
		this.nameSufix = nameSufix;
		this.extension = extension;
	}
	
	private String parentFolder;
	private String namePrefix;
	private String nameSufix;
	
	private String extension;
	
	public String getParentFolder() {
		return parentFolder;
	}
	public void setParentFolder(String parentFolder) {
		this.parentFolder = parentFolder;
	}
	public String getNamePrefix() {
		return namePrefix;
	}
	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}

	public String getNameSufix() {
		return nameSufix;
	}

	public void setNameSufix(String nameSufix) {
		this.nameSufix = nameSufix;
	}
	
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
}
