package com.lcx.blog.service;

import com.lcx.blog.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 标签业务层实现类
 */
public interface TagService {

    Tag saveTag(Tag tag);

    Tag getTag(Long id);

    Page<Tag> listTags(Pageable pageable);

    Tag updateTag(Tag tag, Long id);

    void deleteTag(Long id);

    List<Tag> listTag();

    Tag findByTagName(String name);

    List<Tag> listTag(String ids);

    List<Tag> listTagTop(Integer size);

}
