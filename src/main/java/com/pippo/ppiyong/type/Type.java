package com.pippo.ppiyong.type;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Type {
    RAIN("호우"),
    EARTHQUAK("지진/해일");

    //등 추가

    private final String name;

    @JsonValue
    public String getName() {
        return name;
    }
}
