package br.com.priscilasanfer.zedelivery.partner;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/partners")
public class PartnerController {

    private PartnerService service;

    @GetMapping("/{id}")
    @Operation(summary = "Find partner using its ID")
    @ApiResponses(value = {
            @ApiResponse(description = "Partner consulted with success", responseCode = "200",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Partner.class))))
    })
    public ResponseEntity<PartnerDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new Partner")
    @ApiResponses(value = {
            @ApiResponse(description = "Partner created with success", responseCode = "201",
                    content = @Content(schema = @Schema(implementation = Partner.class)))
    })
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
    @Operation(summary = "Find the nearest partner under coverage area passing longitude and latitude ")
    @ApiResponses(value = {
            @ApiResponse(description = "Partner consulted with success", responseCode = "200",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Partner.class))))
    })
    public ResponseEntity<PartnerDTO> find(
            @RequestParam(value = "lat", required = false) Double lat,
            @RequestParam(value = "long", required = false) Double lng
    ) {
        return ResponseEntity.ok().body(service.find(lat, lng));
    }

}
