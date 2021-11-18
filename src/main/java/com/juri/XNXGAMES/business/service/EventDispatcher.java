package com.juri.XNXGAMES.business.service;

import com.juri.XNXGAMES.business.message.BoardToMemberPostMessage;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventDispatcher {

	private static final String EXCHANGE = "BoardExchange";
	private static final String RESILIENCE4J_INSTANCE = "default";

	private final RabbitTemplate rabbitTemplate;

	EventDispatcher(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	@CircuitBreaker(name = RESILIENCE4J_INSTANCE)
	@Bulkhead(name = RESILIENCE4J_INSTANCE)
	@Retry(name = RESILIENCE4J_INSTANCE)
	public void boardToMemberPostSend(BoardToMemberPostMessage message) {
		rabbitTemplate.convertAndSend(EXCHANGE, "BoardToMember.Post", message);
	}
	
}
