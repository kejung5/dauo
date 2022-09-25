package com.dauo.project.domain.channel;

import com.dauo.project.common.utils.DatetimeUtils;
import com.dauo.project.common.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "CHANNEL")
public class Channel extends BaseEntity<Long> {
    private static final long serialVersionUID = -4014364134048784909L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TIME", nullable = false)
    private LocalDateTime time;

    @Column(name = "JOIN_MEMBER_CNT", nullable = false)
    private Integer joinMemberCnt;

    @Column(name = "LEAVE_MEMBER_CNT", nullable = false)
    private Integer leaveMemberCnt;

    @Column(name = "PAY_AMOUNT", nullable = false)
    private Integer payAmount;

    @Column(name = "USE_AMOUNT", nullable = false)
    private Integer useAmount;

    @Column(name = "SALE_AMOUNT", nullable = false)
    private Integer saleAmount;

    public static Channel of(ImportChannelDto channelDto) {
        return Channel.builder()
                .time(DatetimeUtils.parseyyyyMMddHH(channelDto.getTime()))
                .joinMemberCnt(channelDto.getJoinMemberCnt())
                .leaveMemberCnt(channelDto.getLeaveMemberCnt())
                .payAmount(Integer.valueOf(channelDto.getPayAmount().replaceAll(",", "")))
                .useAmount(Integer.valueOf(channelDto.getUseAmount().replaceAll(",", "")))
                .saleAmount(Integer.valueOf(channelDto.getSaleAmount().replaceAll(",", "")))
                .build();
    }
}