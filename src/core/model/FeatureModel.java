package core.model;

import java.util.Vector;

public class FeatureModel {
	protected Vector<String> features;
	
	public FeatureModel(){
		features = new Vector<String>();
	}
	
	public Vector<String> getFeatures() {
		return features;
	}

	public void setFeatures(Vector<String> features) {
		this.features = features;
	}
	
	public void addFeature(String feature){
		this.features.add(feature);
	}
	
	public void print(){
		System.out.println("Feature Model");
		for(String s:features){
			System.out.println("\t"+s);
		}
	}
}
