package br.com.priscilasanfer.zedelivery.partner;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PartnerConverter {

    @Autowired
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
