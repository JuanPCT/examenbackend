package com.examenfinal.examenbackend.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name = "bill")
public class Bill {

    @Id
    @SequenceGenerator(name="bill_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="bill_id_seq")
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
