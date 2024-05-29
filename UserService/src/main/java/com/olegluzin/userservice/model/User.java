package com.olegluzin.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Document(collection = "users")
@AllArgsConstructor
public class User {
    @Id
    private String id;
    private String name;
    private Set<Instrument> portfolio;
}
