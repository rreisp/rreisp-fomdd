package cartridge.A.jee.jsf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import cartridge.A.jee.jsf.src.conf.PersistenceXML;
import cartridge.A.jee.jsf.src.controller.ControllerAction;
import cartridge.A.jee.jsf.src.controller.ControllerServletBase;
import cartridge.A.jee.jsf.src.controller.ControllerServletRefinement;
import cartridge.A.jee.jsf.src.controller.GoToAction;
import cartridge.A.jee.jsf.src.controller.HandlerActionBase;
import cartridge.A.jee.jsf.src.controller.HandlerGuardBase;
import cartridge.A.jee.jsf.src.model.FacadeBeanBase;
import cartridge.A.jee.jsf.src.model.FacadeBeanRefinement;
import cartridge.A.jee.jsf.src.model.ModelAction;
import cartridge.A.jee.jsf.src.model.ModelBeanBase;
import cartridge.A.jee.jsf.src.model.ModelBeanRefinement;
import cartridge.A.jee.jsf.src.model.ModelRepositoryBase;
import cartridge.A.jee.jsf.src.model.NewModelAction;
import cartridge.A.jee.jsf.web.JSPBase;
import cartridge.A.jee.jsf.web.JSPRefinement;
import cartridge.A.jee.jsf.web.webinf.PersistenceXMLRefinement;
import cartridge.A.jee.jsf.web.webinf.WebXMLBase;
import cartridge.A.jee.jsf.web.webinf.WebXMLRefinement;
import core.artifact.ArtifactModel;
import core.fieldtype.JavaTypeConverter;
import core.model.MVCControl;
import core.model.MVCModel;
import core.model.MVCView;
import core.mvc.controller.Handler;
import core.mvc.controller.Screen;
import core.mvc.model.Attribute;
import core.mvc.model.Facade;
import core.mvc.model.Model;
import core.mvc.model.Operation;
import core.mvc.view.Element;
import core.refinement.ControllerRefinementDocument;
import core.refinement.ModelRefinementDocument;
import core.refinement.ViewRefinementDocument;
import core.scripts.ScriptUtils;

public class ServletArtifactModel extends ArtifactModel {
	private JavaTypeConverter javaTypeConverter;
	private Vector<String> baseArtifacts;

	/*
	 * Directories constants
	 */
	private static final String MODEL_DIR = "src/main/java/model/";
	private static final String MODEL_REPOS_DIR = "src/main/java/model/repository/";
	private static final String MODEL_ACTION_DIR = "src/main/java/model/action/";
	private static final String CONTROLLER_DIR = "src/main/java/controller/";
	private static final String CONTROLLER_ACTION_DIR = "src/main/java/controller/action/";
	private static final String VIEW_DIR = "src/main/webapp/";
	private static final String VIEW_WEBINF_DIR = "src/main/webapp/WEB-INF/";
	private static final String VIEW_IMAGES_DIR = "src/main/webapp/images/";
	private static final String RESOURCES_METAINF_DIR = "src/main/resources/META-INF/";

	private static final String LAYOUT_DIR = ScriptUtils.getInstance().getProperty("layout.dir.base");

	private static final String JSP_EXTENSION = ".jsp";
	private static final String JAK_EXTENSION = ".jak";

	/*
	 * Default Constructor
	 */
	public ServletArtifactModel(MVCModel models, MVCView views, MVCControl controllers) {
		super(models, views, controllers);
		this.baseArtifacts = new Vector<String>();

		this.javaTypeConverter = new JavaTypeConverter();
		this.setAtc(javaTypeConverter);
		convertModelTypes();

		/*
		 * Folders
		 */
		Vector<String> folders = new Vector<String>();
		folders.add(MODEL_DIR);
		folders.add(MODEL_REPOS_DIR);
		folders.add(MODEL_ACTION_DIR);
		folders.add(CONTROLLER_DIR);
		folders.add(CONTROLLER_ACTION_DIR);
		folders.add(RESOURCES_METAINF_DIR);
		folders.add(VIEW_DIR);
		folders.add(VIEW_WEBINF_DIR);
		folders.add(VIEW_IMAGES_DIR);

		generateBasicProjectStructure(folders);
	}

