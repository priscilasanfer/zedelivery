package br.com.priscilasanfer.zedelivery.partner;

import br.com.priscilasanfer.zedelivery.utils.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PartnerService {

    @Autowired
    private PartnerRepository repository;

    @Autowired
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
        Optional<Partner> partner = repository.findNearestPartner(lat, lgn);
        if (partner.isEmpty())
            throw new ResourceNotFoundException("There is no partner available in the researched area");
        return converter.toDto(partner.get());
    }
}
