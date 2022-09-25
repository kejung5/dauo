package com.dauo.project.controller;

import com.dauo.project.common.base.Responses;
import com.dauo.project.domain.channel.ChannelSearchCondition;
import com.dauo.project.domain.channel.ChannelService;
import com.dauo.project.domain.channel.ReqChannelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/channel")
@RequiredArgsConstructor
public class ChannelController {
    private final ChannelService channelService;

    @GetMapping
    public Responses.DataResponse findAll(ChannelSearchCondition condition) {
        return Responses.DataResponse.of(channelService.findAll(condition));
    }

    @GetMapping(value = "/{id}")
    public Responses.DataResponse find(@PathVariable Long id) {
        return Responses.DataResponse.of(channelService.find(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public long create(@RequestBody @Valid ReqChannelDto reqDto) {
        return channelService.create(reqDto);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void update(@PathVariable Long id, @RequestBody @Valid ReqChannelDto userDto) {
        channelService.update(id, userDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        channelService.delete(id);
    }
}