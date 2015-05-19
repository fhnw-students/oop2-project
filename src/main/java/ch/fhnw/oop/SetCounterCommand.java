//package ch.fhnw.oop;
//
///**
// * @author Dieter Holz
// */
//public class SetCounterCommand implements ICommand {
//	private final AcademyModel model;
//	private final int newValue;
//	private final int oldValue;
//
//	public SetCounterCommand(AcademyModel model, int newValue) {
//		this.model = model;
//
//		this.oldValue = model.getCounter();
//		this.newValue = newValue;
//	}
//
//	@Override
//	public void execute() {
//		model.setCounter(newValue);
//		model.setInputValid(true);
//	}
//
//	@Override
//	public void undo() {
//		model.setCounter(oldValue);
//		model.setInputValid(true);
//	}
//}
