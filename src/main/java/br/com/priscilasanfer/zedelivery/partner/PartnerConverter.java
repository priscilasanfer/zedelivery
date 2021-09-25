package br.com.priscilasanfer.zedelivery.partner;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class PartnerConverter {

    private ModelMapper mapper;

    public Partner toModel(PartnerDTO dto) {
        log.info("Converting DTO to Model");
        return mapper.map(dto, Partner.class);
    }

    public PartnerDTO toDto(Partner dto) {
        log.info("Converting model to dto");
        return mapper.map(dto, PartnerDTO.class);
    }
}
