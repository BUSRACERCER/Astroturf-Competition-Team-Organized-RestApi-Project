package com.works.demo.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class BackUp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bid;

    private Long fid;
    private Long tid;
}
