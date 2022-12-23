package com.examenfinal.examenbackend.model.requests;

import lombok.Data;

@Data
public class BillRequest {
    
    private int type;
    private int value;
    private String observation;
}
