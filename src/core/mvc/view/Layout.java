package core.mvc.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import core.scripts.ScriptUtils;

public class Layout {
	private HashMap<String, String> layoutPathMap;

	public Layout() {
		createLayoutPathMap();
	}

	private void createLayoutPathMap() {
		layoutPathMap = new HashMap<String, String>();
		String layoutPath = ScriptUtils.getInstance().getProperty("layout.dir.base");

		// TODO - Ler direto do diretorio
		layoutPathMap.put("1ColumnA", layoutPath + "1ColumnA.html");
		layoutPathMap.put("1ColumnB", layoutPath + "1ColumnB.html");
		layoutPathMap.put("1ColumnC", layoutPath + "1ColumnC.html");
	}

	public String getText(String layoutName) {
		String layoutFilePath = layoutPathMap.get(layoutName);
		File layoutFile = new File(layoutFilePath);
		String content = "";
		try {
			Scanner scanner = new Scanner(layoutFile);
			scanner.useDelimiter("\\Z");
			content = scanner.next();
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return content;
	}
}
