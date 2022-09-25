package com.dauo.project.domain.channel;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect
public class ResChannelDto {

    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH", timezone = "Asia/Seoul")
    private LocalDateTime time;

    private Integer joinMemberCnt;

    private Integer leaveMemberCnt;

    private Integer payAmount;

    private Integer useAmount;

    private Integer saleAmount;
    public static ResChannelDto of(Channel channel) {
        return ResChannelDto.builder()
                .id(channel.getId())
                .time(channel.getTime())
                .joinMemberCnt(channel.getJoinMemberCnt())
                .leaveMemberCnt(channel.getLeaveMemberCnt())
                .payAmount(channel.getPayAmount())
                .useAmount(channel.getUseAmount())
                .saleAmount(channel.getSaleAmount())
                .build();
    }

     public static List<ResChannelDto> of(List<Channel> channels) {
         return channels.stream().map(dto -> of(dto)).collect(Collectors.toList());
     }

}
