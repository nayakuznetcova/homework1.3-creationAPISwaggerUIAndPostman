package ru.hogwarts.school.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Faculty;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerRestTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;
    private String name1 = "Hufflepuff";
    private String name2 = "Ravenclaw";
    private String color1 = "yellow";
    private String color2 = "blue";

    @Test
    public void contextLoad() {

    }

    @Test
    public void addAndGetAndDeleteTest() {
        ResponseEntity<Faculty> facultyResponse
                = testRestTemplate.postForEntity("http://localhost:" + port + "/faculty?name=" + name2 + "color" + color2, new Faculty(name2, color2), Faculty.class);
        assertThat(facultyResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Faculty body1 = facultyResponse.getBody();

        ResponseEntity<Faculty> facultyGet
                = testRestTemplate.getForEntity("http://localhost:" + port + "/faculty?id=" + body1.getId(), Faculty.class);
        assertThat(facultyGet.getStatusCode()).isEqualTo(HttpStatus.OK);
        Faculty body = facultyGet.getBody();
        assertThat(body.getId()).isEqualTo(body1.getId());
        assertThat(body.getColor()).isEqualTo(body1.getColor());
        assertThat(body.getName()).isEqualTo(body1.getName());

        testRestTemplate.delete("http://localhost:" + port + "/faculty?id=" + body.getId(), Faculty.class);
        ResponseEntity<Faculty> facultyGetAfterDelete
                = testRestTemplate.getForEntity("http://localhost:" + port + "/faculty?id=" + body.getId(), Faculty.class);
        Faculty bodyAfterDelete = facultyGetAfterDelete.getBody();
        assertThat(bodyAfterDelete).isNull();

    }

    @Test
    public void updateTest() {
        ResponseEntity<Faculty> facultyResponse
                = testRestTemplate.postForEntity("http://localhost:" + port + "/faculty?name=" + name2 + "color" + color2, new Faculty(name2, color2), Faculty.class);
        assertThat(facultyResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Faculty body1 = facultyResponse.getBody();
        body1.setName(name1);
        body1.setColor(color1);

        testRestTemplate.put("http://localhost:" + port + "/faculty", body1);

        ResponseEntity<Faculty> facultyGet
                = testRestTemplate.getForEntity("http://localhost:" + port + "/faculty?id=" + body1.getId(), Faculty.class);
        assertThat(facultyGet.getStatusCode()).isEqualTo(HttpStatus.OK);
        Faculty body = facultyGet.getBody();
        assertThat(body.getId()).isEqualTo(body1.getId());
        assertThat(body.getColor()).isEqualTo(color1);
        assertThat(body.getName()).isEqualTo(name1);


    }

}
