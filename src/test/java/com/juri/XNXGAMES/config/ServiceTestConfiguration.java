package com.juri.XNXGAMES.config;

import com.juri.XNXGAMES.business.repository.BoardRepository;
import com.juri.XNXGAMES.business.repository.CommentRepository;
import com.juri.XNXGAMES.business.repository.PostRepository;
import io.github.resilience4j.circuitbreaker.autoconfigure.CircuitBreakerAutoConfiguration;
import io.github.resilience4j.timelimiter.autoconfigure.TimeLimiterAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

@TestConfiguration
@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,
        CircuitBreakerAutoConfiguration.class,
        TimeLimiterAutoConfiguration.class
})
public class ServiceTestConfiguration {

    @MockBean
    CommentRepository mockCommentRepository;
    @MockBean
    PostRepository mockPostRepository;
    @MockBean
    BoardRepository mockBoardRepository;

}
