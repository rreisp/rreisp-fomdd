package core.transformation;

import java.io.File;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;

public class MergeScript {
	private Vector<String> xmlTypes;
	private Vector<String> jakTypes;

	private String featureTarget;
	private String domainName;
	private String productName;

	private Vector<String> generatedArtifacts;
	private Vector<String> copiedArtifacts;
	private HashMap<String, Vector<String>> map;
	private Vector<String> commands;

	public MergeScript(String featureTarget, String domainName, String productName, Vector<String> generatedArtifacts, Vector<String> copiedArtifacts) {
		this.xmlTypes = new Vector<String>();
		this.jakTypes = new Vector<String>();

		this.xmlTypes.add("html");
		this.xmlTypes.add("jsp");
		this.xmlTypes.add("js");
		this.xmlTypes.add("xml");

		this.jakTypes.add("jak");

		this.commands = new Vector<String>();
		this.featureTarget = featureTarget.replace("domain/", "");
		this.domainName = domainName;
		this.productName = productName;
		this.generatedArtifacts = generatedArtifacts;
		this.copiedArtifacts = copiedArtifacts;
		this.map = new HashMap<String, Vector<String>>();

		populate();
		mount();
	}

	private void populate() {
		for (String s : this.generatedArtifacts) {
			String file = s.replaceFirst(this.featureTarget + "domain/" + this.domainName + "/", "");
			StringTokenizer st = new StringTokenizer(file, "/");
			String feature = st.nextToken();

			file = "";
			while (st.hasMoreTokens()) {
				file += "/" + st.nextToken();
			}

			Vector<String> v = null;
			if (map.get(file) == null) {
				v = new Vector<String>();
				v.add(feature);
			} else {
				v = map.get(file);
				if (!v.contains(feature)) {
					v.add(feature);
				}
			}
			map.put(file, v);
		}
	}

	private void mount() {
		String script = "";

		for (String file : this.copiedArtifacts) {
			script = "";

			String ffile = file.substring(file.indexOf("Base") + 4, file.length());
			String s = this.featureTarget + "product/" + this.productName + ffile;
			File dir = new File(s.substring(0, file.lastIndexOf("/")));
			dir.mkdirs();
			script += "cp " + file + " " + this.featureTarget + "product/" + this.productName + ffile;
			commands.add(script);
		}

		for (String file : map.keySet()) {
			script = "";
			String extension = file.substring(file.lastIndexOf(".") + 1, file.length());
			String tool = "";
			String toolPreParameters = "";
			String toolPosParameters = "";

			if (this.xmlTypes.contains(extension)) {
				tool = "xak ";
				toolPreParameters = "-c ";
				toolPosParameters = "-o ";
			} else if (this.jakTypes.contains(extension)) {
				// tool = "jampack ";
				tool = "jampack ";
				toolPreParameters = "-t ";
				toolPosParameters = "> ";
			} else {
				tool = "cp ";
				toolPreParameters = "";
				toolPosParameters = "";
			}

			if (map.get(file).size() == 1) {
				Vector<String> sts = map.get(file);
				script += "cp " + this.featureTarget + "domain/" + this.domainName + "/" + sts.elementAt(0) + file + " ";
				toolPosParameters = " ";
			} else {
				script += tool + toolPreParameters;
				for (String feature : map.get(file)) {
					script += this.featureTarget + "domain/" + this.domainName + "/" + feature + file + " ";
					try {
						String fileFolder = file.substring(0, file.lastIndexOf("/"));
						File f = new File(this.featureTarget + "product/" + this.productName + "/" + fileFolder);
						f.mkdirs();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			script += toolPosParameters + this.featureTarget + "product/" + this.productName + file;
			this.commands.add(script);

		}
	}
}
