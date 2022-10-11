package core.scripts.executable;

import cartridge.ServletCartridgeA;
import core.scripts.ScriptCommand;
import core.scripts.ScriptUtils;

public class StepA5_GenerateArtifacts implements ScriptCommand {
	ScriptUtils scriptUtils;

	public StepA5_GenerateArtifacts() {
		scriptUtils = ScriptUtils.getInstance();
	}

	@Override
	public void execute() {
		ServletCartridgeA cartridge = new ServletCartridgeA();
		cartridge.run();

	}

	public static void main(String args[]) {
		StepA5_GenerateArtifacts step5 = new StepA5_GenerateArtifacts();
		System.out.println("Executing...");
		step5.execute();
		System.out.println("Terminated!");
	}
}
