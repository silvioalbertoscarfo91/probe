package com.kata.probe.movement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProbeTest {
    private Probe probe;

    @BeforeEach
    void setUp() {
        probe = new Probe(0, 0, Direction.N);
    }

    @Nested
    class MoveTest {
        @Test
        void shouldTurnLeftFromNorthToWest() {
            probe.move(Command.LEFT, null); // Grid not needed yet, use null
            assertEquals(Direction.W, probe.getDirection());
        }

        @Test
        void shouldTurnRightFromNorthToEast() {
            probe.move(Command.RIGHT, null); // Grid not needed yet, use null
            assertEquals(Direction.E, probe.getDirection());
        }
    }

    @Test
    void getPosition() {
    }

    @Test
    void getDirection() {
    }

    @Test
    void getVisited() {
    }
}