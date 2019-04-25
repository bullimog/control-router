package router.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import router.connectors.FamilyConnection;
import router.models.Family;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/myservice")
public class JsonController {

    @Autowired
    private FamilyConnection fc; //Dependency resolved by a @Bean method that returns a FamilyConnection

    @GetMapping("/family-back/{familyId}")
    public Family retrieveFamilyBack(@PathVariable long familyId) {
        return fc.getFamily(familyId);
    }


    @GetMapping("/family/{familyId}")
    public Family retrieveFamily(@PathVariable long familyId) {
        return fc.getStubFamily(familyId);
    }


    @PostMapping("/family")
    public ResponseEntity<Family> create(@RequestBody Family family) throws URISyntaxException {
        Family createdFamily = fc.createFamily(family);
        if (createdFamily == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdFamily.getId())
                    .toUri();

            return ResponseEntity.created(uri)
                    .body(createdFamily);
        }
    }
}
