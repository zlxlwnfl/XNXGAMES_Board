package com.juri.XNXGAMES.business.dto;

import com.juri.XNXGAMES.business.entity.CommentEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.lang.NonNull;

import java.util.Date;

@Getter
@ToString
@RequiredArgsConstructor
@Builder
public class CommentPostResponseDTO {

    private final long commentId;
    private final long postId;
    @NonNull
    private final String writerId;
    @NonNull
    private final Date regdate;
    @NonNull
    private final String content;
    private final int heartCount;

    static public CommentPostResponseDTO fromCommentEntity(@NonNull final CommentEntity commentEntity) {
        return CommentPostResponseDTO.builder()
                .commentId(commentEntity.getCommentId())
                .postId(commentEntity.getPostId())
                .writerId(commentEntity.getWriterId())
                .regdate(commentEntity.getRegdate())
                .content(commentEntity.getContent())
                .heartCount(commentEntity.getHeartCount())
                .build();
    }

}
