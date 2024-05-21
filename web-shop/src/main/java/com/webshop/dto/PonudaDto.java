package com.webshop.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;

public class PonudaDto implements Serializable {
    private final Double cenaPonude;

    @JsonCreator
    public PonudaDto(double cenaPonude) {
        this.cenaPonude = cenaPonude;
    }

    public PonudaDto(PonudaDto ponudaDto) {
        this.cenaPonude = ponudaDto.getCenaPonude();
    }

    public double getCenaPonude() {return cenaPonude;}
}
