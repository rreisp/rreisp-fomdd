package core.scripts.executable;

import core.scripts.ScriptCommand;
import core.scripts.ScriptUtils;

public class StepA2_GenerateMappingTemplates implements ScriptCommand {
	ScriptUtils scriptUtils;

	public StepA2_GenerateMappingTemplates() {
		scriptUtils = ScriptUtils.getInstance();
	}

	@Override
	public void execute() {
		String project = scriptUtils.getProperty("project.location");
		String simplifiedDataModel = scriptUtils.getProperty("simplified.data.model");
		String simplifiedStateModel = scriptUtils.getProperty("simplified.state.model");
		String featureDataMappingModel = scriptUtils.getProperty("feature.data.model");
		String featureStateMappingModel = scriptUtils.getProperty("feature.state.model");
		String layoutStateMappingModel = scriptUtils.getProperty("layout.state.model");
		
		/*
		 * Generate Feature x Data Mapping Template
		 */				
		ScriptUtils.executeXMLTransformationScript(project + "/transformations/mapping/GenerateFeatureDataModelMappingTemplate.xsl", simplifiedDataModel, featureDataMappingModel + "~");
		ScriptUtils.executeXMLFormatterCommand(featureDataMappingModel + "~", featureDataMappingModel);
		ScriptUtils.executeRemoveFileCommand(featureDataMappingModel + "~");

		/*
		 * Generate Feature x State Mapping Template
		 */		
		ScriptUtils.executeXMLTransformationScript(project + "/transformations/mapping/GenerateFeatureStateModelMappingTemplate1.xsl", simplifiedStateModel, featureStateMappingModel + "~");
		ScriptUtils.executeXMLFormatterCommand(featureStateMappingModel + "~", featureStateMappingModel);
		ScriptUtils.executeXMLTransformationScript(project + "/transformations/mapping/GenerateFeatureStateModelMappingTemplate2.xsl", featureStateMappingModel, featureStateMappingModel + "~");
		ScriptUtils.executeXMLFormatterCommand(featureStateMappingModel + "~", featureStateMappingModel);
		ScriptUtils.executeRemoveFileCommand(featureStateMappingModel + "~");
		
		/*
		 * Generate Layout x State Mapping Template
		 */
		ScriptUtils.executeXMLTransformationScript(project + "/transformations/mapping/GenerateLayoutStateModelMappingTemplate1.xsl", simplifiedStateModel, layoutStateMappingModel + "~");
		ScriptUtils.executeXMLFormatterCommand(layoutStateMappingModel + "~", layoutStateMappingModel);
		ScriptUtils.executeXMLTransformationScript(project + "/transformations/mapping/GenerateLayoutStateModelMappingTemplate2.xsl", layoutStateMappingModel, layoutStateMappingModel + "~");
		ScriptUtils.executeXMLFormatterCommand(layoutStateMappingModel + "~", layoutStateMappingModel);
		ScriptUtils.executeRemoveFileCommand(layoutStateMappingModel + "~");		
	}

	public static void main(String args[]) {
		System.out.println("Step A2 - Generating mapping templates");
		StepA2_GenerateMappingTemplates step2 = new StepA2_GenerateMappingTemplates();
		System.out.println("Executing...");
		step2.execute();
		System.out.println("Terminated!");
	}
}
