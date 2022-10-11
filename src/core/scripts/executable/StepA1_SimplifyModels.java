package core.scripts.executable;

import core.scripts.ScriptCommand;
import core.scripts.ScriptUtils;
import core.transformation.StateModelSimplifier;

public class StepA1_SimplifyModels implements ScriptCommand {
	ScriptUtils scriptUtils;

	public StepA1_SimplifyModels() {
		scriptUtils = ScriptUtils.getInstance();
	}

	@Override
	public void execute() {
		/*
		 * Simplify Data Model
		 */
		String model = scriptUtils.getProperty("emf.data.model");
		String simplifiedModel = scriptUtils.getProperty("simplified.data.model");
		String simplifyDataModelScript = scriptUtils.getProperty("simplify.data.model.script");

		ScriptUtils.executeXMLTransformationScript(simplifyDataModelScript, model, simplifiedModel + "~");
		ScriptUtils.executeXMLFormatterCommand(simplifiedModel + "~", simplifiedModel);
		ScriptUtils.executeRemoveFileCommand(simplifiedModel + "~");
	
		
		/*
		 * Simplify State Model
		 */
		model = scriptUtils.getProperty("emf.state.model");
		simplifiedModel = scriptUtils.getProperty("simplified.state.model");

		StateModelSimplifier.simplify(model, simplifiedModel + "~");
		
		ScriptUtils.executeXMLFormatterCommand(simplifiedModel + "~", simplifiedModel);
		ScriptUtils.executeRemoveFileCommand(simplifiedModel + "~");
	}

	public static void main(String args[]) {
		System.out.println("Step A1 - Simplifying input models");
		StepA1_SimplifyModels step1 = new StepA1_SimplifyModels();
		System.out.println("Executing...");
		step1.execute();
		System.out.println("Terminated!");
	}
}