	public void generateBaseFiles() {
		super.generateBaseFiles();
		generateConfigurationBaseFiles();
	}

	public void generateRefinementFiles() {
		super.generateRefinementFiles();
		generateConfigurationRefinementFiles();
	}

	private String getJspName(String screen) {
		return screen.toLowerCase() + JSP_EXTENSION;
	}

	private String getJspLayoutName(String screen) {
		return screen.toLowerCase() + "_layout" + JSP_EXTENSION;
	}

	private String getJakName(String name) {
		return getJakModelName(name);
	}

	private String getJakModelName(String model) {
		return model + JAK_EXTENSION;
	}

	private String getJakFacadeName(String facade) {
		return facade + "Facade" + JAK_EXTENSION;
	}

	private String getJakModelRepositoryName(String modelRepository) {
		return modelRepository + "Repository" + JAK_EXTENSION;
	}

	private String getJakNewActionName(String model) {
		return "New" + model + "Action" + JAK_EXTENSION;
	}

	private String getJakActionName(String action) {
		return action + "Action" + JAK_EXTENSION;
	}

	private String getJakGoToActionName(String screen) {
		return "GoTo" + screen + JAK_EXTENSION;
	}

	private String getModelBaseTemplateOutput(Object... parameters) {
		ModelBeanBase modelBase = new ModelBeanBase();
		return modelBase.generate(parameters);
	}

	private String getModelRefinementTemplateOutput(Object... parameters) {
		ModelBeanRefinement modelRefinement = new ModelBeanRefinement();
		return modelRefinement.generate(parameters);
	}

	private String getFacadeBaseTemplateOutput(Object... parameters) {
		FacadeBeanBase facadeBase = new FacadeBeanBase();
		return facadeBase.generate(parameters);
	}

	private String getModelRepositoryBaseTemplateOutput(Object... parameters) {
		ModelRepositoryBase modelReposBase = new ModelRepositoryBase();
		return modelReposBase.generate(parameters);
	}

	private String getNewActionBaseTemplateOutput(Object... parameters) {
		NewModelAction newModelAction = new NewModelAction();
		return newModelAction.generate(parameters);
	}

	private String getFacadeRefinementTemplateOutput(Object... parameters) {
		FacadeBeanRefinement facadeBeanRefinement = new FacadeBeanRefinement();
		return facadeBeanRefinement.generate(parameters);
	}

	private String getJSPBaseTemplateOutput(Object... parameters) {
		JSPBase viewBase = new JSPBase();
		return viewBase.generate(parameters);
	}

	private String getJSPRefinementTemplateOutput(Object... parameters) {
		JSPRefinement viewRefinement = new JSPRefinement();
		return viewRefinement.generate(parameters);
	}

	private String getControllerServletBaseTemplateOutput(Object... parameters) {
		ControllerServletBase controllerServletBase = new ControllerServletBase();
		return controllerServletBase.generate(parameters);
	}

	private String getControllerServletRefinementTemplateOutput(Object... parameters) {
		ControllerServletRefinement controllerServletRefinement = new ControllerServletRefinement();
		return controllerServletRefinement.generate(parameters);
	}

	private String getHandlerActionBaseTemplateOutput(Object... parameters) {
		HandlerActionBase handlerAction = new HandlerActionBase();
		return handlerAction.generate(parameters);
	}

	private String getHandlerGuardBaseTemplateOutput(Object... parameters) {
		HandlerGuardBase handlerGuard = new HandlerGuardBase();
		return handlerGuard.generate(parameters);
	}

	private String generateHandlerTargetName(Handler handler) {
		String[] tokens = handler.getTarget().split("\\.");
		return tokens[0].toLowerCase();
	}

	private String generateHandlerElementName(String element) {
		return element.substring(0, 1).toUpperCase() + element.substring(1);
	}

	private String getGoToActionsBaseTemplateOutput(Object... parameters) {
		GoToAction goToAction = new GoToAction();
		return goToAction.generate(parameters);
	}

