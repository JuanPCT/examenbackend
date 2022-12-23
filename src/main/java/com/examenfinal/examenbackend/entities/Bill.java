package com.examenfinal.examenbackend.entities;

import java.io.Serial;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name = "bill")
public class Bill {

    @Id
    private Integer id;
    private Date date_bill;
    private int value;
    private int type;
    private String observation;
    
    @JsonIgnore
    @ManyToOne
	@JoinColumn(name = "user_id",insertable = false,updatable = false)
    private User user;
}
