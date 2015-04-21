package ch.fhnw.oop;


public class AcademyController {
	private final AcademyModel model;
	private final AcademyView view;

	public AcademyController(AcademyModel model) {
		this.model = model;
		this.view = new AcademyView(model, this);
	}

	public void initializeView(){
		view.createAndShow();
	}


}
