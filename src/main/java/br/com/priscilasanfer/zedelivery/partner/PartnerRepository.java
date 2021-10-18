package br.com.priscilasanfer.zedelivery.partner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {

    @Query(nativeQuery = true, value = "select *, st_distance(POINT(:lat, :lon), POINT(st_x(p.address), st_y(p.address))) as distance " +
            "from partner p " +
            "where (st_contains (p.coverage_area, point(:lat, :lon))) = true " +
            "order by distance  limit 1 ")
    Optional<Partner> findNearestPartner(Double lat, Double lon);
}
