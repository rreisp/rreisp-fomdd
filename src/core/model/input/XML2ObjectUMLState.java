package core.model.input;

import java.io.File;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;
import org.w3c.dom.*;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import core.model.state.*;
import core.model.state.Element;

public class XML2ObjectUMLState {
	private DOMParser parser;
	private Document document;
	private HashMap<String, String> diagramElementsById;
	private HashMap<String, String> elementTypes;
	
	private int screenPointer;
	private int statePointer;
	private Vector<Integer> elementPointers;

	private StateMachine stateMachine;
	private String currentScreen;
	private String currentState;
	
	public XML2ObjectUMLState(){
		this.parser = new DOMParser();
		this.screenPointer = -1;
		this.statePointer = -1;
		this.elementPointers = new Vector<Integer>();
		this.elementPointers.add(-1);
		
		this.currentScreen = "";
		this.currentState = "";
		
		diagramElementsById = new HashMap<String, String>();
		this.elementTypes = new HashMap<String, String>();
	}

	public StateMachine translate(File input){
		try {
			this.parser = new DOMParser();
			parser.parse(input.getPath());
			this.document = parser.getDocument();
			
			Vector<String> profileTagNames = new Vector<String>();
			profileTagNames.add("StateModeling:AccordionMenu");
			profileTagNames.add("StateModeling:AutoCompleteField");
			profileTagNames.add("StateModeling:Bar");
			profileTagNames.add("StateModeling:Button");
			profileTagNames.add("StateModeling:Calendar");
			profileTagNames.add("StateModeling:Captcha");
			profileTagNames.add("StateModeling:CheckBox");
			profileTagNames.add("StateModeling:CheckBoxGroup");
			profileTagNames.add("StateModeling:ComboBox");
			profileTagNames.add("StateModeling:DefaultButton");
			profileTagNames.add("StateModeling:DynamicColumnSet");
			profileTagNames.add("StateModeling:DynamicItem");
			profileTagNames.add("StateModeling:DynamicItemSet");
			profileTagNames.add("StateModeling:DynamicRowSet");
			profileTagNames.add("StateModeling:DynamicTable");
			profileTagNames.add("StateModeling:EditorPane");
			profileTagNames.add("StateModeling:Expander");
			profileTagNames.add("StateModeling:FileChooser");
			profileTagNames.add("StateModeling:FileChooserButton");
			profileTagNames.add("StateModeling:FileChooserField");
			profileTagNames.add("StateModeling:FileChooserWidget");
			profileTagNames.add("StateModeling:FileUploadField");
			profileTagNames.add("StateModeling:FishEyeList");
			profileTagNames.add("StateModeling:FishEyeListItem");
			profileTagNames.add("StateModeling:FishEyeMenu");
			profileTagNames.add("StateModeling:FontSelectionWidget");
			profileTagNames.add("StateModeling:Form");
			profileTagNames.add("StateModeling:FreeText");
			profileTagNames.add("StateModeling:FullFeaturedWidget");
			profileTagNames.add("StateModeling:HiddenField");
			profileTagNames.add("StateModeling:HorizontalList");
			profileTagNames.add("StateModeling:HorizontalMenu");
			profileTagNames.add("StateModeling:HorizontalSeparator");
			profileTagNames.add("StateModeling:HorizontalSlider");
			profileTagNames.add("StateModeling:Image");
			profileTagNames.add("StateModeling:ImageButton");
			profileTagNames.add("StateModeling:ImageLink");
			profileTagNames.add("StateModeling:ImageMapMenu");
			profileTagNames.add("StateModeling:ImageMenuItem");
			profileTagNames.add("StateModeling:ImageSliderPane");
			profileTagNames.add("StateModeling:InputComboBox");
			profileTagNames.add("StateModeling:InputField");
			profileTagNames.add("StateModeling:Label");
			profileTagNames.add("StateModeling:Link");
			profileTagNames.add("StateModeling:List");
			profileTagNames.add("StateModeling:ListElement");
			profileTagNames.add("StateModeling:ListItem");
			profileTagNames.add("StateModeling:MapAPI");
			profileTagNames.add("StateModeling:MapArea");
			profileTagNames.add("StateModeling:MapViewer");
			profileTagNames.add("StateModeling:Menu");
			profileTagNames.add("StateModeling:MenuBar");
			profileTagNames.add("StateModeling:MenuItem");
			profileTagNames.add("StateModeling:NavigatiionBar");
			profileTagNames.add("StateModeling:NavigationBar");
			profileTagNames.add("StateModeling:NavigationMenu");
			profileTagNames.add("StateModeling:OrderedList");
			profileTagNames.add("StateModeling:Pane");
			profileTagNames.add("StateModeling:Paragraph");
			profileTagNames.add("StateModeling:PasswordField");
			profileTagNames.add("StateModeling:ProgressBar");
			profileTagNames.add("StateModeling:RadioButton");
			profileTagNames.add("StateModeling:RadioGroup");
			profileTagNames.add("StateModeling:RatingElement");
			profileTagNames.add("StateModeling:ResetButton");
			profileTagNames.add("StateModeling:RichTextAreaEditor");
			profileTagNames.add("StateModeling:RolloverImage");
			profileTagNames.add("StateModeling:RSSFeed");
			profileTagNames.add("StateModeling:ScaleButton");
			profileTagNames.add("StateModeling:ScrollPane");
			profileTagNames.add("StateModeling:Separator");
			profileTagNames.add("StateModeling:SimpleButton");
			profileTagNames.add("StateModeling:SimpleComboBox");
			profileTagNames.add("StateModeling:SimpleHorizontalList");
			profileTagNames.add("StateModeling:SimpleImage");
			profileTagNames.add("StateModeling:SimpleList");
			profileTagNames.add("StateModeling:SimpleListItem");
			profileTagNames.add("StateModeling:SimplePane");
			profileTagNames.add("StateModeling:SimpleText");
			profileTagNames.add("StateModeling:SimpleVerticalList");
			profileTagNames.add("StateModeling:SlidePanel");
			profileTagNames.add("StateModeling:Slider");
			profileTagNames.add("StateModeling:SpecializedWidgets");
			profileTagNames.add("StateModeling:SpinField");
			profileTagNames.add("StateModeling:SplitPane");
			profileTagNames.add("StateModeling:StaticTable");
			profileTagNames.add("StateModeling:StatusBar");
			profileTagNames.add("StateModeling:SubmitButtn");
			profileTagNames.add("StateModeling:SubmitButton");
			profileTagNames.add("StateModeling:Tab");
			profileTagNames.add("StateModeling:TabbedPane");
			profileTagNames.add("StateModeling:Table");
			profileTagNames.add("StateModeling:TableColumn");
			profileTagNames.add("StateModeling:TableElement");
			profileTagNames.add("StateModeling:TableHeader");
			profileTagNames.add("StateModeling:TableRow");
			profileTagNames.add("StateModeling:TagCloud");
			profileTagNames.add("StateModeling:Text");
			profileTagNames.add("StateModeling:TextArea");
			profileTagNames.add("StateModeling:TextField");
			profileTagNames.add("StateModeling:TextPane");
			profileTagNames.add("StateModeling:TextualLink");
			profileTagNames.add("StateModeling:TextualMenuItem");
			profileTagNames.add("StateModeling:ToggleButton");
			profileTagNames.add("StateModeling:ToolBar");
			profileTagNames.add("StateModeling:Tree");
			profileTagNames.add("StateModeling:UnorderedList");
			profileTagNames.add("StateModeling:VerticalList");
			profileTagNames.add("StateModeling:VerticalMenu");
			profileTagNames.add("StateModeling:VerticalSeparator");
			profileTagNames.add("StateModeling:VerticalSlider");
			profileTagNames.add("StateModeling:FormItem");

			
			for(String s: profileTagNames){
				NodeList nl = document.getElementsByTagName(s);
				//System.out.println(nl.getLength());
				for(int i=0;i<nl.getLength();i++){
					Node n = nl.item(i);
					NamedNodeMap map = n.getAttributes();
					//System.out.println(n.getNodeName());

					//System.out.println(" "+ n.getNodeName());
					//System.out.println(map.getNamedItem("base_State").getNodeValue());
					this.elementTypes.put(map.getNamedItem("base_State").getNodeValue(),n.getNodeName());
					//System.out.println(map.getNamedItem("base_State").getNodeValue()+ " "+ n.getNodeName());
				}
			}
			
			Node root = document.getElementsByTagName("packagedElement").item(0);			
			NamedNodeMap nm = root.getAttributes();
			this.stateMachine = new StateMachine(nm.getNamedItem("name").getNodeValue());
			
			NodeList firstElements = root.getChildNodes();
			process("statemachine", firstElements);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(stateMachine.getName());
		return stateMachine;
	}

	public StateMachine getStateMachine() {
		return stateMachine;
	}

	public void setStateMachine(StateMachine stateMachine) {
		this.stateMachine = stateMachine;
	}
	
	public String getNameById(String id){
		return this.diagramElementsById.get(id);
	}
	
	public void process(String tagName, NodeList nl){
		if(tagName.compareTo("statemachine")==0){
			for(int i=0;i<nl.getLength();i++){
				if(nl.item(i).getNodeName().compareTo("region")==0){
					NodeList child = nl.item(i).getChildNodes();
					
					for(int j=0; j<child.getLength();j++){
						Node item = child.item(j);
						if(item.getNodeName().compareTo("subvertex")==0){
							NamedNodeMap nm = item.getAttributes();
							// screens
							if(nm.getNamedItem("xmi:type").getNodeValue().compareTo("uml:State")==0){
								screenPointer++;
								statePointer = -1;
								this.currentScreen = nm.getNamedItem("name").getNodeValue();
								this.stateMachine.addScreen(new Screen(currentScreen));
								this.diagramElementsById.put(nm.getNamedItem("xmi:id").getNodeValue(), 
								this.stateMachine.getScreens().elementAt(screenPointer).getName());
								//System.out.println("Screen "+ nm.getNamedItem("name").getNodeValue());
								process("screen",item.getChildNodes());
							}
						}			
					}
				}
			}		
		}
		else if(tagName.compareTo("screen")==0){
			for(int i=0;i<nl.getLength();i++){
				if(nl.item(i).getNodeName().compareTo("region")==0){
					NodeList child = nl.item(i).getChildNodes();
					
					for(int j=0; j<child.getLength();j++){
						Node item = child.item(j);
						if(item.getNodeName().compareTo("subvertex")==0){
							NamedNodeMap nm = item.getAttributes();
							// screen states
							if(nm.getNamedItem("xmi:type").getNodeValue().compareTo("uml:State")==0){
								statePointer++;
								//this.currentState = this.currentScreen +"."+ nm.getNamedItem("name").getNodeValue();
								this.currentState = nm.getNamedItem("name").getNodeValue();
								this.stateMachine.getScreens().elementAt(screenPointer).addState(new State(this.currentState));
								this.diagramElementsById.put(nm.getNamedItem("xmi:id").getNodeValue(), 
										this.stateMachine.getScreens().elementAt(screenPointer).getName() +"."+
										this.currentState	
										);
								//System.out.println("State "+ nm.getNamedItem("name").getNodeValue());
								process("state",item.getChildNodes());
							}
						}			
					}
				}
			}			
		}
		else if(tagName.compareTo("state")==0){
			for(int i=0;i<nl.getLength();i++){
				if(nl.item(i).getNodeName().compareTo("region")==0){
					NodeList child = nl.item(i).getChildNodes();
					
					for(int j=0; j<child.getLength();j++){
						Node item = child.item(j);
						NamedNodeMap nm = item.getAttributes();
				
						if(item.getNodeName().compareTo("subvertex")==0){
							// state elements
							if(nm.getNamedItem("xmi:type").getNodeValue().compareTo("uml:State")==0){
								//System.out.println(item.getNodeName() +" "+ nm.getNamedItem("name").getNodeValue());
								Element currentElementGrandpha = new Element(this.currentScreen,"screen",-2);
								Element currentElementParent = new Element(this.currentState,"state",-1);
								currentElementParent.setParent(currentElementGrandpha);
								
								Element currentElement = new Element(nm.getNamedItem("name").getNodeValue(),this.elementTypes.get(nm.getNamedItem("xmi:id").getNodeValue()), 0);
								currentElement.setParent(currentElementParent);
								String elementId = nm.getNamedItem("xmi:id").getNodeValue();
								
								NodeList allTransitions = document.getElementsByTagName("transition");
							
								for(int k=0;k<allTransitions.getLength();k++){
									Node t = allTransitions.item(k);
									NamedNodeMap tnm = t.getAttributes();
									
									String tName = null;
									try{
										tName = tnm.getNamedItem("name").getNodeValue();
									}
									catch(NullPointerException npe){
										tName = "";
									}
									//System.out.println(tnm.getNamedItem("source").getNodeValue());
									String tSource = tnm.getNamedItem("source").getNodeValue();									
									String tTarget = tnm.getNamedItem("target").getNodeValue();

									if(tSource.compareTo(elementId)==0){
										currentElement.addTransition(new Transition(tName, tSource, tTarget));
									}
								}
								
								this.stateMachine.getScreens().elementAt(screenPointer).getStates().elementAt(statePointer).addElement(currentElement);
								this.diagramElementsById.put(nm.getNamedItem("xmi:id").getNodeValue(), 
								this.currentScreen +"."+ this.currentState +"."+ currentElement.getName());								
								
								if(item.hasChildNodes()){
									processElement(currentElement,item.getChildNodes());
								}
							}
						}			
					}
				}
			}
		}
	}
	
	public void processElement(Element parent, NodeList nl){
		for(int i=0;i<nl.getLength();i++){
			if(nl.item(i).getNodeName().compareTo("region")==0){
				NodeList child = nl.item(i).getChildNodes();			

				for(int j=0; j<child.getLength();j++){
					Node item = child.item(j);

					if(item.getNodeName().compareTo("subvertex")==0){
						NamedNodeMap nm = item.getAttributes();
						// screen states
						if(nm.getNamedItem("xmi:type").getNodeValue().compareTo("uml:State")==0){
							//System.out.println("\t"+item.getNodeName() +" "+ nm.getNamedItem("name").getNodeValue());
							Element currentElement = new Element(nm.getNamedItem("name").getNodeValue(),this.elementTypes.get(nm.getNamedItem("xmi:id").getNodeValue()), parent.getDepth()+1);
							
							String elementId = nm.getNamedItem("xmi:id").getNodeValue();
							
							NodeList allTransitions = document.getElementsByTagName("transition");
						
							for(int k=0;k<allTransitions.getLength();k++){
								Node t = allTransitions.item(k);
								NamedNodeMap tnm = t.getAttributes();
								
								String tName = null;
								try{
									tName = tnm.getNamedItem("name").getNodeValue();
								}
								catch(NullPointerException npe){
									tName = "";
								}
								String tSource = tnm.getNamedItem("source").getNodeValue();
								String tTarget = tnm.getNamedItem("target").getNodeValue();
								
								if(tSource.compareTo(elementId)==0){
									currentElement.addTransition(new Transition(tName, tSource, tTarget));
								}
							}							
							
							currentElement.setParent(parent);
							parent.addElement(currentElement);
							
							String path = "";
							Element p = currentElement.getParent();
							
							while(p != null && p.getDepth() >= 0){
								path = p.getName() +"."+ path;
								p = p.getParent();								 
							}
							
							this.diagramElementsById.put(nm.getNamedItem("xmi:id").getNodeValue(), 
									this.currentScreen +"."+ this.currentState +"."+ path + currentElement.getName());								
							if(item.hasChildNodes()){
								processElement(currentElement, item.getChildNodes());
							}
						}
					}
				}				
			}
		}
	}
	
	public void print(){
		this.stateMachine.print();
	}
	
	public void printIds(){
		Set<String> ids = this.diagramElementsById.keySet();
		HashMap<String,String> reversedMap = new HashMap<String, String>();
		
		for(String id:ids){
			reversedMap.put(this.getNameById(id),id);
		}
		
		TreeSet<String> orderedIds = new TreeSet<String>();
		ids = reversedMap.keySet();

		for(String id:ids){
			orderedIds.add(id);
		}
		
		HashMap<String,String> orderedMap = new HashMap<String, String>();
		for(String id:orderedIds){
			System.out.println(reversedMap.get(id) +" --- "+ id);
			orderedMap.put(id,diagramElementsById.get(id));
		}		
	}
}
