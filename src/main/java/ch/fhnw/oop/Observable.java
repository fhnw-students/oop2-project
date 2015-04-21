package ch.fhnw.oop;

/**
 * @author Dieter Holz
 */
public interface Observable {
	void addObserver(Observer observer);

	void removeObserver(Observer observer);
}