	@Override
	public void generateModelBaseFiles() {
		generateModelBase();
		generateFacadeBase();
		generateModelRepositoryBase();
		generateNewModelActionBase();
		generateModelActionBase();
	}

	@Override
	public void generateModelRefinementFiles() {
		generateModelRefinement();
		generateFacadeRefinement();
		generateModelRepositoryRefinement();
		generateNewModelActionRefinement();
	}

	private void generateModelActionBase() {
		ModelAction modelAction = new ModelAction();
		String modelActionContent = modelAction.generate(null);
		generateArtifactFile("Base", MODEL_ACTION_DIR, getJakModelName("ModelAction"), modelActionContent);
	}

	private void generateNewModelActionBase() {
		/*
		 * New Actions dos Modelos
		 */
		for (Model model : getModelEntities().getModels()) {
			if (model.isBaseFeature()) {

				String newModelActionContent = getNewActionBaseTemplateOutput(model);
				generateArtifactFile("Base", MODEL_ACTION_DIR, getJakNewActionName(model.getName()), newModelActionContent);

				baseArtifacts.add(MODEL_ACTION_DIR + getJakNewActionName(model.getName()));
			}
		}
	}

	private void generateModelRepositoryBase() {
		/*
		 * Repositorio de Modelos
		 */
		for (Model model : getModelEntities().getModels()) {
			if (model.isBaseFeature()) {
				ModelRefinementDocument mrd = new ModelRefinementDocument(model);
				Vector<Attribute> attributes = mrd.getAttributesByFeature("");

				String modelRepositoryContent = getModelRepositoryBaseTemplateOutput(model, attributes);
				generateArtifactFile("Base", MODEL_REPOS_DIR, getJakModelRepositoryName(model.getName()), modelRepositoryContent);

				baseArtifacts.add(MODEL_REPOS_DIR + getJakModelRepositoryName(model.getName()));
			}
		}
	}

	private void generateFacadeBase() {
		/*
		 * Fachadas (Views)
		 */
		for (Facade facade : this.getModelEntities().getFacades()) {
			if (facade.isBaseFeature()) {
				ModelRefinementDocument modelDocument = new ModelRefinementDocument(facade);
				Vector<Attribute> attributes = modelDocument.getAttributesByFeature("");
				Vector<Operation> operations = modelDocument.getOperationsByFeature("");

				String facadeContent = getFacadeBaseTemplateOutput(facade, attributes, operations);
				generateArtifactFile("Base", MODEL_REPOS_DIR, getJakFacadeName(facade.getName()), facadeContent);

				baseArtifacts.add(MODEL_REPOS_DIR + getJakFacadeName(facade.getName()));
				this.facadeConstructorParametersStack.put(facade.getName(), attributes);
			}
		}
	}

	private void generateModelBase() {
		/*
		 * Modelos (Tables)
		 */
		for (Model model : getModelEntities().getModels()) {
			if (model.isBaseFeature()) {
				ModelRefinementDocument modelDocument = new ModelRefinementDocument(model);
				Vector<Attribute> attributes = modelDocument.getAttributesByFeature("");
				Vector<Operation> operations = modelDocument.getOperationsByFeature("");

				String modelContent = getModelBaseTemplateOutput(model, attributes, operations);
				generateArtifactFile("Base", MODEL_DIR, getJakModelName(model.getName()), modelContent);

				baseArtifacts.add(MODEL_DIR + getJakModelName(model.getName()));
				this.modelConstructorParametersStack.put(model.getName(), attributes);
			}
		}
	}

