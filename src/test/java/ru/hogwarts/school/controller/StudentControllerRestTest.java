package ru.hogwarts.school.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.model.Student;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerRestTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;
    private String name1 = "Anna";
    private String name2 = "Petr";
    private int age1 = 22;
    private int age2 = 20;
    @Test
    public void contextLoad(){

    }

    @Test
    public void addAndGetAndDeleteTest(){
        Student responsePost
                = testRestTemplate.postForObject("http://localhost:"+port+"/student?name="+name2+"&age="+age2, null,Student.class);

        String responseGet
                = testRestTemplate.getForObject("http://localhost:"+port+"/student?id="+responsePost.getId(), String.class);
        assertEquals(responseGet, responsePost.toString());
    }

}

