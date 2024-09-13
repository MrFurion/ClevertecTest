package by.clevertec.controller;

import by.clevertec.domain.Cake;
import by.clevertec.exception.CakeNotFoundException;
import by.clevertec.service.CakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CakeController {
    private final CakeService cakeService;

    @GetMapping("/api/getCake")
    public ResponseEntity<List<Cake>> findAll(){
        List<Cake> cake = cakeService.getCake();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
                .body(cake);
    }

    @GetMapping("/api/{id}")
    public ResponseEntity<Cake> findById(@PathVariable("id") UUID id){
        Cake cake = cakeService.getCakeById(id);
        return ResponseEntity.ok(cake);
    }

    @PutMapping("/api/up/{id}")
    public ResponseEntity<Cake> update(@PathVariable("id") UUID id, @Validated @RequestBody Cake cake){
        Cake cakeUpdate = cakeService.update(id, cake);
        return ResponseEntity.ok(cakeUpdate);
    }

    @DeleteMapping("/api/del/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id){

        try {
            cakeService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (CakeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sensor not found with id: " + id);
        }
    }
}
