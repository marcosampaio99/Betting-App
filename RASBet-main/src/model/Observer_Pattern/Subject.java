package src.model.Observer_Pattern;

public interface Subject {
    enum Event {
        GOAL,
        START,
        END,
        HALF
    }
    void notifyAllObservers(Event event);

    void subscribeObserver(Observer Obj);

    void unsubscribeObserver(Observer Obj);
}
