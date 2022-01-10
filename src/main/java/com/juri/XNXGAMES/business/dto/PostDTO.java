package com.juri.XNXGAMES.business.dto;

import com.juri.XNXGAMES.business.entity.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class PostDTO {

    @Getter
    @ToString
    @RequiredArgsConstructor
    static public class Request {
        private final long postId;
        @NonNull
        @NotBlank
        private final String title;
        @NonNull
        @NotBlank
        private final String content;
        @NonNull
        @NotBlank
        private final String writerId;
        @NonNull
        @NotBlank
        private final String postType;
        @NonNull
        private final List<String> gameTagList;
    }

    @Getter
    @ToString
    @RequiredArgsConstructor
    @Builder
    static public class Response {
        private final long postId;
        @NonNull
        private final String type;
        private final long boardId;
        @NonNull
        private final String writerId;
        private final int commentCount;
        @NonNull
        private final String regdate;
        @NonNull
        private final String title;
        @NonNull
        private final String content;
        private final int hits;
        private final int heartCount;
        @NonNull
        private final List<String> gameTagList;

        static public PostDTO.Response fromPost(@NonNull final Post post) {
            return PostDTO.Response.builder()
                    .postId(post.getPostId())
                    .type(post.getType())
                    .boardId(post.getBoardId())
                    .writerId(post.getWriterId())
                    .commentCount(post.getCommentCount())
                    .regdate(post.getRegdate().toString())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .hits(post.getHits())
                    .heartCount(post.getHeartCount())
                    .gameTagList(post.getGameTagList())
                    .build();
        }
    }

}
