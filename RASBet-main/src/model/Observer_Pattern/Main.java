package src.model.Observer_Pattern;

import java.util.ArrayList;
import java.util.List;

import src.model.Better;

public class Main {
    public static void main(String[] args) {
        List<Better> better_list = new ArrayList<>();
        Better better1 = new Better();
        better_list.add(better1);
        Better better2 = new Better();
        better_list.add(better2);
        Better better3 = new Better();
        better_list.add(better3);
        Better better4 = new Better();
        better_list.add(better4);
        Better better5 = new Better();
        better_list.add(better5);
        Better better6 = new Better();
        better_list.add(better6);
        Better better7 = new Better();
        better_list.add(better7);

        Event match = new Event("A", "B", better_list);

        match.start_of_match();
        match.half_time_match();
        match.end_of_match();
        match.goal_scored();
    }
}
