package com.lcx.blog.service.impl;

import com.lcx.blog.NotFoundException;
import com.lcx.blog.dao.TypeRepository;
import com.lcx.blog.model.Type;
import com.lcx.blog.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * created Lcxin
 * 分类标签管理：业务层
 **/
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    @Qualifier(value = "typeRepository")
    private TypeRepository typeRepository;

    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeRepository.getOne(id);
    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        // SpringBoot2.1.1以上，Sort 父类已不再公有，所以要使用Sort.by
        Sort sort = Sort.by(Sort.Direction.DESC, "blogs.size");
        // SpringBoot2.1.1以上，Pageable 父类也不允许在其他包中使用，所以要使用PageRequest下的of方法
        Pageable pageable = PageRequest.of(0, size, sort);
        return typeRepository.findTop(pageable);
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type type1 = typeRepository.getOne(id);
        if(type1 == null){
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(type, type1);
        return typeRepository.save(type1);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }

    @Override
    public Type getTypeName(String name) {
        return typeRepository.findByName(name);
    }
}
