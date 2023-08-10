package com.pippo.ppiyong.type;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Type {
    RAIN("호우"),
    HOT("폭염"),
    WIND("태풍"),
    SNOW("폭설"),
    EARTHQUAKE("지진/해일"),
    CIVIL("민방위"),
    LOST("실종자");

    private final String name;

    //@JsonValue
    public String getName() {
        return name;
    }
}
