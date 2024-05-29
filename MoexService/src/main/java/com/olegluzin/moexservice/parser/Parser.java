package com.olegluzin.moexservice.parser;

import com.olegluzin.moexservice.dto.BondDto;

import java.util.List;

public interface Parser {
    List<BondDto> parse(String bondsAsString);
    }