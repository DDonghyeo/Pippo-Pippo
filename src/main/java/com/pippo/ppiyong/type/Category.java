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

    public static Category fromValues(int weather, int earthquake, int civil, int lost) {
        if (weather == 1 && earthquake == 0 && civil == 0 && lost == 0) {
            return WEATHER;
        } else if (weather == 0 && earthquake == 1 && civil == 0 && lost == 0) {
            return EARTHQUAKE;
        } else if (weather == 0 && earthquake == 0 && civil == 1 && lost == 0) {
            return CIVIL;
        } else if (weather == 0 && earthquake == 0 && civil == 0 && lost == 1) {
            return LOST;
        }

        return null; // Return null if no match found
    }


    //@JsonValue
    public String getName() {
        return name;
    }
}
