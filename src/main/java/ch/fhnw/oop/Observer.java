package ch.fhnw.oop;

/**
 * @author Dieter Holz
 */

@FunctionalInterface
public interface Observer {
	void update(Observable model);
}
