package core.transformation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import core.model.input.XML2ObjectUMLState;
import core.model.state.Element;
import core.model.state.Screen;
import core.model.state.State;
import core.model.state.StateMachine;
import core.model.state.Transition;

public class StateModelSimplifier {
	/**
	 * @param args
	 */

	private static String currentScreen = "";
	private static String currentState = "";

	public static void simplify(String model, String simplifiedModel) {
		XML2ObjectUMLState stateModel = new XML2ObjectUMLState();
		stateModel.translate(new File(model));
		StateMachine sm = stateModel.getStateMachine();
		generateFile(stateModel, new File(simplifiedModel), sm);
	}

	public static void generateFile(XML2ObjectUMLState stateModel, File f, StateMachine sm) {
		FileWriter fstream;
		BufferedWriter out;
		StringBuffer buffer = new StringBuffer();
		buffer.append("<statemodel>\n");

		for (Screen s : sm.getScreens()) {
			currentScreen = s.getName();
			buffer.append("<screen name=\"" + s.getName() + "\">\n");

			for (State st : s.getStates()) {
				currentState = st.getName();

				buffer.append("<state name=\"" + s.getName() + "." + st.getName() + "\">\n");

				for (Element e : st.getElements()) {

					String type = null;
					if (e.getType() != null) {
						type = e.getType().substring(e.getType().lastIndexOf(":") + 1, e.getType().length());
					}

					buffer.append("<element name=\"" + s.getName() + "." + st.getName() + "." + e.getName() + "\" type=\"" + type + "\">\n");

					// Sorting elements
					Vector<Element> children = e.getElements();
					List<Element> unsortedElements = new ArrayList<Element>();
					unsortedElements.addAll(children);

					Collections.sort(unsortedElements, new Comparator<Element>() {
						@Override
						public int compare(Element o1, Element o2) {
							return o1.getName().compareTo(o2.getName());
						}
					});

					Vector<Element> sortedElements = new Vector<Element>();
					sortedElements.addAll(unsortedElements);

					e.setElements(sortedElements);

					generateElements(stateModel, buffer, e, e.getName());
					buffer.append("</element>\n");
				}

				buffer.append("</state>\n");
			}
			buffer.append("</screen>\n");
		}

		buffer.append("</statemodel>");

		try {
			f.createNewFile();
			fstream = new FileWriter(f.getAbsolutePath());
			out = new BufferedWriter(fstream);
			out.write(buffer.toString());
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void generateElements(XML2ObjectUMLState stateModel, StringBuffer buffer, Element e, String path) {

		for (Transition t : e.getTransitions()) {
			String target = stateModel.getNameById(t.getTarget());
			if (target.indexOf(".") == -1) {
				if (target.compareTo("ExternalURL") != 0) {
					target += ".Default";
				}
			}

			buffer.append("<transition name=\"" + t.getName() + "\" source=\"" + stateModel.getNameById(t.getSource()) + "\" target=\"" + target + "\"/>\n");
		}

		for (Element e1 : e.getElements()) {
			String type = null;
			if (e1.getType() != null) {
				type = e1.getType().substring(e1.getType().lastIndexOf(":") + 1, e1.getType().length());
			}

			buffer.append("<element name=\"" + currentScreen + "." + currentState + "." + path + "." + e1.getName() + "\" type=\"" + type + "\">\n");
			generateElements(stateModel, buffer, e1, path + "." + e1.getName());
			buffer.append("</element>");
		}
	}
}
