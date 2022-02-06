package com.project.screentool.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String zipNo;
    private String roadFullAddr;
    private String addrDetail;

}
