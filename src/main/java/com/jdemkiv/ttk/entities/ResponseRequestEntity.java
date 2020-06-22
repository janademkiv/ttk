package com.jdemkiv.ttk.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "REQ")
public class ResponseRequestEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "response")
    private String response;
    @Column(name = "request")
    private String request;
}
