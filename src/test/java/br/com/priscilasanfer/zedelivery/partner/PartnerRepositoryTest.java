package br.com.priscilasanfer.zedelivery.partner;

import br.com.priscilasanfer.zedelivery.factory.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)  // Necessário para não usar embedded Database
class PartnerRepositoryTest {

    @Autowired
    private PartnerRepository repository;

    @BeforeEach
    public void beforeAll() {
        repository.save(Factory.createFirstPartnerInsideSameCoverage());
        repository.save(Factory.createSecondPartnerInsideSameCoverage());
    }

    @Test
    void deveRetornarOParceiroMaisProximo() {
        Optional<Partner> nearestPartner = repository.findNearestPartner(-23.632382120633007, -46.73805055397889);

        assertTrue(nearestPartner.isPresent());
        assertEquals(nearestPartner.get().getOwnerName(), "Proprietário 1");
    }
}