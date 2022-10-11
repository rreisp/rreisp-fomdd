package core.scripts.executable;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

import core.scripts.ScriptCommand;
import core.scripts.ScriptUtils;

public class StepA7_Jak2Java implements ScriptCommand {
	ScriptUtils scriptUtils;

	public StepA7_Jak2Java() {
		scriptUtils = ScriptUtils.getInstance();
	}

	private FileFilter newDirectoryFilter() {
		FileFilter filter = new FileFilter() {
			public boolean accept(File file) {
				return file.isDirectory();
			}
		};
		return filter;
	}

	private FilenameFilter newFileFilter() {
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return !name.startsWith(".");
			}
		};
		return filter;
	}

	private void expandDirectories(String baseDirectory) {
		File baseFile = new File(baseDirectory);

		// This filter only returns directories
		File[] directories = baseFile.listFiles(newDirectoryFilter());

		for (File directory : directories) {
			expandDirectories(directory.getAbsolutePath());
		}

		// This filter only returns files
		File[] files = baseFile.listFiles(newFileFilter());

		for (File file : files) {
			if (file.getName().contains(".jak")) {
				scriptUtils.executeCommandWithReturn("jak2java " + file.getAbsolutePath(), null);
				scriptUtils.executeCommandWithReturn("rm " + file.getAbsolutePath(), null);
			}
		}
	}

	@Override
	public void execute() {
		String baseDirectory = scriptUtils.getProperty("target.product.dir.base");
		expandDirectories(baseDirectory);
	}

	public static void main(String args[]) {
		StepA7_Jak2Java step7 = new StepA7_Jak2Java();
		System.out.println("Executing...");
		step7.execute();
		System.out.println("Terminated!");
	}
}
