package sk.kosickaakademia.matorudolf;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JokeController {

    String joke1 = "add joke 1";
    String joke2 = "add joke 2";
    String joke3 = "add joke 3";

    @GetMapping ("/joke")

    public ResponseEntity<String> getJokes(){}
}
