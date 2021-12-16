package com.juri.XNXGAMES.business.dto;

import com.juri.XNXGAMES.business.entity.PostEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.lang.NonNull;

import java.util.List;

@Getter
@ToString
@RequiredArgsConstructor
@Builder
public class PostPostResponseDTO {

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

    static public PostPostResponseDTO fromPostEntity(@NonNull final PostEntity postEntity) {
        return PostPostResponseDTO.builder()
                .postId(postEntity.getPostId())
                .type(postEntity.getType())
                .boardId(postEntity.getBoardId())
                .writerId(postEntity.getWriterId())
                .commentCount(postEntity.getCommentCount())
                .regdate(postEntity.getRegdate().toString())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .hits(postEntity.getHits())
                .heartCount(postEntity.getHeartCount())
                .gameTagList(postEntity.getGameTagList())
                .build();
    }

}
