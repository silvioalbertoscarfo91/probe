package com.kata.probe.movement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Set;

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
        @ParameterizedTest(name = "[{index}] startDirection: {0}, finalDirection: {1}")
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
            probe.move(Command.RIGHT, grid);

            //then
            assertEquals(finalDirection, probe.getDirection());
        }

        @ParameterizedTest(name = "[{index}] startDirection: {0}, finalDirection: {1}")
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
            probe.move(Command.LEFT, grid);

            //then
            assertEquals(finalDirection, probe.getDirection());
        }

        @ParameterizedTest(name = "[{index}] direction: {0}, expectedX: {1}, expectedY: {2}")
        @CsvSource(
                useHeadersInDisplayName = true,
                value = {
                        "direction, expectedX, expectedY",
                        "N, 5, 6",
                        "E, 6, 5",
                        "S, 5, 4",
                        "W, 4, 5"
                }
        )
        void shouldMoveForwardInCorrectDirection(Direction direction, int expectedX, int expectedY) {
            // given
            Probe probe = new Probe(5, 5, direction); //start from safe position

            // when
            probe.move(Command.FORWARD, grid);

            // then
            Position position = probe.getPosition();
            assertEquals(expectedX, position.x());
            assertEquals(expectedY, position.y());
            assertEquals(2, probe.getVisited().size());
            assertEquals(new Position(expectedX, expectedY), probe.getVisited().get(1));
        }

        @ParameterizedTest(name = "[{index}] direction: {0}, expectedX: {1}, expectedY: {2}")
        @CsvSource(
                useHeadersInDisplayName = true,
                value = {
                        "direction, expectedX, expectedY",
                        "N, 5, 4",
                        "E, 4, 5",
                        "S, 5, 6",
                        "W, 6, 5"
                }
        )
        void shouldMoveBackwardInOppositeDirection(Direction direction, int expectedX, int expectedY) {
            // given
            Probe probe = new Probe(5, 5, direction); //safe position to start

            // when
            probe.move(Command.BACKWARD, grid);

            // then
            Position position = probe.getPosition();
            assertEquals(expectedX, position.x());
            assertEquals(expectedY, position.y());
            assertEquals(2, probe.getVisited().size()); //initial + movement
            assertEquals(new Position(expectedX, expectedY), probe.getVisited().get(1));
        }

        @ParameterizedTest(name = "[{index}] direction: {0}, startX: {1}, startY: {2}")
        @CsvSource(
                useHeadersInDisplayName = true,
                value = {
                        "direction, startX, startY",
                        "N, 0, 9",
                        "E, 9, 0",
                        "S, 0, 0",
                        "W, 0, 0"
                }
        )

        void shouldNotMoveForwardBeyondBoundary(Direction direction, int startX, int startY) {
            // given
            Probe probe = new Probe(startX, startY, direction);

            // when
            probe.move(Command.FORWARD, grid);

            // then
            Position position = probe.getPosition();
            assertEquals(startX, position.x());
            assertEquals(startY, position.y());
            assertEquals(1, probe.getVisited().size()); //no position was visited, just actual position
        }

        @ParameterizedTest(name = "[{index}] direction: {0}, obstacleX: {1}, obstacleY: {2}")
        @CsvSource(
                useHeadersInDisplayName = true,
                value = {
                        "direction, obstacleX, obstacleY",
                        "N, 5, 6",
                        "E, 6, 5",
                        "S, 5, 4",
                        "W, 4, 5"
                }
        )
        void shouldNotMoveForwardIntoObstacle(Direction direction, int obstacleX, int obstacleY) {
            // given
            Grid grid = new Grid(10, 10, Set.of(new Position(obstacleX, obstacleY)));
            Probe probe = new Probe(5, 5, direction); //start from safe position

            // when
            probe.move(Command.FORWARD, grid);

            // then
            Position position = probe.getPosition();
            assertEquals(5, position.x());
            assertEquals(5, position.y());
            assertEquals(1, probe.getVisited().size()); //just actual position
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