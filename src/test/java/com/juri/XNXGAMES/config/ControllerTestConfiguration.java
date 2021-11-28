package com.juri.XNXGAMES.config;

import com.juri.XNXGAMES.business.service.BoardService;
import com.juri.XNXGAMES.business.service.CommentService;
import com.juri.XNXGAMES.business.service.PostService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

@TestConfiguration
public class ControllerTestConfiguration {

    @MockBean
    CommentService mockCommentService;
    @MockBean
    PostService mockPostService;
    @MockBean
    BoardService mockBoardService;

}
