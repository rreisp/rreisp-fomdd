package core.refinement;

import java.util.HashMap;
import java.util.Vector;

import core.mvc.model.*;

public class ModelRefinementDocument {
	private String modelName;
	private Vector<String> features;
	private Vector<String> attributeFeatures;
	private Vector<String> operationFeatures;
	private HashMap<String, Vector<Attribute>> featureAttributesMap;
	private HashMap<String, Vector<Operation>> featureOperationsMap;

	public ModelRefinementDocument(Model m) {
		this.featureAttributesMap = new HashMap<String, Vector<Attribute>>();
		this.featureOperationsMap = new HashMap<String, Vector<Operation>>();
		this.features = new Vector<String>();
		this.attributeFeatures = new Vector<String>();
		this.operationFeatures = new Vector<String>();
		this.modelName = m.getName();

		features.add(m.getFeature());
		
		for (Attribute a : m.getAttributes()) {
			if (!this.features.contains(a.getFeature())) {
				this.features.add(a.getFeature());
			}
			if (!this.attributeFeatures.contains(a.getFeature())) {
				this.attributeFeatures.add(a.getFeature());
			}

			Vector<Attribute> attrs = featureAttributesMap.get(a.getFeature());
			if (attrs == null) {
				attrs = new Vector<Attribute>();
			}
			attrs.add(a);
			featureAttributesMap.put(a.getFeature(), attrs);
		}

		for (Operation o : m.getOperations()) {
			if (!this.features.contains(o.getFeature())) {
				this.features.add(o.getFeature());
			}

			if (!this.operationFeatures.contains(o.getFeature())) {
				this.operationFeatures.add(o.getFeature());
			}

			Vector<Operation> ops = featureOperationsMap.get(o.getFeature());
			if (ops == null) {
				ops = new Vector<Operation>();
			}
			ops.add(o);
			featureOperationsMap.put(o.getFeature(), ops);
		}
	}

	public ModelRefinementDocument(Facade fc) {
		this.featureAttributesMap = new HashMap<String, Vector<Attribute>>();
		this.featureOperationsMap = new HashMap<String, Vector<Operation>>();
		this.features = new Vector<String>();
		this.attributeFeatures = new Vector<String>();
		this.operationFeatures = new Vector<String>();
		this.modelName = fc.getName();

		for (Attribute a : fc.getAttributes()) {
			if (!this.features.contains(a.getFeature())) {
				this.features.add(a.getFeature());
			}
			if (!this.attributeFeatures.contains(a.getFeature())) {
				this.attributeFeatures.add(a.getFeature());
			}

			Vector<Attribute> attrs = featureAttributesMap.get(a.getFeature());
			if (attrs == null) {
				attrs = new Vector<Attribute>();
			}
			attrs.add(a);
			featureAttributesMap.put(a.getFeature(), attrs);
		}

		for (Operation o : fc.getOperations()) {
			if (!this.features.contains(o.getFeature())) {
				this.features.add(o.getFeature());
			}

			if (!this.operationFeatures.contains(o.getFeature())) {
				this.operationFeatures.add(o.getFeature());
			}

			Vector<Operation> ops = featureOperationsMap.get(o.getFeature());
			if (ops == null) {
				ops = new Vector<Operation>();
			}
			ops.add(o);
			featureOperationsMap.put(o.getFeature(), ops);
		}
	}

	public void print() {
		System.out.println(this.getModelName());
		for (String feature : this.attributeFeatures) {
			System.out.println("\t" + feature);

			for (Attribute a : this.featureAttributesMap.get(feature)) {
				System.out.println("\t\t" + a.getName());
			}
		}

		for (String feature : this.operationFeatures) {
			System.out.println("\t" + feature);

			for (Operation o : this.featureOperationsMap.get(feature)) {
				System.out.println("\t\t" + o.getName());
			}
		}
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public Vector<String> getFeatures() {
		return features;
	}

	public void setFeatures(Vector<String> features) {
		this.features = features;
	}

	public Vector<String> getAttrFeatures() {
		return attributeFeatures;
	}

	public void setAttrFeatures(Vector<String> attrFeatures) {
		this.attributeFeatures = attrFeatures;
	}

	public Vector<String> getOpFeatures() {
		return operationFeatures;
	}

	public void setOpFeatures(Vector<String> opFeatures) {
		this.operationFeatures = opFeatures;
	}

	public HashMap<String, Vector<Attribute>> getAttrRefinements() {
		return featureAttributesMap;
	}

	public void setAttrRefinements(HashMap<String, Vector<Attribute>> attrRefinements) {
		this.featureAttributesMap = attrRefinements;
	}

	public HashMap<String, Vector<Operation>> getOpRefinements() {
		return featureOperationsMap;
	}

	public void setOpRefinements(HashMap<String, Vector<Operation>> opRefinements) {
		this.featureOperationsMap = opRefinements;
	}

	public Vector<Attribute> getAttributesByFeature(String feature) {
		Vector<Attribute> attsAux = this.getAttrRefinements().get(feature);
		if (attsAux == null) {
			attsAux = new Vector<Attribute>();
		}
		return attsAux;
	}

	public Vector<Operation> getOperationsByFeature(String feature) {
		Vector<Operation> opsAux = this.getOpRefinements().get(feature);
		if (opsAux == null) {
			opsAux = new Vector<Operation>();
		}
		return opsAux;
	}
}
