package br.com.priscilasanfer.zedelivery.partner;

import br.com.priscilasanfer.zedelivery.utils.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PartnerService {

    private PartnerRepository repository;
    private PartnerConverter converter;

    @Transactional(readOnly = true)
    public PartnerDTO findById(Long id) {
        Partner partner = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The Partner with id informed was not found"));
        return converter.toDto(partner);
    }

    @Transactional
    public PartnerDTO insert(PartnerDTO dto) {
        Partner partner = repository.save(converter.toModel(dto));
        return converter.toDto(partner);
    }

    public PartnerDTO find(Double lat, Double lgn) {
        Partner partner = repository.findNearestPartner(lat, lgn)
                .orElseThrow(() -> new ResourceNotFoundException("There is no partner available in the researched area"));
        return converter.toDto(partner);
    }
}
