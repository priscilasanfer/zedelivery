package br.com.priscilasanfer.zedelivery.utils.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.webmvc.ui.SwaggerConfig;

@OpenAPIDefinition(
        info = @Info(
                title = "Zé Delivery",
                description = "Resolução do Desafio Técnico",
                contact = @Contact(url = "https://github.com/priscilasanfer", name = "Priscila Ferreira")))
public class SwaggerConfiguration extends SwaggerConfig {

}
