package core.scripts.executable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;

import core.scripts.ScriptCommand;
import core.scripts.ScriptUtils;

public class StepA6_MergeArtifacts implements ScriptCommand {
	ScriptUtils scriptUtils;
	private Vector<String> xmlTypes;
	private Vector<String> jakTypes;

	private String domainTargetDir;
	private String productTargetDir;
	private Vector<String> generatedArtifacts;
	private HashMap<String, Vector<String>> artifactFeaturesMap;
	private Vector<String> commands;
	private static String PATH_SEPARATOR = "/";

	public static void main(String args[]) {
		StepA6_MergeArtifacts step6 = new StepA6_MergeArtifacts();
		System.out.println("Executing...");
		step6.execute();
		System.out.println("Terminated!");
	}
	
	public StepA6_MergeArtifacts() {
		scriptUtils = ScriptUtils.getInstance();

		domainTargetDir = scriptUtils.getProperty("target.domain.dir.base");
		productTargetDir = scriptUtils.getProperty("target.product.dir.base");

		xmlTypes = new Vector<String>();
		jakTypes = new Vector<String>();

		xmlTypes.add("html");
		xmlTypes.add("jsp");
		xmlTypes.add("js");
		xmlTypes.add("xml");
		jakTypes.add("jak");

		commands = new Vector<String>();
		generatedArtifacts = new Vector<String>();
		artifactFeaturesMap = new HashMap<String, Vector<String>>();

		readArtifactsFile();
		populate();
		mountCommands();
	}

	@Override
	public void execute() {
		String[] commandAux = new String[2];
		for (String command : commands) {
			if (command.contains(">")) {
				commandAux = command.split(" > ");
				ScriptUtils.executeCommandWithReturn(commandAux[0], commandAux[1]);
			}
			else {
				ScriptUtils.executeCommand(command);
			}
		}
	}

	private void readArtifactsFile() {
		// reads the artifacts name
		String artifactsFile = scriptUtils.getProperty("target.outputFile");
		FileInputStream fileStream;
		try {
			fileStream = new FileInputStream(artifactsFile);
			BufferedReader data = new BufferedReader(new InputStreamReader(fileStream));

			String artifact;
			while ((artifact = data.readLine()) != null) {
				generatedArtifacts.add(artifact);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void populate() {
		for (String artifactName : this.generatedArtifacts) {
			String artifact = artifactName.replace(domainTargetDir + PATH_SEPARATOR, "");
			StringTokenizer tokens = new StringTokenizer(artifact, PATH_SEPARATOR);
			String feature = tokens.nextToken();

			artifact = "";
			while (tokens.hasMoreTokens()) {
				artifact += PATH_SEPARATOR + tokens.nextToken();
			}

			Vector<String> artifactsFeatures = null;
			if (artifactFeaturesMap.get(artifact) == null) {
				artifactsFeatures = new Vector<String>();
				artifactsFeatures.add(feature);
			}
			else {
				artifactsFeatures = artifactFeaturesMap.get(artifact);
				if (!artifactsFeatures.contains(feature)) {
					artifactsFeatures.add(feature);
				}
			}
			artifactFeaturesMap.put(artifact, artifactsFeatures);
		}
	}

	private String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
	}

	private String getFileFolder(String fileName) {
		return fileName.substring(0, fileName.lastIndexOf(PATH_SEPARATOR));
	}

	private String generateAheadCommand(String artifact, String tool, String preParameters, String posParameters) {
		StringBuilder builder = new StringBuilder();
		builder.append(tool + " ");
		builder.append(preParameters);
		for (String feature : artifactFeaturesMap.get(artifact)) {
			builder.append(domainTargetDir + PATH_SEPARATOR);
			builder.append(feature);
			builder.append(artifact + " ");
		}
		builder.append(posParameters);
		builder.append(productTargetDir);
		builder.append(artifact);
		return builder.toString();
	}
	
	private boolean hasOneFeature(String artifact) {
		return (artifactFeaturesMap.get(artifact).size() == 1);
	}

	private String generateCopyCommand(String artifact) {
		return generateAheadCommand(artifact, "cp", "", "");
	}
	
	private String generateXakCommand(String artifact) {
		return generateAheadCommand(artifact, "xak", "-c ",  "-o ");
	}
	
	private String generateJampackCommand(String artifact) {
		return generateAheadCommand(artifact, "jampack", "-kt ", " > ");
	}
	
	

	private void mountCommands() {
		for (String artifact : artifactFeaturesMap.keySet()) {
			String extension = getFileExtension(artifact);

			if (hasOneFeature(artifact)) {
				commands.add(generateCopyCommand(artifact));
			}
			else {
				if (xmlTypes.contains(extension)) {
					commands.add(generateXakCommand(artifact));
				}
				else if (jakTypes.contains(extension)) {
					commands.add(generateJampackCommand(artifact));
				}
			}
			makeTargetDir(artifact);
		}
		Collections.sort(commands);
	}

	private void makeTargetDir(String artifact) {
		try {
			String fileFolder = getFileFolder(artifact);
			File f = new File(productTargetDir + fileFolder);
			f.mkdirs();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
