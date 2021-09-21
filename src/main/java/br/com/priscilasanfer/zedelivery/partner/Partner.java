package br.com.priscilasanfer.zedelivery.partner;

import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tradingName;

    @Column(nullable = false)
    private String ownerName;

    @Column(nullable = false)
    private String document;

    @Column(columnDefinition = "multipolygon", nullable = false)
    private MultiPolygon coverageArea;

    @Column(columnDefinition = "point", nullable = false)
    private Point address;

}
