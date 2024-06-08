package ca.sheridancollege.malhojay.beans;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Drink implements Serializable {
    private int id;
    private String name;
    private String main1;
    private double amount1;
    private String main2;
    private double amount2;
    private String directions;
}


