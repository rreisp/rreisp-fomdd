package core.fieldtype;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Vector;

import core.mvc.view.Element;
import core.mvc.view.Transition;

public class HTMLElementTranslator extends ElementTranslator {

	private boolean radioGroupFlag;
	private String radioGroupName;

	private static String dynamicCodeStart = "<jsp:scriptlet>// Dynamic code starts here// Use CDATA to insert your Java source code</jsp:scriptlet>";
	private static String dynamicCodeEnd = "<jsp:scriptlet>// Dynamic code ends here// Use CDATA to insert your Java source code</jsp:scriptlet>";

	public HTMLElementTranslator() {
		super();
		radioGroupName = "";
		radioGroupFlag = false;
	}

	public String getElementText(Element e) {
		StringBuilder output = new StringBuilder();
		Vector<Element> childElements = e.getElements();

		output.append(translateBegin(e));
		for (Element child : childElements) {
			output.append(getElementText(child));
		}
		output.append(translateEnd(e));

		return output.toString();
	}

	@Override
	public String translateBegin(Element e) {
		String s = "";

		e.setName(e.getName().replace(".", ""));

		if (e.getType().compareTo("Table") == 0) {
			s += "<table id=\"" + e.getName() + "\">\n";
		}

		else if (e.getType().compareTo("Form") == 0) {
			s += "<form id=\"" + e.getName() + "\">\n";
		}

		else if (e.getType().compareTo("TextField") == 0) {
			s += "<input type=\"text\" id=\"" + e.getName() + "\" name=\"" + e.getName() + "\" />\n";
		}

		else if (e.getType().compareTo("TextArea") == 0) {
			s += "<textarea id=\"" + e.getName() + "\" name=\"" + e.getName() + "\">FREETEXT\n";
		}

		else if (e.getType().compareTo("CheckBox") == 0) {
			s += "<input type=\"checkbox\" id=\"" + e.getName() + "\" name=\"" + e.getName() + "\" value=\"\" />\n";
		}

		else if (e.getType().compareTo("SubmitButton") == 0) {
			s += "<input type=\"hidden\" name=\"action\" value=\"\" /><br /><input type=\"submit\" id=\"" + e.getName()
					+ "\" name=\""
					+ e.getName() + "\" value=\"\" />\n";
		}

		else if (e.getType().compareTo("ResetButton") == 0) {
			s += "<input type=\"reset\" id=\"" + e.getName() + "\" name=\"" + e.getName() + "\" value=\"\" />\n";
		}

		else if (e.getType().compareTo("RadioGroup") == 0) {
			this.radioGroupFlag = true;
			this.radioGroupName = e.getName();
			s += "<div id=\"" + e.getName() + "\" />";
		}

		else if (e.getType().compareTo("RadioButton") == 0) {
			if (this.radioGroupFlag) {
				s += "<input type=\"radio\" name=\"" + this.radioGroupName + "\" id=\"" + e.getName()
						+ "\" value=\"\" />\n";
			}
			else {
				s += "<input type=\"radio\" name=\"" + e.getName() + "\" id=\"" + e.getName() + "\" value=\"\" />\n";
			}
		}

		else if (e.getType().compareTo("SimpleButton") == 0) {
			s += "<button type=\"button\" id=\"" + e.getName() + "\" name=\"" + e.getName() + "\">\n";
		}

		else if (e.getType().compareTo("SimplePane") == 0) {
			s += "<div id=\"" + e.getName() + "\">\n";
		}

		else if (e.getType().compareTo("ScrollPane") == 0) {
			s += "<div id=\"" + e.getName() + "\">\n";
		}

		else if (e.getType().compareTo("TextualLink") == 0) {
			// Issue 2
			if (e.getTransitions().size() > 0) {
				Transition t = e.getTransitions().get(0);
				if (t.getName() == null || t.getName().compareTo("") == 0 || t.getName().startsWith("onClick")) {
					s += "<a id=\"" + e.getName() + "\" href=\"exe.cute?action=goTo" + t.getTarget().split("\\.")[0]
							+ "\">"
							+ t.getTarget().split("\\.")[0];
				}
			}
			else {
				s += "<a id=\"" + e.getName() + "\" href=\"#\">LINK";
				;
			}
		}

		else if (e.getType().compareTo("Label") == 0) {
			s += "<label id=\"" + e.getName() + "\" for=\"\">LABEL";
		}

		else if (e.getType().compareTo("TableRow") == 0) {
			s += "<tr id=\"" + e.getName() + "\">\n";
		}

		else if (e.getType().compareTo("TableColumn") == 0) {
			s += "<td id=\"" + e.getName() + "\">\n";
		}

		else if (e.getType().compareTo("TableHeader") == 0) {
			s += "<th id=\"" + e.getName() + "\">\n";
		}

		else if (e.getType().compareTo("SimpleImage") == 0) {
			s += "<img src=\"\" border=\"\" />\n";
		}

		else if (e.getType().compareTo("ImageLink") == 0) {
			// Issue 2
			if (e.getTransitions().size() > 0) {
				Transition t = e.getTransitions().get(0);
				if (t.getName() == null || t.getName().compareTo("") == 0 || t.getName().startsWith("onClick")) {
					s += "<a id=\"" + e.getName() + "\" href=\"exe.cute?action=goTo" + t.getTarget().split("\\.")[0]
							+ "\">" + t.getTarget().split("\\.")[0] + "<img src=\"\" border=\"\" />";
				}
			}
			else {
				s += "<a id=\"" + e.getName() + "\" href=\"#\">LINK<img src=\"\" border=\"\" />";
				;
			}
		}

		else if (e.getType().compareTo("HorizontalList") == 0) {
			s += "<ul id=\"" + e.getName() + "\">\n";
		}

		else if (e.getType().compareTo("VerticalList") == 0) {
			s += "<ul id=\"" + e.getName() + "\">\n";
		}

		else if (e.getType().compareTo("ListItem") == 0) {
			s += "<li id=\"" + e.getName() + "\">ITEM";
		}

		else if (e.getType().compareTo("FreeText") == 0) {
			s += "<span id=\"" + e.getName() + "\">FREETEXT";
		}

		else if (e.getType().compareTo("CheckBox") == 0) {
			s += "<input id=\"" + e.getName() + "\" type=\"checkbox\" name=" + e.getName()
					+ " value=\"[Change this value]\" />\n";
		}

		else if (e.getType().compareTo("FileChooser") == 0) {
			s += "<input id=\"" + e.getName() + "\" type=\"file\" name=" + e.getName() + "/>\n";
		}

		else if (e.getType().compareTo("DynamicRowSet") == 0) {
			s += "\n<!-- Dynamic code starts here  -->\n";
			s += dynamicCodeStart;
			s += "<tr id=\"" + e.getName() + "\"> \n";
		}

		else if (e.getType().compareTo("FormItem") == 0) {
			s += "<div id=\"" + e.getName() + "\" />";
		}

		else {
			s += "<UNKNOWNTAG>>\n";
		}
		return s;
	}

