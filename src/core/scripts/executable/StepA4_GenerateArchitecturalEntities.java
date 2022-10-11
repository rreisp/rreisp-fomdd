package core.scripts.executable;

import core.scripts.ScriptCommand;
import core.scripts.ScriptUtils;

public class StepA4_GenerateArchitecturalEntities implements ScriptCommand {
	ScriptUtils scriptUtils;

	public StepA4_GenerateArchitecturalEntities() {
		scriptUtils = ScriptUtils.getInstance();
	}

	@Override
	public void execute() {
		String mappedDataModel = scriptUtils.getProperty("mapped.data.model");
		String mappedStateModel = scriptUtils.getProperty("mapped.state.model");

		String mvcModel = scriptUtils.getProperty("mvc.models");
		String mvcView = scriptUtils.getProperty("mvc.views");
		String mvcController = scriptUtils.getProperty("mvc.controllers");

		String data2ModelScript = scriptUtils.getProperty("data2model.script");
		String state2ControlScript = scriptUtils.getProperty("state2control.script");
		String state2ViewScript = scriptUtils.getProperty("state2view.script");

		/*
		 * Generate MVC Models
		 */
		ScriptUtils.executeXMLTransformationScript(data2ModelScript, mappedDataModel, mvcModel + "~");
		ScriptUtils.executeXMLFormatterCommand(mvcModel + "~", mvcModel);
		ScriptUtils.executeRemoveFileCommand(mvcModel + "~");

		/*
		 * Generate MVC Views
		 */
		ScriptUtils.executeXMLTransformationScript(state2ViewScript, mappedStateModel, mvcView + "~");
		ScriptUtils.executeXMLFormatterCommand(mvcView + "~", mvcView);
		ScriptUtils.executeRemoveFileCommand(mvcView + "~");

		/*
		 * Generate MVC Controllers
		 */
		ScriptUtils.executeXMLTransformationScript(state2ControlScript, mappedStateModel, mvcController + "~");
		ScriptUtils.executeXMLFormatterCommand(mvcController + "~", mvcController);
		ScriptUtils.executeRemoveFileCommand(mvcController + "~");
	}

	public static void main(String args[]) {
		System.out.println("Step A4 - Generating abstract architectural entities");
		StepA4_GenerateArchitecturalEntities step4 = new StepA4_GenerateArchitecturalEntities();
		System.out.println("Executing...");
		step4.execute();
		System.out.println("Terminated!");
	}
}
