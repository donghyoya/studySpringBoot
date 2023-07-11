package com.gogofnd.theCreditableCMS.domain.payment.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PgRegTerminalReqDto {

    private String mid;
    private String tid;
    private String bno;


}
