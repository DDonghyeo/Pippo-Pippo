package com.pippo.ppiyong.type;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    WEATHER("날씨"),
    EARTHQUAKE("지진/해일"),
    CIVIL("민방위"),
    LOST("실종자");

    private final String name;

    //@JsonValue
    public String getName() {
        return name;
    }
}
