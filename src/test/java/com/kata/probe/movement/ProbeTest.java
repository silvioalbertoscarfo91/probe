package com.kata.probe.movement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class ProbeTest {
    private Probe probe;
    private Grid grid;

    @BeforeEach
    void setUp() {
        probe = new Probe(0, 0, Direction.N);
        grid = new Grid(10, 10, null); //obstacles not yet there
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

        @Test
        void givenPositionFarFromBoundariesAndObstacles_shouldMoveForward() { //test will fail
            //given
            probe = new Probe(5, 5, Direction.N);

            // when
            probe.move(Command.FORWARD, grid);

            //then
            assertEquals(new Position(5, 6), probe.getPosition());
        }

        @Test
        void givenPositionFarFromBoundariesAndObstacles_shouldMoveBackward() { //test will fail
            //given
            probe = new Probe(5, 5, Direction.N);

            // when
            probe.move(Command.BACKWARD, grid);

            //then
            assertEquals(new Position(5, 4), probe.getPosition());
        }

        @Test
        void shouldNotMoveOutOfBoundaries() {
            //given
            probe = new Probe(0, 9, Direction.N); //next to boundaries

            // when
            probe.move(Command.FORWARD, grid);

            //then
            assertEquals(new Position(0, 9), probe.getPosition()); //recognize boundaries and don't move
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