	private void generateModelRefinement() {
		for (core.mvc.model.Model model : this.getModelEntities().getModels()) {
			ModelRefinementDocument modelRefinementDocument = new ModelRefinementDocument(model);

			for (String feature : modelRefinementDocument.getFeatures()) {
				if (!feature.isEmpty()) {
					Vector<Attribute> attributes = modelRefinementDocument.getAttributesByFeature(feature);
					Vector<Operation> operations = modelRefinementDocument.getOperationsByFeature(feature);

					Vector<Attribute> parametersStack = this.modelConstructorParametersStack.get(model.getName());
					if (parametersStack == null) {
						parametersStack = new Vector<Attribute>();
					}

					boolean needCreateBase = false;
					if (!baseArtifacts.contains(MODEL_DIR + getJakModelName(model.getName()))) {
						baseArtifacts.add(MODEL_DIR + getJakModelName(model.getName()));
						needCreateBase = true;
					}

					String modelContent;
					if (needCreateBase) {
						modelContent = getModelBaseTemplateOutput(model, attributes, operations);
					}
					else {
						modelContent = getModelRefinementTemplateOutput(model, attributes, operations, parametersStack, feature);
					}

					generateArtifactFile(feature, MODEL_DIR, getJakModelName(model.getName()), modelContent);
					this.modelConstructorParametersStack.put(model.getName(), parametersStack);
				}
			}
		}
	}

	private void generateFacadeRefinement() {
		for (core.mvc.model.Facade facade : this.getModelEntities().getFacades()) {
			ModelRefinementDocument modelRefinementDocument = new ModelRefinementDocument(facade);

			for (String feature : modelRefinementDocument.getFeatures()) {
				if (!feature.isEmpty()) {
					Vector<Attribute> attributes = modelRefinementDocument.getAttributesByFeature(feature);
					Vector<Operation> operations = modelRefinementDocument.getOperationsByFeature(feature);

					Vector<Attribute> parametersStack = this.facadeConstructorParametersStack.get(facade.getName());
					if (parametersStack == null) {
						parametersStack = new Vector<Attribute>();
					}

					boolean needCreateBase = false;
					if (!baseArtifacts.contains(MODEL_REPOS_DIR + getJakFacadeName(facade.getName()))) {
						baseArtifacts.add(MODEL_REPOS_DIR + getJakFacadeName(facade.getName()));
						needCreateBase = true;
					}

					String facadeContent = "";
					if (needCreateBase) {
						facadeContent = getFacadeBaseTemplateOutput(facade, attributes, operations);
					}
					else {
						facadeContent = getFacadeRefinementTemplateOutput(facade, attributes, operations, parametersStack, feature);
					}
					generateArtifactFile(feature, MODEL_REPOS_DIR, getJakFacadeName(facade.getName()), facadeContent);

					this.facadeConstructorParametersStack.put(facade.getName(), parametersStack);
				}
			}
		}
	}

	private void generateModelRepositoryRefinement() {
		/*
		 * Repositorio de Modelos
		 */
		for (core.mvc.model.Model model : getModelEntities().getModels()) {
			ModelRefinementDocument mrd = new ModelRefinementDocument(model);

			for (String feature : mrd.getFeatures()) {
				if (feature.compareTo("") != 0) {
					Vector<Attribute> attributes = mrd.getAttributesByFeature(feature);
					Vector<Operation> operations = mrd.getOperationsByFeature(feature);
					Vector<Attribute> parametersStack = this.modelConstructorParametersStack.get(model.getName());

					if (parametersStack == null) {
						parametersStack = new Vector<Attribute>();
					}

					boolean writeBase = false;
					if (!baseArtifacts.contains(MODEL_REPOS_DIR + model.getName() + "Repository.jak")) {
						baseArtifacts.add(MODEL_REPOS_DIR + model.getName() + "Repository.jak");
						writeBase = true;
					}

					Object[] parameter;
					String modelContent = "";

					if (writeBase) {
						parameter = new Object[3];
						parameter[0] = model;
						parameter[1] = attributes;
						parameter[2] = operations;

						ModelRepositoryBase modelRepositoryBase = new ModelRepositoryBase();
						modelContent = modelRepositoryBase.generate(parameter);

						generateArtifactFile(feature, MODEL_REPOS_DIR, model.getName() + "Repository.jak", modelContent);
						this.modelConstructorParametersStack.put(model.getName(), parametersStack);
					}
				}
			}
		}
	}

