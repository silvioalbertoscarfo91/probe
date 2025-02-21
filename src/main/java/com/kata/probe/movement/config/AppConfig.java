package com.kata.probe.movement.config;

import com.kata.probe.movement.Direction;
import com.kata.probe.movement.Grid;
import com.kata.probe.movement.Position;
import com.kata.probe.movement.Probe;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class AppConfig {
    //assumptions:
    //  - Grid dimension is not specified, so I decided to give it 10x10 dimensions and put 2 Obstacles
    //  - Probe position is not specified, so I decided to set the Probe at (0, 0) in the grid and give North direction
    // I put those in a configuration class so then in the future they can be changed from the yaml file or defining
    // another solution and set them through API calls.
    @Bean
    public Grid grid() {
        return new Grid(10, 10, Set.of(new Position(2, 2), new Position(3, 5)));
    }

    @Bean
    public Probe probe() {
        return new Probe(0, 0, Direction.N);
    }
}