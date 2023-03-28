package src.model.Observer_Pattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.model.Better;

public class Event implements Subject {
    private List<Observer> observersList; // list of observers for this match
    private Map<String, Integer> team_scores; // identify the teams and scorings
    
    // empty constructor
    public Event() {
        this.observersList = new ArrayList<>();
        this.team_scores = new HashMap<>();
    }

    public Event(String home, String outsider) {
        this.observersList = new ArrayList<>();
        this.team_scores = new HashMap<>();
        team_scores.put(home, 0);
        team_scores.put(outsider, 0);
    }

    public Event(String home, String outsider, List<Better> observers) {
        this.team_scores = new HashMap<>();
        team_scores.put(home, 0);
        team_scores.put(outsider, 0);
        this.observersList = new ArrayList<>();
        for (Better better : observers) {
            this.observersList.add(better);
        }
    }

    // subject methods
    @Override
    public void notifyAllObservers(Event event) {
        for (Observer obs : observersList) {
            obs.update(event, team_scores);
        }
    }

    @Override
    public void subscribeObserver(Observer Obj) {
        this.observersList.add(Obj);
    }

    @Override
    public void unsubscribeObserver(Observer Obj) {
        this.observersList.remove(Obj);
    }
    
    // events methods
    public void start_of_match() {
        Event start = Event.START;
        notifyAllObservers(start);
    }
    
    public void end_of_match() {
        Event end = Event.END;
        notifyAllObservers(end);
    }
    
    public void half_time_match() {
        Event half = Event.HALF;
        notifyAllObservers(half);
    }
    
    public void goal_scored() {
        Event goal = Event.GOAL;
        notifyAllObservers(goal);
    }

}