	private void generateNewModelActionRefinement() {
		/*
		 * New Actions dos Modelos
		 */
		NewModelAction newModelAction = new NewModelAction();
		for (core.mvc.model.Model model : getModelEntities().getModels()) {
			if (model.getFeature().compareTo("") != 0) {
				Object parameter[] = new Object[1];
				parameter[0] = model;

				baseArtifacts.add(MODEL_ACTION_DIR + "New" + model.getName() + "Action.jak");

				String newModelActionContent = newModelAction.generate(parameter);
				generateArtifactFile(model.getFeature(), MODEL_ACTION_DIR, "New" + model.getName() + "Action.jak",
						newModelActionContent);
			}
		}
	}

	@Override
	public void generateViewBaseFiles() {
		/*
		 * JSP Base
		 */
		for (core.mvc.view.Screen screen : getViewEntities().getScreens()) {
			ViewRefinementDocument viewDocument = new ViewRefinementDocument(screen);

			// A tela é da característica BASE
			if (screen.isBaseFeature()) {
				Vector<Element> elements = viewDocument.getAllElementsByFeature(screen.getFeature());

				if (elements.size() > 0) {
					String viewContent = getJSPBaseTemplateOutput(screen);
					String layoutFile = generateFile("Base", VIEW_DIR, getJspLayoutName(screen.getName()), viewContent);

					viewContent = getJSPRefinementTemplateOutput(elements);
					String elementsRefinementFile = generateArtifactFile("Base", VIEW_DIR, getJspName(screen.getName()), viewContent);

					composeJSPBaseFiles(layoutFile, elementsRefinementFile);
				}
				else {
					String viewContent = getJSPBaseTemplateOutput(screen);
					generateArtifactFile("Base", VIEW_DIR, getJspName(screen.getName()), viewContent);
				}

			}
		}
	}

	private void composeJSPBaseFiles(String jspLayoutBaseFile, String jspRefinementFile) {
		ScriptUtils.executeXakCommand(jspLayoutBaseFile, jspRefinementFile, jspRefinementFile);
		ScriptUtils.executeRemoveFileCommand(jspLayoutBaseFile);
	}

	@Override
	public void generateViewRefinementFiles() {
		/*
		 * JSP Refinements
		 */
		for (core.mvc.view.Screen screen : getViewEntities().getScreens()) {
			ViewRefinementDocument viewDocument = new ViewRefinementDocument(screen);

			baseArtifacts.add(VIEW_DIR + getJspName(screen.getName()));

			// Tela (Feature: BASE)
			if (screen.isBaseFeature()) {
				// Create elements refinement file
				for (String feature : viewDocument.getFeatures()) {
					if (!feature.isEmpty()) {
						Vector<Element> elements = viewDocument.getAllElementsByFeature(feature);

						String viewContent = getJSPRefinementTemplateOutput(elements);
						generateArtifactFile(feature, VIEW_DIR, getJspName(screen.getName()), viewContent);
					}
				}
			}
			else {
				/*
				 * Feature tela = features de alguns elementos que a compoe
				 */
				if (viewDocument.getFeatures().contains(screen.getFeature())) {
					Vector<Element> elements = viewDocument.getAllElementsByFeature(screen.getFeature());

					if (elements.size() > 0) {
						String viewContent = getJSPBaseTemplateOutput(screen);
						String layoutFile = generateFile(screen.getFeature(), VIEW_DIR, getJspLayoutName(screen.getName()), viewContent);

						viewContent = getJSPRefinementTemplateOutput(elements);
						String elementsRefinementFile = generateArtifactFile(screen.getFeature(), VIEW_DIR, getJspName(screen.getName()), viewContent);

						composeJSPBaseFiles(layoutFile, elementsRefinementFile);
					}
					else {
						String viewContent = getJSPBaseTemplateOutput(screen);
						generateArtifactFile(screen.getFeature(), VIEW_DIR, getJspName(screen.getName()), viewContent);
					}

					for (String feature : viewDocument.getFeatures()) {
						if (!feature.equals(screen.getFeature()) && !feature.isEmpty()) {
							elements = viewDocument.getAllElementsByFeature(feature);

							String viewContent = getJSPRefinementTemplateOutput(elements);
							generateArtifactFile(feature, VIEW_DIR, getJspName(screen.getName()), viewContent);
						}
					}
				}
				/*
				 * Feature tela != feature todos elementos que a compoe
				 */
				else {
					Vector<Element> elements = new Vector<Element>();

					String viewContent = getJSPBaseTemplateOutput(screen, elements, LAYOUT_DIR);
					generateArtifactFile(screen.getFeature(), VIEW_DIR, getJspName(screen.getName()), viewContent);

					for (String feature : viewDocument.getFeatures()) {
						if (!feature.isEmpty()) {
							elements = viewDocument.getAllElementsByFeature(feature);

							viewContent = getJSPRefinementTemplateOutput(elements);
							generateArtifactFile(feature, VIEW_DIR, getJspName(screen.getName()), viewContent);
						}
					}
				}
			}
		}
	}

