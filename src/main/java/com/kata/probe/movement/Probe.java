package com.kata.probe.movement;

import java.util.ArrayList;
import java.util.List;

public class Probe {
    private final int x;
    private final int y;
    private final Direction direction;
    private final List<Position> visited;

    public Probe(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.visited = new ArrayList<>();
        visited.add(new Position(x, y));
    }

    public Position getPosition() {
        return new Position(x, y);
    }

    public Direction getDirection() {
        return direction;
    }

    public List<Position> getVisited() {
        return new ArrayList<>(visited); // Return a copy
    }
}