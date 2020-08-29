package com.lcx.blog.service.impl;

import com.lcx.blog.NotFoundException;
import com.lcx.blog.dao.TagRepository;
import com.lcx.blog.model.Tag;
import com.lcx.blog.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * created Lcxin
 * 标签业务层
 **/
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    @Qualifier(value = "tagRepository")
    private TagRepository tagRepository;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagRepository.getOne(id);
    }

    @Transactional
    @Override
    public Page<Tag> listTags(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Tag updateTag(Tag tag, Long id) {
        tagRepository.getOne(id);
        BeanUtils.copyProperties(tag, tagRepository.getOne(id));
        return tagRepository.save(tagRepository.getOne(id));
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

    @Transactional
    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    @Transactional
    @Override
    public Tag findByTagName(String name) {
        return tagRepository.findByName(name);
    }

    @Transactional
    @Override
    public List<Tag> listTag(String ids) {//1,2,3
        return tagRepository.findAllById(convertToList(ids));
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = PageRequest.of(0, size, sort);
        return tagRepository.findTop(pageable);
    }

    private List<Long> convertToList(String ids){
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null){
            String[] idaArray = ids.split(",");
            for (int i = 0; i < idaArray.length; i++) {
                list.add(new Long(idaArray[i]));
            }
        }
        return list;
    }
}
