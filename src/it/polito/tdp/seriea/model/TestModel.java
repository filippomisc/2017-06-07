package it.polito.tdp.seriea.model;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Model model = new Model();
		
		model.createGraph(2007);
		System.out.println(model.generaClassifica().toString());
	}

}
