package com.dauo.project.domain.channel;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.dauo.project.domain.channel.QChannel.channel;

@Repository
public class ChannelRepositoryImpl extends QuerydslRepositorySupport implements ChannelRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ChannelRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Channel.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Channel> findByWhere(ChannelSearchCondition condition) {
        return queryFactory
                .selectFrom(channel)
                .where(
                        timeGoe(condition.getSDateTime()),
                        timeLoe(condition.getEDateTime()))
                .fetch();
    }

    private BooleanExpression timeGoe(LocalDateTime time) {
        return time == null ? null : channel.time.goe(time);
    }

    private BooleanExpression timeLoe(LocalDateTime time) {
        return time == null ? null : channel.time.loe(time);
    }
}
