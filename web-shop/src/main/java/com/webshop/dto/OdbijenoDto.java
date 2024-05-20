package com.webshop.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;

/**
 * DTO for {@link com.webshop.model.PrijavaProfila}
 */
public class OdbijenoDto implements Serializable {

    private final String razlogOdbijanja;

    @JsonCreator
    public OdbijenoDto(String razlogOdbijanja) {
        this.razlogOdbijanja = razlogOdbijanja;
    }

    public OdbijenoDto(OdbijenoDto o) {
        this.razlogOdbijanja = o.getRazlogOdbijanja();
    }

    public String getRazlogOdbijanja() {
        return razlogOdbijanja;
    }
}
