package br.com.priscilasanfer.zedelivery.factory;

import br.com.priscilasanfer.zedelivery.partner.Partner;
import br.com.priscilasanfer.zedelivery.partner.PartnerDTO;
import com.vividsolutions.jts.geom.*;

public class Factory {

    private static GeometryFactory geometryFactory;

    public static PartnerDTO createPartnerDTO() {
        geometryFactory = new GeometryFactory();
        Point address = geometryFactory.createPoint(new Coordinate(-43.297337, -23.013538));
        Coordinate[] coordinates1 = new Coordinate[]{
                new Coordinate(-46.61026, -23.66622),
                new Coordinate(-46.62596, -23.66985),
                new Coordinate(-46.63481, -23.6749),
                new Coordinate(-46.64012, -23.69742),
                new Coordinate(-46.63566, -23.71857),
                new Coordinate(-46.63154, -23.74041),
                new Coordinate(-46.63078, -23.75411),
                new Coordinate(-46.61701, -23.75216),
                new Coordinate(-46.59878, -23.74832),
                new Coordinate(-46.58076, -23.73916),
                new Coordinate(-46.56257, -23.73662),
                new Coordinate(-46.55038, -23.73378),
                new Coordinate(-46.54404, -23.73016),
                new Coordinate(-46.53368, -23.72612),
                new Coordinate(-46.52488, -23.71453),
                new Coordinate(-46.52502, -23.70481),
                new Coordinate(-46.52786, -23.69098),
                new Coordinate(-46.5573, -23.66818),
                new Coordinate(-46.57335, -23.66606),
                new Coordinate(-46.61026, -23.66622)
        };

        MultiPolygon coverageArea = geometryFactory.createMultiPolygon(new Polygon[]{geometryFactory.createPolygon(coordinates1)});
        return PartnerDTO.builder()
                .tradingName("Adega da Cerveja")
                .ownerName("Zé da Silva")
                .document("43.693.436/0001-16")
                .address(address)
                .coverageArea(coverageArea)
                .build();
    }

    public static Partner createPartner() {
        geometryFactory = new GeometryFactory();
        Point address10 = geometryFactory.createPoint(new Coordinate(-23.63155989012796, -46.73796904391404));
        Coordinate[] coordinates10 = new Coordinate[]{
                new Coordinate(-23.62602164463357, -46.7381894466976),
                new Coordinate(-23.629565728490356, -46.732987795922256),
                new Coordinate(-23.63554407423428, -46.732823285111884),
                new Coordinate(-23.636859664595708, -46.74220555717418),
                new Coordinate(-23.63019387098605, -46.74335440679014),
                new Coordinate(-23.62602164463357, -46.7381894466976)
        };
        MultiPolygon coverageArea10 = geometryFactory.createMultiPolygon(new Polygon[]{geometryFactory.createPolygon(coordinates10)});

        return Partner.builder()
                .tradingName("Bar Morumbi town")
                .ownerName("Felipe Rudenberg")
                .document("71.028.255/0001-00")
                .coverageArea(coverageArea10)
                .address(address10)
                .build();
    }
}
