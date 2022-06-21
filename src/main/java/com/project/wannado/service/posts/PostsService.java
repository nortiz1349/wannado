package com.project.wannado.service.posts;

import com.project.wannado.domain.posts.Posts;
import com.project.wannado.domain.posts.PostsRepository;
import com.project.wannado.web.dto.PostsListResponseDto;
import com.project.wannado.web.dto.PostsResponseDto;
import com.project.wannado.web.dto.PostsSaveRequestDto;
import com.project.wannado.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }

    /**
     * `@Transactional(readOnly=true)
     * readOnly = true 옵션을 주면 트랜잭션 범위는 유지하되, 조회 기능만 남겨 조회 속도가 개선됨.
     * 등록, 수정, 삭제 기능이 전혀 없는 메서드에서 사용하는 것을 추천.
     *
     * 람다식 참조
     * .map(PostsListResponseDto::new) = .map(posts -> new PostsListResponseDto(posts))
     * postsRepository 결과로 넘어온 Posts 의 Stream 을 map 을 통해 PostsListResponseDto 변환 -> List 로 반환하는 메서드
     */
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postsRepository.delete(posts);
    }

}