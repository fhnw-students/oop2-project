package ch.fhnw.oop;

import java.util.HashSet;
import java.util.Set;

public class AcademyModel implements Observable {

    private final Set<Observer> observers = new HashSet<>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        observers.forEach(observer -> observer.update(this));
    }

}
