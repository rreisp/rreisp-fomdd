package core.model;

import java.util.HashMap;
import java.util.Vector;

import core.mvc.model.Facade;
import core.mvc.model.Model;

public class MVCModel {
	private Vector<Model> models;
	private Vector<Facade> facades;
	private HashMap<String, Vector<Model>> featureModelsMap;
	private HashMap<String, Vector<Facade>> featureFacadesMap;

	public MVCModel() {
		models = new Vector<Model>();
		facades = new Vector<Facade>();
		featureModelsMap = new HashMap<String, Vector<Model>>();
		featureFacadesMap = new HashMap<String, Vector<Facade>>();
		createFeatureModelsMap();
		createFeatureFacadesMap();
	}

	private void createFeatureModelsMap() {
		Vector<Model> featureLabeledModels;

		for (core.mvc.model.Model model : getModels()) {
			featureLabeledModels = featureModelsMap.get(model.getFeature());

			if (featureLabeledModels == null) {
				featureLabeledModels = new Vector<Model>();
			}

			featureLabeledModels.add(model);
			featureModelsMap.put(model.getFeature(), featureLabeledModels);
		}
	}

	private void createFeatureFacadesMap() {
		Vector<Facade> featureLabeledFacades;

		for (Facade facade : getFacades()) {
			featureLabeledFacades = featureFacadesMap.get(facade.getFeature());

			if (featureLabeledFacades == null) {
				featureLabeledFacades = new Vector<Facade>();
			}

			featureLabeledFacades.add(facade);
			featureFacadesMap.put(facade.getFeature(), featureLabeledFacades);
		}
	}

	public Vector<String> getModelsFeatures() {
		return new Vector<String>(featureModelsMap.keySet());
	}

	public Vector<String> getFacadesFeatures() {
		return new Vector<String>(featureFacadesMap.keySet());
	}

	public Vector<Model> getModelsByFeature(String feature) {
		return featureModelsMap.containsKey(feature) ? featureModelsMap.get(feature) : new Vector<Model>();
	}

	public Vector<Facade> getFacadesByFeature(String feature) {
		return featureFacadesMap.containsKey(feature) ? featureFacadesMap.get(feature) : new Vector<Facade>();
	}

	public Vector<Model> getModels() {
		return models;
	}

	public void setModels(Vector<Model> models) {
		this.models = models;
	}

	public void addModel(Model m) {
		this.models.add(m);
	}

	public Vector<Facade> getFacades() {
		return facades;
	}

	public void setFacades(Vector<Facade> facades) {
		this.facades = facades;
	}

	public void addFacade(Facade f) {
		this.facades.add(f);
	}

	public void print() {
		System.out.println(":: Abstract MVC Model ::");

		for (Model m : models) {
			m.print();
		}
		for (Facade f : facades) {
			f.print();
		}
	}
}
