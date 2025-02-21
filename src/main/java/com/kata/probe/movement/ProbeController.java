package com.kata.probe.movement;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/probe")
@Tag(name = "Probe Controller", description = "Controls the submersible probe")
public class ProbeController {
    private final Probe probe;
    private final Grid grid;

    @PostMapping("/movement")
    @Operation(summary = "Execute a list of movement commands", description = "Moves the probe and returns its current position and direction")
    public ProbeState executeCommands(@RequestBody @Validated List<Command> commands) {
        //verbesserung: size of the list can be checked
        for (Command command : commands) {
            probe.move(command, grid);
        }
        return new ProbeState(probe.getPosition(), probe.getDirection());
    }

    @GetMapping("/visited")
    @Operation(summary = "Get visited positions", description = "Returns the list of all positions visited by the probe")
    public List<Position> getVisited() {
        return probe.getVisited();
    }
}