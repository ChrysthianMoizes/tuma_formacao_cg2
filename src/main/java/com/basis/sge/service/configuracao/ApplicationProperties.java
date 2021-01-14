package com.basis.sge.service.configuracao;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "application.mail")
@Configuration
@Data
public class ApplicationProperties {

    private String enderecoRemetente;

    private String nomeRemetente;

}
