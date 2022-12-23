package com.examenfinal.examenbackend.model.responses;


import java.util.Date;

import lombok.Data;

@Data
public class BillResponse {
    private int type;
    private int value;
    private String observation;
    private String user;
    private int id;
    private Date date;
}
