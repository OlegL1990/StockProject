package com.olegluzin.userservice.dto;

import com.olegluzin.userservice.model.Instrument;
import lombok.Value;

import java.util.Set;

@Value
public class UserDto {
    private String id;
    private String name;
    private Set<Instrument> portfolio;

}
