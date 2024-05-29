package com.olegluzin.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class Instrument {
    private String ticker;
    private Integer quantity;

}
