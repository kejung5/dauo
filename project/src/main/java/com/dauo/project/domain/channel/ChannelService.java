package com.dauo.project.domain.channel;

import org.springframework.data.domain.Page;

import java.util.List;

public interface ChannelService {
    List<ResChannelDto> findAll(ChannelSearchCondition condition);

    ResChannelDto find(Long id);

    long create(ReqChannelDto reqDto);

    void update(Long id, ReqChannelDto reqDto);

    void delete(Long id);

}
