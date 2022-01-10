package com.juri.XNXGAMES.business.dto;

import com.juri.XNXGAMES.business.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;

public class CommentDTO {

    @Getter
    @ToString
    @RequiredArgsConstructor
    static public class Request {
        @NonNull
        @NotBlank
        private final String writerId;
        @NonNull
        @NotBlank
        private final String content;
    }

    @Getter
    @ToString
    @RequiredArgsConstructor
    @Builder
    static public class Response {
        private final long commentId;
        private final long postId;
        @NonNull
        private final String writerId;
        @NonNull
        private final String regdate;
        @NonNull
        private final String content;
        private final int heartCount;

        static public CommentDTO.Response fromComment(@NonNull final Comment comment) {
            return CommentDTO.Response.builder()
                    .commentId(comment.getCommentId())
                    .postId(comment.getPostId())
                    .writerId(comment.getWriterId())
                    .regdate(comment.getRegdate().toString())
                    .content(comment.getContent())
                    .heartCount(comment.getHeartCount())
                    .build();
        }
    }

}
