package com.kata.probe.movement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class ProbeTest {
    private Probe probe;

    @BeforeEach
    void setUp() {
        probe = new Probe(0, 0, Direction.N);
    }

    @Nested
    class MoveTest {
        @ParameterizedTest(name = "[{index}] argument is: {argumentsWithNames}")
        @CsvSource(
                useHeadersInDisplayName = true,
                value = {
                        "N, E",
                        "E, S",
                        "S, W",
                        "W, N",
                })
        void shouldTurnToTheRightDirection(Direction startDirection, Direction finalDirection) {
            //given
            probe = new Probe(0, 0, startDirection);

            //when
            probe.move(Command.RIGHT, null); // Grid not needed yet, use null

            //then
            assertEquals(finalDirection, probe.getDirection());
        }

        @ParameterizedTest(name = "[{index}] argument is: {argumentsWithNames}")
        @CsvSource(
                useHeadersInDisplayName = true,
                value = {
                        "N, W",
                        "W, S",
                        "S, E",
                        "E, N",
                })
        void shouldTurnToTheLeftDirection(Direction startDirection, Direction finalDirection) {
            //given
            probe = new Probe(0, 0, startDirection);

            //when
            probe.move(Command.LEFT, null); // Grid not needed yet, use null

            //then
            assertEquals(finalDirection, probe.getDirection());
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