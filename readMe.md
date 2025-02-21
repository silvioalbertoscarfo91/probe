# Submersible Probe API

## Overview
This project implements a REST API to control a submersible probe on a grid, developed using Spring Boot with a Test-Driven Development (TDD) approach. Below are the key assumptions made during implementation.

## Assumptions Made in Solving the Exercise

1. **Grid Dimensions**:
    - Fixed 10x10 grid, coordinates from (0,0) to (9,9).
    - Reason: Manageable size for testing movement and constraints.

2. **Initial Probe Position**:
    - Starts at (0,0) facing North (N).
    - Reason: Simple origin point; adjusted to (5,5) in some tests to avoid edges.

3. **Number and Position of Obstacles**:
    - 3 obstacles at (2,2), (3,5), (7,8).
    - Reason: Spread across grid for effective testing, hardcoded for simplicity.

4. **Probe Movement Rules**:
    - Moves one unit per FORWARD/BACKWARD, rotates with LEFT/RIGHT, stays within grid and avoids obstacles.
    - Reason: Predictable, grid-respecting behavior.

5. **Visited Coordinates Tracking**:
    - Records successful moves in a list, including duplicates.
    - Reason: Preserves movement history per requirements.

6. **Command Input**:
    - JSON list of `Command` enums; invalid inputs return 400 Bad Request.
    - Reason: Type safety and default error handling suffice.

7. **State Management**:
    - Singleton probe instance with persistent state.
    - Reason: Simplifies single-user scenario.

8. **Error Handling**:
    - No explicit obstacle/boundary feedback; inferred from unchanged position.
    - Reason: Keeps API minimal within timebox.

9. **Testing Assumptions**:
    - 10x10 grid, no obstacles unless specified, centered positions for movement tests.
    - Reason: Focus on core behavior with comprehensive coverage.

10. **API Design**:
    - `POST /probe/movement` returns position and direction; `GET /probe/visited` returns history.
    - Reason: Meets requirements with lean design.

## Possible improvements

1. **Possibility to add second parameter to Command**:
   - In order to be able to repeat a single command (FORWARD) for x times, a second parameter (int repetition) could be added to the Command object.
   - In this way the user could send 10 FORWARD Command using a list of just 1 element instead of 10

2. **API to assign Grid dimension and Obstacles and set the initial Position of the Probe**:
   - In order to improve the reusability for different scenario to have the possibility to give a custom shape to the GRID 
   - It would be useful to decide and assign the position of the PROBE