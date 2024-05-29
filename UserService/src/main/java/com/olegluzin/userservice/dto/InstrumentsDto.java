package com.olegluzin.userservice.dto;

import com.olegluzin.userservice.model.Instrument;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InstrumentsDto {
    private Set<Instrument> instruments;
}
