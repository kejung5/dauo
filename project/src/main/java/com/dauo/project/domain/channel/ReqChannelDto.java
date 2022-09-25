package com.dauo.project.domain.channel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReqChannelDto {

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime time;

    @NotNull
    private Integer joinMemberCnt;

    @NotNull
    private Integer leaveMemberCnt;

    @NotNull
    private Integer payAmount;

    @NotNull
    private Integer useAmount;

    @NotNull
    private Integer saleAmount;

    public Channel toEntity() {
        return Channel.builder()
                .time(time)
                .joinMemberCnt(joinMemberCnt)
                .leaveMemberCnt(leaveMemberCnt)
                .payAmount(payAmount)
                .useAmount(useAmount)
                .saleAmount(saleAmount)
                .build();
    }
}
