package com.dauo.project.domain.channel;

import java.util.List;

public interface ChannelRepositoryCustom {
    List<Channel> findByWhere(ChannelSearchCondition condition);

}
