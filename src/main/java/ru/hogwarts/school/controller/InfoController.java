package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping("/info")
public class InfoController {
    @Value("${server.port}")
    private Integer port;

    @GetMapping
    public ResponseEntity<Integer> getPort() {
        return ResponseEntity.ok(port);
    }

    @GetMapping("/return-number")
    public Integer returnNumber() {
        return Stream.iterate(1, a -> a + 1)
                .parallel()
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b);
    }
}
