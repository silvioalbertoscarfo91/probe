package com.kata.probe.movement;

import java.util.ArrayList;
import java.util.List;

public class Probe {
    private int x;
    private int y;
    private Direction direction;
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

    public void move(Command command, Grid grid) { // Grid will be used to move forward and backward
        switch (command) {
            case LEFT -> turnLeft();
            case RIGHT -> turnRight();
        }
    }

    private void turnLeft() {
        direction = switch (direction) {
            case N -> Direction.W;
            case E -> Direction.N;
            case S -> Direction.E;
            case W -> Direction.S;
        };
    }

    private void turnRight() {
        direction = switch (direction) {
            case N -> Direction.E;
            case E -> Direction.S;
            case S -> Direction.W;
            case W -> Direction.N;
        };
    }
}