package core.scripts.executable;

import core.scripts.ScriptCommand;
import core.scripts.ScriptUtils;

public class StepA3_ApplyMappingModels implements ScriptCommand {
	ScriptUtils scriptUtils;

	public StepA3_ApplyMappingModels() {
		scriptUtils = ScriptUtils.getInstance();
	}

	@Override
	public void execute() {
		String project = scriptUtils.getProperty("project.location");
		String simplifiedDataModel = scriptUtils.getProperty("simplified.data.model");
		String simplifiedStateModel = scriptUtils.getProperty("simplified.state.model");
		String mappedDataModel = scriptUtils.getProperty("mapped.data.model");
		String mappedStateModel = scriptUtils.getProperty("mapped.state.model");
		
		/*
		 * Maps features and data model elements
		 */				
		ScriptUtils.executeXMLTransformationScript(project + "/transformations/mapping/MapFeaturesAndDataModel.xsl", simplifiedDataModel, mappedDataModel + "~");
		ScriptUtils.executeXMLFormatterCommand(mappedDataModel + "~", mappedDataModel);
		ScriptUtils.executeRemoveFileCommand(mappedDataModel + "~");

		/*
		 * Maps features and state model elements
		 */				
		ScriptUtils.executeXMLTransformationScript(project + "/transformations/mapping/MapFeaturesAndStateModel.xsl", simplifiedStateModel, mappedStateModel + "~");
		ScriptUtils.executeXMLFormatterCommand(mappedStateModel + "~", mappedStateModel);
		ScriptUtils.executeRemoveFileCommand(mappedStateModel + "~");		
		
		/*
		 * Maps layout and state model elements
		 */				
		ScriptUtils.executeXMLTransformationScript(project + "/transformations/mapping/MapLayoutAndStateModel.xsl", mappedStateModel, mappedStateModel + "~");
		ScriptUtils.executeXMLFormatterCommand(mappedStateModel + "~", mappedStateModel);
		ScriptUtils.executeRemoveFileCommand(mappedStateModel + "~");
	}

	public static void main(String args[]) {
		System.out.println("Step A3 - Applying mapping models");
		StepA3_ApplyMappingModels step2 = new StepA3_ApplyMappingModels();
		System.out.println("Executing...");
		step2.execute();
		System.out.println("Terminated!");
	}
}
