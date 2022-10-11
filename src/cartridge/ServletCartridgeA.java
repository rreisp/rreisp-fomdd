package cartridge;

import java.io.File;

import cartridge.A.jee.jsf.ServletArtifactModel;
import core.artifact.ArtifactModel;
import core.model.FeatureModel;
import core.model.input.XML2ObjectMVCControllerTranslator;
import core.model.input.XML2ObjectMVCModelTranslator;
import core.model.input.XML2ObjectMVCViewTranslator;
import core.scripts.ScriptUtils;

public class ServletCartridgeA {
	ScriptUtils scriptUtils;
	ArtifactModel servletArtifactModel;
	FeatureModel featureModel;

	XML2ObjectMVCModelTranslator mvcModelObject;
	File projectModel;

	XML2ObjectMVCViewTranslator mvcViewObject;
	File projectView;

	XML2ObjectMVCControllerTranslator mvcControllerObject;
	File projectController;

	private void createFeatureModel() {
		featureModel = new FeatureModel();
		featureModel.addFeature("LayoutDefinitions");
		featureModel.addFeature("BasicBackEndDefinitions");
		featureModel.addFeature("ProductManagement");
		featureModel.addFeature("BasicFrontEndDefinitions");
		featureModel.addFeature("Checkout");
		featureModel.addFeature("Payment");
		featureModel.addFeature("StaticContent");
		featureModel.addFeature("BasicPaymentDefinitions");
		featureModel.addFeature("Paypal");
		featureModel.addFeature("BankSlip");
		featureModel.addFeature("CategoryManagement");
		featureModel.addFeature("DisplayWhatIsNew");
		featureModel.addFeature("DisplayByCategory");
	}

	private void translateXMLToObject() {
		mvcModelObject = new XML2ObjectMVCModelTranslator();
		mvcModelObject.translate(projectModel);
		mvcModelObject.sortByFeatures(featureModel);
		// mvcModelObject.print();

		mvcViewObject = new XML2ObjectMVCViewTranslator();
		mvcViewObject.translate(projectView);
		mvcViewObject.sortByFeatures(featureModel);
		// mvcViewObject.print();

		mvcControllerObject = new XML2ObjectMVCControllerTranslator();
		mvcControllerObject.translate(projectController);
		mvcControllerObject.sortByFeatures(featureModel);
		// mvcControllerObject.print();
	}

	public ServletCartridgeA() {
		
	}

	public void run() {

		/*
		 * Parameters
		 */
		scriptUtils = ScriptUtils.getInstance();
		
		final String DESIGN_MODELS_DIR = scriptUtils.getProperty("model.design.dir.mvc");
		final String TARGET_DIR = scriptUtils.getProperty("target.dir.dase");
		final String featureTarget = scriptUtils.getProperty("target.domain.dir.base");
		final String productName = scriptUtils.getProperty("product.name");
		final String MODEL_DESIGN_FILE = "WebStoresMVCModel.xml";
		final String CONTROLLER_DESIGN_FILE = "WebStoresMVCController.xml";
		final String VIEW_DESIGN_FILE = "WebStoresMVCView.xml";

		/*
		 * Model files
		 */
		projectModel = new File(DESIGN_MODELS_DIR + "/" + MODEL_DESIGN_FILE);
		projectView = new File(DESIGN_MODELS_DIR + "/" + VIEW_DESIGN_FILE);
		projectController = new File(DESIGN_MODELS_DIR + "/" + CONTROLLER_DESIGN_FILE);

		createFeatureModel();
		translateXMLToObject();

		/*
		 * Artifact Model
		 */
		servletArtifactModel = new ServletArtifactModel(mvcModelObject.getModelEntities(), mvcViewObject.getViews(), mvcControllerObject
				.getControllers());
		servletArtifactModel.setFeatureModel(featureModel.getFeatures());
		servletArtifactModel.setFeatureTarget(featureTarget);
		servletArtifactModel.setProductName(productName);
		servletArtifactModel.setFeatureFlag(true);

		// creates base artifacts
		servletArtifactModel.generateBaseFiles();

		// creates refinement artifacts
		servletArtifactModel.generateRefinementFiles();

		System.out.println("Generated files...");

		StringBuilder artifactNames = new StringBuilder();
		for (String s : servletArtifactModel.getGeneratedFiles()) {
			System.out.println(s);
			artifactNames.append(s + "\n");
		}

		servletArtifactModel.generateOutputFile(TARGET_DIR, scriptUtils.getProperty("target.outputFile"), artifactNames.toString());
	}
}
