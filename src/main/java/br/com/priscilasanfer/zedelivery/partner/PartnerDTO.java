package br.com.priscilasanfer.zedelivery.partner;

import br.com.priscilasanfer.zedelivery.utils.validation.Document;
import br.com.priscilasanfer.zedelivery.utils.validation.UniqueValue;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PartnerDTO {

    private Long id;

    @NotNull(message = "Trading Name cannot be null")
    private String tradingName;

    @NotNull(message = "Trading Name cannot be null")
    private String ownerName;

    @NotNull(message = "Document cannot be null")
    @Document
    @UniqueValue(targetClass = Partner.class, field = "document")
    private String document;

    @NotNull(message = "Coverage area cannot be nul")
    @JsonSerialize(using = GeometrySerializer.class)
    @JsonDeserialize(contentUsing = GeometryDeserializer.class)
    private MultiPolygon coverageArea;

    @NotNull(message = "Address cannot be null")
    @JsonSerialize(using = GeometrySerializer.class)
    @JsonDeserialize(contentUsing = GeometryDeserializer.class)
    private Point address;
}
