package com.sgsoft.search_engine_task_api.services;

import com.sgsoft.search_engine_task_api.models.Tag;
import com.sgsoft.search_engine_task_api.repositories.TagRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TagServiceTest {

    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private TagService tagService;

    @Test
    void saveTag() {
        Tag tag = new Tag();
        Mockito.when(tagRepository.save(ArgumentMatchers.any(Tag.class))).thenReturn(tag);
        Tag tag1 = tagService.saveTag(tag);
        Assertions.assertEquals(tag,tag1);
    }

    @Test
    void getTags() {
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag());
        BDDMockito.given(tagRepository.findAll()).willReturn(tags);
        List<Tag> tags1 = tagService.getTags();
        Assertions.assertEquals(tags,tags1);
    }

    @Test
    void getTag() {
        Tag tag = new Tag();
        BDDMockito.given(tagRepository.getById(tag.getId())).willReturn(tag);
        Tag tag1 = tagService.getTag(tag.getId());
        Assertions.assertEquals(tag,tag1);
    }

    @Test
    void updateTag() {
        Tag tag = new Tag();
        tag.setId(1);
        tag.setKeyword("Test Keyword");

        Tag newTag = new Tag();
        tag.setKeyword("New Test Keyword");

        BDDMockito.given(tagRepository.findById(tag.getId())).willReturn(Optional.of(tag));
        tagService.updateTag(tag.getId(), newTag);
    }

    @Test
    void deleteTag() {
        Tag tag = new Tag();
        tagService.deleteTag(tag.getId());
        Mockito.verify(tagRepository).deleteById(tag.getId());
    }
}