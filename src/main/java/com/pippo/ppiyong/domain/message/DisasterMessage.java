package com.pippo.ppiyong.domain.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown=true)
public class DisasterMessage {

    @JsonProperty("create_date")
    private String createDate;

    @JsonProperty("location_name")
    private String locationName;

    @JsonProperty("md101_sn")
    private String id;

    private String msg;

}
