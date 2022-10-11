package core.artifact;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import core.fieldtype.AbstractTypeConverter;
import core.model.MVCControl;
import core.model.MVCModel;
import core.model.MVCView;
import core.mvc.model.Attribute;
import core.mvc.model.Facade;
import core.mvc.model.Model;
import core.mvc.model.Operation;
import core.mvc.model.Parameter;

public abstract class ArtifactModel {
	protected HashMap<String, Vector<Attribute>> modelConstructorParametersStack;
	protected HashMap<String, Vector<Attribute>> facadeConstructorParametersStack;
	protected Boolean featureFlag = true;
	private static final String BASE_DIR = "/Base/";

	private AbstractTypeConverter typeConverter;

	private Vector<String> generatedFiles;
	private String repositoryPath;

	private Vector<Artifact> configurationFiles;
	private Vector<Artifact> modelFiles;
	private Vector<Artifact> viewFiles;
	private Vector<Artifact> controlFiles;

	private MVCModel modelEntities;
	private MVCView viewEntities;
	private MVCControl controllerEntities;

	private String featureTarget;
	private String productName;

	private Vector<String> featureModel;

	public ArtifactModel(MVCModel modelEntities, MVCView viewEntities, MVCControl controllerEntities) {
		this.modelConstructorParametersStack = new HashMap<String, Vector<Attribute>>();
		this.facadeConstructorParametersStack = new HashMap<String, Vector<Attribute>>();
		this.generatedFiles = new Vector<String>();
		this.repositoryPath = "";
		this.configurationFiles = new Vector<Artifact>();
		this.modelFiles = new Vector<Artifact>();
		this.viewFiles = new Vector<Artifact>();
		this.controlFiles = new Vector<Artifact>();
		this.modelEntities = modelEntities;
		this.viewEntities = viewEntities;
		this.controllerEntities = controllerEntities;
		this.featureTarget = "";
		this.featureModel = new Vector<String>();
	}
	
	public Vector<String> getFeatureModel() {
		return featureModel;
	}

	public void setFeatureModel(Vector<String> featureModel) {
		this.featureModel = featureModel;
	}

	public String getRepositoryPath() {
		return repositoryPath;
	}

	public void setRepositoryPath(String repositoryPath) {
		this.repositoryPath = repositoryPath;
	}

	public AbstractTypeConverter getAtc() {
		return typeConverter;
	}

	public void setAtc(AbstractTypeConverter atc) {
		this.typeConverter = atc;
	}

	public void addArtifactFile(String file) {
		this.generatedFiles.add(file);
	}

	public void addConfigurationFile(Artifact configurationFile) {
		this.configurationFiles.add(configurationFile);
	}

	public void addModelFile(Artifact modelFile) {
		this.modelFiles.add(modelFile);
	}

	public void addViewFile(Artifact viewFile) {
		this.viewFiles.add(viewFile);
	}

	public void addControlFile(Artifact controlFile) {
		this.controlFiles.add(controlFile);
	}

	public List<String> getGeneratedFiles() {
		return new ArrayList<String>(generatedFiles);
	}

	public void setGeneratedFiles(Vector<String> generatedFiles) {
		this.generatedFiles = generatedFiles;
	}

	public Vector<Artifact> getConfigurationFiles() {
		return configurationFiles;
	}

	public void setConfigurationFiles(Vector<Artifact> configurationFiles) {
		this.configurationFiles = configurationFiles;
	}

	public Vector<Artifact> getModelFiles() {
		return modelFiles;
	}

	public void setModelFiles(Vector<Artifact> modelFiles) {
		this.modelFiles = modelFiles;
	}

	public Vector<Artifact> getViewFiles() {
		return viewFiles;
	}

	public void setViewFiles(Vector<Artifact> viewFiles) {
		this.viewFiles = viewFiles;
	}

	public Vector<Artifact> getControlFiles() {
		return controlFiles;
	}

	public void setControlFiles(Vector<Artifact> controlFiles) {
		this.controlFiles = controlFiles;
	}

	public MVCModel getModelEntities() {
		return modelEntities;
	}

	public void setModelEntities(MVCModel modelEntities) {
		this.modelEntities = modelEntities;
	}

	public MVCView getViewEntities() {
		return viewEntities;
	}

	public void setViewEnitities(MVCView viewEntities) {
		this.viewEntities = viewEntities;
	}

	public MVCControl getControllerEntities() {
		return controllerEntities;
	}

	public void setControllerEntities(MVCControl controllerEntities) {
		this.controllerEntities = controllerEntities;
	}

	public String getFeatureTarget() {
		return featureTarget;
	}

