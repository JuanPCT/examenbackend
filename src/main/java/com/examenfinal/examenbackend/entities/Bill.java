package com.examenfinal.examenbackend.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import lombok.Data;

@Entity
@Data
@Table(name = "bill")
public class Bill {

    @Id
	@SequenceGenerator(name="bill_id_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "bill_id_seq")
	private Integer id;
	private Date date_bill;
	private Integer user_id;
	private Integer value;
	private String observation;
    private Integer type;
}
