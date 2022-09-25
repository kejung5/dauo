package com.dauo.project.domain.channel;

import com.dauo.project.common.code.ErrorCode;
import com.dauo.project.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChannelServiceImpl implements ChannelService {
    private final ChannelRepository channelRepository;

    public List<ResChannelDto> findAll(ChannelSearchCondition condition) {
        List<Channel> contents = channelRepository.findByWhere(condition);
        return ResChannelDto.of(contents);
    }

    public ResChannelDto find(Long id) {
        Channel channel = channelRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND_DATA, " id : " + id));

        return ResChannelDto.of(channel);
    }

    public long create(ReqChannelDto reqDto) {
        Channel channel = reqDto.toEntity();
        channelRepository.save(channel);

        return channel.getId();
    }

    public void update(Long id, ReqChannelDto reqDto) {
        find(id);

        Channel channel = reqDto.toEntity();
        channel.setId(id);
        channelRepository.save(channel);
    }

    public void delete(Long id) {
        find(id);
        channelRepository.deleteById(id);
    }
}