	public void setFeatureTarget(String featureTarget) {
		this.featureTarget = featureTarget;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Boolean getFeatureFlag() {
		return featureFlag;
	}

	public void setFeatureFlag(Boolean featureFlag) {
		this.featureFlag = featureFlag;
	}

	public void generateProjectBasicStructure(Vector<String> paths) {
		File f = null;

		for (String currentPath : paths) {
			try {
				f = new File(this.getFeatureTarget() + BASE_DIR + currentPath);
				f.mkdirs();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void generateFile(String filePath, String fileName, String fileContent) {
		File f;
		FileWriter fstream;
		BufferedWriter out;

		try {
			f = new File(this.getFeatureTarget() + "/" + filePath);
			f.mkdirs();
			f = new File(this.getFeatureTarget() + "/" + filePath + fileName);
			f.createNewFile();
			this.addArtifactFile(f.getPath());
			fstream = new FileWriter(f.getAbsolutePath());
			out = new BufferedWriter(fstream);
			out.write(fileContent);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void generateOutputFile(String fileAbsolutePath, String fileName, String content) {
		File f;
		FileWriter fstream;
		BufferedWriter out;

		try {
			f = new File(fileAbsolutePath);
			f.mkdirs();
			f = new File(fileName);
			f.createNewFile();

			fstream = new FileWriter(f.getAbsolutePath());
			out = new BufferedWriter(fstream);
			out.write(content);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Generate file and add filename in list
	 * @param feature file feature
	 * 
	 * @return file absolute path
	 */
	public String generateArtifactFile(String feature, String filePath, String fileName, String fileContent) {
		String pathFile = generateFile(feature, filePath, fileName, fileContent);
		addArtifactFile(pathFile);
		return pathFile;
	}
	
	/**
	 * Generate file
	 * @return file absolute path
	 */
	public String generateFile(String feature, String filePath, String fileName, String fileContent) {
		File f = null;
		FileWriter fstream;
		BufferedWriter out;

		try {
			f = new File(this.getFeatureTarget() + "/" + feature + "/" + filePath);
			f.mkdirs();
			f = new File(this.getFeatureTarget() + "/" + feature + "/" + filePath + fileName);
			f.createNewFile();
			fstream = new FileWriter(f.getAbsolutePath());
			out = new BufferedWriter(fstream);
			out.write(fileContent);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f.getAbsolutePath();
	}
	

	public void printModelConstructorsStack() {
		for (String modelConstructorParametersKey : modelConstructorParametersStack.keySet()) {
			System.out.println(modelConstructorParametersKey + "\n");
			Vector<Attribute> modelConstructorParameters = modelConstructorParametersStack.get(modelConstructorParametersKey);
			if (modelConstructorParameters == null) {
				modelConstructorParameters = new Vector<Attribute>();
			}

			for (Attribute attribute : modelConstructorParameters) {
				System.out.println("\t" + attribute.getType() + " " + attribute.getName());
			}
		}
	}

	public void convertModelTypes() {
		for (Model model : this.getModelEntities().getModels()) {
			for (Attribute attribute : model.getAttributes()) {
				typeConverter.visit(attribute);
			}

			for (Operation operation : model.getOperations()) {
				typeConverter.visit(operation);

				for (Parameter parameter : operation.getParameters()) {
					typeConverter.visit(parameter);
				}
			}
		}

		for (Facade facade : this.getModelEntities().getFacades()) {
			for (Attribute attribute : facade.getAttributes()) {
				typeConverter.visit(attribute);
			}

			for (Operation operation : facade.getOperations()) {
				typeConverter.visit(operation);

				for (Parameter parameter : operation.getParameters()) {
					typeConverter.visit(parameter);
				}
			}
		}
	}


	public void copyFile(File sourceFile, File destFile) throws IOException {
		if (!destFile.exists()) {
			try {
				destFile.createNewFile();
			} catch (IOException ioe) {
				System.out.println(destFile.getAbsolutePath());
			}
		}

		FileChannel source = null;
		FileChannel destination = null;
		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			destination.transferFrom(source, 0, source.size());
		} finally {
			if (source != null) {
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
	}

	public void generateBasicProjectStructure(Vector<String> paths) {
		File f = null;

		for (String s : paths) {
			try {
				f = new File(this.getFeatureTarget() + BASE_DIR + s);
				f.mkdirs();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void generateBaseFiles() {
		File f;
		if (this.featureFlag) {
			try {
				f = new File(this.getFeatureTarget() + BASE_DIR);
				f.mkdirs();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		generateModelBaseFiles();
		generateViewBaseFiles();
		generateControllerBaseFiles();
	}

	public void generateRefinementFiles() {
		generateModelRefinementFiles();
		generateViewRefinementFiles();
		generateControllerRefinementFiles();
	}

	public abstract void generateModelBaseFiles();

	public abstract void generateViewBaseFiles();

	public abstract void generateControllerBaseFiles();

	public abstract void generateModelRefinementFiles();

	public abstract void generateViewRefinementFiles();

	public abstract void generateControllerRefinementFiles();
}