	@Override
	public String translateEnd(Element e) {
		String s = "";
		e.setName(e.getName().replace(".", ""));

		if (e.getType().compareTo("Table") == 0) {
			s += "</table>\n";
		}

		else if (e.getType().compareTo("Form") == 0) {
			s += "</form>\n";
		}

		else if (e.getType().compareTo("TextField") == 0) {
			s += "\n";
		}

		else if (e.getType().compareTo("TextArea") == 0) {
			s += "</textarea>\n";
		}

		else if (e.getType().compareTo("CheckBox") == 0) {
			s += "\n";
		}

		else if (e.getType().compareTo("SubmitButton") == 0) {
			s += "\n";
		}

		else if (e.getType().compareTo("ResetButton") == 0) {
			s += "\n";
		}

		else if (e.getType().compareTo("RadioGroup") == 0) {
			s += "";
		}

		else if (e.getType().compareTo("RadioButton") == 0) {
			s += "\n";
		}

		else if (e.getType().compareTo("SimpleButton") == 0) {
			s += "</button>\n";
		}

		else if (e.getType().compareTo("SimplePane") == 0) {
			s += "</div>\n";
		}

		else if (e.getType().compareTo("ScrollPane") == 0) {
			s += "</div>\n";
		}

		else if (e.getType().compareTo("TextualLink") == 0) {
			s += "</a>\n";
		}

		else if (e.getType().compareTo("Label") == 0) {
			s += "</label>";
		}

		else if (e.getType().compareTo("TableRow") == 0) {
			s += "</tr>\n";
		}

		else if (e.getType().compareTo("TableColumn") == 0) {
			s += "</td>\n";
		}

		else if (e.getType().compareTo("TableHeader") == 0) {
			s += "</th>\n";
		}

		else if (e.getType().compareTo("SimpleImage") == 0) {
			s += "\n";
		}

		else if (e.getType().compareTo("ImageLink") == 0) {
			s += "</a>\n";
		}

		else if (e.getType().compareTo("HorizontalList") == 0) {
			s += "</ul>\n";
		}

		else if (e.getType().compareTo("VerticalList") == 0) {
			s += "</ul>\n";
		}

		else if (e.getType().compareTo("ListItem") == 0) {
			s += "</li>\n";
		}

		else if (e.getType().compareTo("FreeText") == 0) {
			s += "</span>";
		}

		else if (e.getType().compareTo("CheckBox") == 0) {
			s += "\n";
		}

		else if (e.getType().compareTo("FileChooser") == 0) {
			s += "\n";
		}

		else if (e.getType().compareTo("DynamicRowSet") == 0) {
			s += "</tr>\n";
			s += dynamicCodeEnd;
			s += "<!-- Dynamic code ends here  -->\n";
		}

		else if (e.getType().compareTo("FormItem") == 0) {
			s += "";
		}

		else {
			s += "</UNKNOWNTAG>\n";
		}
		return s;
	}

	public String getContents(File aFile) {
		StringBuilder contents = new StringBuilder();

		try {
			BufferedReader input = new BufferedReader(new FileReader(aFile));
			try {
				String line = null;
				while ((line = input.readLine()) != null) {
					contents.append(line);
					contents.append(System.getProperty("line.separator"));
				}
			} finally {
				input.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return contents.toString();
	}
}
