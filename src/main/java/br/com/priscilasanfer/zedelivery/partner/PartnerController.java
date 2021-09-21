package br.com.priscilasanfer.zedelivery.partner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/partners")
public class PartnerController {

    @Autowired
    private PartnerService service;

    @GetMapping("/{id}")
    public ResponseEntity<PartnerDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<PartnerDTO> insert(@Valid @RequestBody PartnerDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(dto);

    }

    @GetMapping
    public ResponseEntity<PartnerDTO> find(
            @RequestParam(value = "lat", required = false) Double lat,
            @RequestParam(value = "long", required = false) Double lng
    ) {
        return ResponseEntity.ok().body(service.find(lat, lng));
    }

}
