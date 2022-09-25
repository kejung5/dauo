package com.dauo.project.domain.channel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel, Long>, ChannelRepositoryCustom {

}