	private void composeJSPBaseFiles(Screen screen) {

	}

	@Override
	public void generateControllerBaseFiles() {

		List<String> handlerActions = new ArrayList<String>();
		List<String> handlerGuards = new ArrayList<String>();
		List<Screen> baseScreens = new ArrayList<Screen>();

		generateControllerActionBase();
		generateGoToActionsBase(baseScreens);
		generateHandlersActionsBase(handlerActions, handlerGuards);
		generateControllerServletBase(handlerActions, handlerGuards, baseScreens);

	}

	private void generateControllerServletBase(List<String> handlerActions, List<String> handlerGuards, List<Screen> baseScreens) {
		/*
		 * Controller Servlet
		 */
		String controllerServletContent = getControllerServletBaseTemplateOutput(baseScreens, handlerGuards, handlerActions);
		generateArtifactFile("Base", CONTROLLER_DIR, getJakName("ControllerServlet"), controllerServletContent);
	}

	private void generateHandlersActionsBase(List<String> handlerActions, List<String> handlerGuards) {
		/*
		 * Handlers (actions, guards) Actions
		 */
		for (core.mvc.controller.Screen currentScreen : this.getControllerEntities().getScreens()) {

			ControllerRefinementDocument controllerRefinementDocument = new ControllerRefinementDocument(currentScreen);
			for (String feature : controllerRefinementDocument.getFeatures()) {
				if (feature.isEmpty()) {
					Vector<Handler> featureHandlers = controllerRefinementDocument.getHandlersByFeature(feature);

					String[] values = new String[2];
					String guard;
					String action;

					for (Handler handler : featureHandlers) {
						String handlerName = handler.getName();
						values = splitScreenHandler(handlerName);

						guard = values[0];
						action = values[1];

						/*
						 * Action file
						 */
						if (!action.isEmpty()) {
							String actionName = generateHandlerElementName(action);
							String targetName = generateHandlerTargetName(handler);

							String handlerContent = getHandlerActionBaseTemplateOutput(actionName, targetName);
							generateArtifactFile("Base", CONTROLLER_ACTION_DIR, getJakActionName(actionName), handlerContent);

							handlerActions.add(actionName);
						}
						/*
						 * Guard file
						 */
						if (!guard.isEmpty()) {
							String guardName = generateHandlerElementName(guard);

							String handlerContent = getHandlerGuardBaseTemplateOutput(guardName);
							generateArtifactFile("Base", CONTROLLER_ACTION_DIR, getJakActionName(guardName), handlerContent);

							handlerGuards.add(guardName);
						}
					}
				}
			}
		}
	}

	private void generateGoToActionsBase(List<Screen> baseScreens) {
		/*
		 * GoTo Actions
		 */
		for (core.mvc.controller.Screen screen : getControllerEntities().getScreens()) {
			if (screen.getFeature().isEmpty()) {
				baseScreens.add(screen);

				String goToActionContent = getGoToActionsBaseTemplateOutput(screen);
				generateArtifactFile("Base", CONTROLLER_ACTION_DIR, getJakGoToActionName(screen.getName()), goToActionContent);
			}
		}
	}

	private void generateControllerActionBase() {
		/*
		 * Controller Action (Interface)
		 */
		ControllerAction controllerAction = new ControllerAction();
		String controllerActionContent = controllerAction.generate(null);
		generateArtifactFile("Base", CONTROLLER_ACTION_DIR, getJakName("ControllerAction"), controllerActionContent);
	}

