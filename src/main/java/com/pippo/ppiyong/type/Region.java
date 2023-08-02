package com.pippo.ppiyong.type;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Region {
    SEOUL("서울특별시"),
    BUSAN("부산광역시");

    //등 추가

    private final String name;

    @JsonValue
    public String getName() {
        return name;
    }
}
