package com.example.ems.config;


import com.example.ems.model.ErrorMessageDTO;
import com.example.ems.model.InfoMessageDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
@Getter
@Setter
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "global-message")
public class MessageConfiguration {

    private HashMap<String, String> error;
    private HashMap<String, String> info;


    public ErrorMessageDTO errorMessage(final String code) {
        final ErrorMessageDTO dto = new ErrorMessageDTO();
        dto.setCode(code);
        dto.setMessage(error.get(code));
        return dto;
    }


    public ErrorMessageDTO errorMessage(final String code, final String detail) {
        final ErrorMessageDTO dto = new ErrorMessageDTO();
        dto.setCode(code);
        dto.setDetail(detail);
        dto.setMessage(error.get(code));
        return dto;
    }

    public String getErrorMessage(final String code) {
        return code;
    }


    public InfoMessageDTO infoMessage(final String code) {
        final InfoMessageDTO dto = new InfoMessageDTO();
        dto.setCode(code);
        dto.setMessage(info.get(code));
        return dto;
    }


    public InfoMessageDTO infoMessage(final String code, final String detail) {
        final InfoMessageDTO dto = new InfoMessageDTO();
        dto.setCode(code);
        dto.setDetail(detail);
        dto.setMessage(info.get(code));
        return dto;
    }


}
