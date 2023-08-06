package com.pippo.ppiyong.type;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Region {
    SEOUL("서울특별시"),
    BUSAN("부산광역시"),
    DAEGU("대구광역시"),
    INCHEON("인천광역시"),
    GWANGJU("광주광역시"),
    DAEJEON("대전광역시"),
    ULSAN("울산광역시"),
    SEJONG("세종특별자치시"),
    GYEONGGI("경기도"),
    GANGWON("강원도"),
    CHUNGCHEONGBUKDO("충청북도"),
    CHUNGCHEONGNAMDO("충청남도"),
    JEOLLABUKDO("전라북도"),
    JEOLLANAMDO("전라남도"),
    GYEONGSANGBUKDO("경상북도"),
    GYEONGSANGNAMDO("경상남도"),
    JEJU("제주특별자치도");

    private final String name;

    @JsonValue
    public String getName() {
        return name;
    }
}