	@Override
	public void generateControllerRefinementFiles() {

		Map<String, List<String>> handlerActionsMap = new HashMap<String, List<String>>();
		Map<String, List<String>> handlerGuardsMap = new HashMap<String, List<String>>();
		Map<String, String> goToScreenActionsMap = new HashMap<String, String>();

		List<String> features = new ArrayList<String>();
		List<String> handlerActions = new ArrayList<String>();
		List<String> handlerGuards = new ArrayList<String>();
		String goToScreenAction = "";

		String screenFeature;
		/*
		 * GoTo Actions
		 */
		for (core.mvc.controller.Screen screen : getControllerEntities().getScreens()) {
			screenFeature = screen.getFeature();

			if (!screenFeature.isEmpty()) {
				if (!features.contains(screenFeature)) {
					features.add(screenFeature);
				}

				goToScreenAction = goToScreenActionsMap.get(screenFeature);
				if (goToScreenAction == null) {
					goToScreenAction = "";
				}
				goToScreenAction = screen.getName();
				goToScreenActionsMap.put(screenFeature, goToScreenAction);

				String content = getGoToActionsBaseTemplateOutput(screen, screenFeature);
				generateArtifactFile(screenFeature, CONTROLLER_ACTION_DIR, getJakGoToActionName(screen.getName()),
						content);
			}
		}

		/*
		 * Handlers (actions, guards) Actions
		 */
		for (core.mvc.controller.Screen currentScreen : this.getControllerEntities().getScreens()) {

			ControllerRefinementDocument controllerRefinementDocument = new ControllerRefinementDocument(currentScreen);
			for (String feature : controllerRefinementDocument.getFeatures()) {
				if (!feature.isEmpty()) {
					Vector<Handler> screenFeatureHandlers = controllerRefinementDocument.getHandlersByFeature(feature);

					String[] values = new String[2];
					String guard;
					String action;

					for (Handler handler : screenFeatureHandlers) {
						String handlerName = handler.getName();
						values = splitScreenHandler(handlerName);

						guard = values[0];
						action = values[1];

						/*
						 * Action file
						 */
						if (!action.isEmpty()) {
							String actionName = generateHandlerElementName(action);

							if (!features.contains(feature)) {
								features.add(feature);
							}
							handlerActions = handlerActionsMap.get(feature);
							if (handlerActions == null) {
								handlerActions = new ArrayList<String>();
							}
							handlerActions.add(actionName);
							handlerActionsMap.put(feature, handlerActions);

							String handlerActionContent = getHandlerActionBaseTemplateOutput(actionName);
							generateArtifactFile(feature, CONTROLLER_ACTION_DIR, getJakActionName(actionName),
									handlerActionContent);

						}
						/*
						 * Guard file
						 */
						if (!guard.isEmpty()) {
							String guardName = generateHandlerElementName(guard);

							if (!features.contains(feature)) {
								features.add(feature);
							}
							handlerGuards = handlerGuardsMap.get(feature);
							if (handlerGuards == null) {
								handlerGuards = new ArrayList<String>();
							}
							handlerGuards.add(guardName);
							handlerGuardsMap.put(feature, handlerGuards);

							String handlerActionContent = getHandlerGuardBaseTemplateOutput(guardName);
							generateArtifactFile(feature, CONTROLLER_ACTION_DIR, getJakActionName(guardName), handlerActionContent);
						}
					}
				}
			}
		}

		/*
		 * Controller Servlet Refinement
		 */
		for (String feature : features) {
			// Checking handlers
			handlerGuards = handlerGuardsMap.get(feature);
			if (handlerGuards == null) {
				handlerGuards = new ArrayList<String>();
			}
			handlerActions = handlerActionsMap.get(feature);
			if (handlerActions == null) {
				handlerActions = new ArrayList<String>();
			}
			goToScreenAction = goToScreenActionsMap.get(feature);
			if (goToScreenAction == null) {
				goToScreenAction = "";
			}

			String controllerServletContent = getControllerServletRefinementTemplateOutput(goToScreenAction, handlerGuards, handlerActions, feature);
			generateArtifactFile(feature, CONTROLLER_DIR, getJakName("ControllerServlet"), controllerServletContent);
		}
	}

