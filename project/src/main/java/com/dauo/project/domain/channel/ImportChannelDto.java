package com.dauo.project.domain.channel;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImportChannelDto {
    @CsvBindByPosition(position = 0)
    private String time;

    @CsvBindByPosition(position = 1)
    private Integer joinMemberCnt;

    @CsvBindByPosition(position = 2)
    private Integer leaveMemberCnt;

    @CsvBindByPosition(position = 3)
    private String payAmount;

    @CsvBindByPosition(position = 4)
    private String useAmount;

    @CsvBindByPosition(position = 5)
    private String saleAmount;

    public boolean valid() {
        return ObjectUtils.isEmpty(this.time)
                || ObjectUtils.isEmpty(this.joinMemberCnt)
                || ObjectUtils.isEmpty(this.leaveMemberCnt)
                || ObjectUtils.isEmpty(this.payAmount)
                || ObjectUtils.isEmpty(this.useAmount)
                || ObjectUtils.isEmpty(this.saleAmount);
    }
}
