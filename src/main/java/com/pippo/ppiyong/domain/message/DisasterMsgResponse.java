package com.pippo.ppiyong.domain.message;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class DisasterMsgResponse {

    private List<DisasterMessage> row;
}