	private String getPersistenceXMLBaseTemplateOutput(Object... parameters) {
		PersistenceXML persistence = new PersistenceXML();
		return persistence.generate(parameters);
	}

	private String getWebXMLBaseTemplateOutput(Object... parameters) {
		WebXMLBase webConfig = new WebXMLBase();
		return webConfig.generate(parameters);
	}

	private String getPersistenceXMLRefinementTemplateOutput(Object... parameters) {
		PersistenceXMLRefinement persistence = new PersistenceXMLRefinement();
		return persistence.generate(parameters);
	}

	private String getWebXMLRefinemenTemplateOutput(Object... parameters) {
		WebXMLRefinement webConfig = new WebXMLRefinement();
		return webConfig.generate(parameters);
	}

	public void generateConfigurationBaseFiles() {
		generatePersistenceXML();
		generateWebXML();
	}

	private void generateWebXML() {
		/*
		 * Generates web.xml
		 */
		String feature;
		Vector<Facade> facades;
		Vector<Model> models;

		feature = "";
		facades = new Vector<Facade>();
		models = new Vector<Model>();

		for (Facade fc : getModelEntities().getFacades()) {
			feature = fc.getFeature();
			if (feature.isEmpty()) {
				facades.add(fc);
			}
		}
		for (Model m : getModelEntities().getModels()) {
			feature = m.getFeature();
			if (feature.isEmpty()) {
				models.add(m);
			}
		}
		String webConfigContent = getWebXMLBaseTemplateOutput(getProductName(), models, facades);
		generateArtifactFile("Base", VIEW_WEBINF_DIR, "web.xml", webConfigContent);
	}

	private void generatePersistenceXML() {
		/*
		 * Generates persistence.xml
		 */
		String feature;
		Vector<Model> models = new Vector<Model>();

		for (core.mvc.model.Model m : this.getModelEntities().getModels()) {
			feature = m.getFeature();
			if (feature.isEmpty()) {
				models.add(m);
			}
		}

		String persistenceXMLContent = getPersistenceXMLBaseTemplateOutput(getProductName(), models);
		generateArtifactFile("Base", RESOURCES_METAINF_DIR, "persistence.xml", persistenceXMLContent);
	}

	public void generateConfigurationRefinementFiles() {
		// Extract Models and Facades by Feature

		List<String> features = new ArrayList<String>(getModelEntities().getModelsFeatures());
		features.addAll(getModelEntities().getFacadesFeatures());

		for (String feature : features) {

			Vector<Model> featureLabeledModels = getModelEntities().getModelsByFeature(feature);
			Vector<Facade> featureLabeledFacades = getModelEntities().getFacadesByFeature(feature);

			// Generates persistence.xml
			String persistenceXMLContent = getPersistenceXMLRefinementTemplateOutput(featureLabeledModels);
			generateArtifactFile(feature, RESOURCES_METAINF_DIR, "persistence.xml", persistenceXMLContent);

			// Generates web.xml
			String webXMLContent = getWebXMLRefinemenTemplateOutput(featureLabeledModels, featureLabeledFacades);
			generateArtifactFile(feature, VIEW_WEBINF_DIR, "web.xml", webXMLContent);

		}
	}

	// Split Screen Handlers (Event, Guard and Action)
	private String[] splitScreenHandler(String handlerName) {
		String guard = "";
		String action = "";

		// Exists action
		if (handlerName.contains("/")) {
			String eventAndGuard = handlerName.substring(0, handlerName.indexOf("/"));

			// Exists guard
			if (eventAndGuard.contains("[")) {
				guard = eventAndGuard.substring(eventAndGuard.indexOf("[") + 1, eventAndGuard.length() - 1);
			}
			action = handlerName.substring(handlerName.indexOf("/") + 1, handlerName.length());
		}
		else {
			// Exists guard
			if (handlerName.contains("[")) {
				guard = handlerName.substring(handlerName.indexOf("[") + 1, handlerName.length() - 1);
			}
		}

		return new String[] { guard, action };
	}

}
