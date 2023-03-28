package src.model.Observer_Pattern;

import java.util.Map;

public interface Observer {
    void update(Subject.Event event, Map<String,Integer> team_scores);
}