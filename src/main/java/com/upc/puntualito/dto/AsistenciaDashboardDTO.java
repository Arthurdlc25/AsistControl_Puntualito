package com.upc.puntualito.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AsistenciaDashboardDTO {

    //HU05

    private Long presentes;
    private Long ausentes;
    private Long tardanzas;
}
