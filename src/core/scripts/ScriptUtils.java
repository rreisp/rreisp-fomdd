package core.scripts;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import net.jmatrix.eproperties.EProperties;

public class ScriptUtils {
	public String projectLocation;
	private EProperties propertiesFile;
	
	// Strings
	private static String XML_STARTLET = "xmlstarlet tr --omit-decl";
	private static String XML_PP = "xml_pp";
	private static String RM = "rm";
	private static String SPACE = " ";
	
	private void loadProperties() {
		propertiesFile = new EProperties();
		try {
			propertiesFile.load(new FileInputStream(projectLocation + "/src/core/scripts/FOMDD.properties"));
			propertiesFile.setProperty("project.location", projectLocation);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ScriptUtils() {
		Properties baseProperties = new Properties();
		try {
			baseProperties.load(new FileInputStream("project.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		projectLocation = baseProperties.getProperty("project.absolute.dir");
		if (projectLocation == null || projectLocation.length() == 0) {
			try {
				Process p = Runtime.getRuntime().exec("pwd");
				projectLocation = getOutput(p).replaceAll("\n", "");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		loadProperties();
	}

	private String getOutput(final Process p) throws IOException {
		InputStream is = p.getInputStream();
		if (is != null) {
			StringBuilder sb = new StringBuilder();
			String line;

			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				while ((line = reader.readLine()) != null) {
					sb.append(line).append("\n");
				}
			} finally {
				is.close();
			}
			return sb.toString();
		} else {
			return "";
		}
	}

	public static ScriptUtils getInstance() {
		return SingletonScriptUtils.instance;
	}

	private static class SingletonScriptUtils {
		private static ScriptUtils instance = new ScriptUtils();
	}

	public String getProperty(String key) {
		return (String) propertiesFile.getString(key);
	}

	public void addProperty(String key, String value) {

	}

	public static void executeXakCommand(String base, String outputPathFile, String... refinements) {
		StringBuilder command = new StringBuilder("xak -c ");
		command.append(base);
		for (String refinement : refinements) {
			command.append(" ");
			command.append(refinement);
		}
		command.append(" -o ");
		command.append(outputPathFile);
		executeCommand(command.toString());
	}


	private static void createFile(String filePath, String fileContent) {
		File f;
		FileWriter fstream;
		BufferedWriter out;

		try {
			f = new File(filePath);
			f.createNewFile();
			fstream = new FileWriter(f.getAbsolutePath());
			out = new BufferedWriter(fstream);
			out.write(fileContent);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Process executeCommand(String command) {
		System.out.println(command);
		Process p = null;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	
	public static void executeCommandWithReturn(String command, String returnFilePath) {
		Process p = executeCommand(command);
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		BufferedReader errorReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));

		String output = getProcessInformation(inputReader).toString();
		String error = getProcessInformation(errorReader).toString();
		if (returnFilePath != null) {
			createFile(returnFilePath, output);
		}
		
		showOutputError(output, error);
	}
	
	public static void executeXMLTransformationScript(String scriptPath, String modelPath, String outputFilePath) {
		executeCommandWithReturn(XML_STARTLET + SPACE + scriptPath + SPACE + modelPath, outputFilePath);
	}
	
	public static void executeXMLFormatterCommand(String inputModelFilePath, String outputModelFilePath) {
		executeCommandWithReturn(XML_PP + SPACE + inputModelFilePath, outputModelFilePath);
	}
	
	public static void executeRemoveFileCommand(String pathFile) {
		StringBuilder command = new StringBuilder(RM + SPACE);
		command.append(pathFile);
		executeCommand(command.toString());
	}

	public static StringBuilder getProcessInformation(BufferedReader buff) {
		// Write the command output in a new file
		StringBuilder builder = new StringBuilder();
		String line = "";
		try {
			line = buff.readLine();

			while (line != null) {
				builder.append(line + "\n");
				line = buff.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder;
	}

	private static void showOutputError(String output, String error) {
		if (!error.isEmpty()) {
			System.out.println("**** ERROR **** ERROR **** ERROR **** ERROR **** ERROR **** ERROR **** ERROR **** ERROR ****");
			System.out.println(error);
		}
		else if (output.contains("Exception") && output.contains(".java:")) {
			System.out.println("**** ERROR **** ERROR **** ERROR **** ERROR **** ERROR **** ERROR **** ERROR **** ERROR ****");
			System.out.println(output);
		}
	}
}
