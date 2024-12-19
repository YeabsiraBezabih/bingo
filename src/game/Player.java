package game;

import java.util.HashSet;
import java.util.Set;

public class Player {
    private String name;
    private Set<Integer> markedNumbers;

    public Player(String name) {
        this.name = name;
        this.markedNumbers = new HashSet<>();
    }

    public void addMarkedNumber(int number) {
        markedNumbers.add(number);
    }

    public boolean hasBingo() {
        return markedNumbers.size() >= 5; // Simplified win condition
    }

    public String getName() {
        return name;
    }
}