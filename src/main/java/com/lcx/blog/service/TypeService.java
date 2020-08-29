package com.lcx.blog.service;

import com.lcx.blog.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 分类业务层类
 */
public interface TypeService {

    //添加分类标签
    Type saveType(Type type);

    //查找对应的分类标签
    Type getType(Long id);

    //将分类标签进行分页处理
    Page<Type> listType(Pageable pageable);

    List<Type> listType();

    List<Type> listTypeTop(Integer size);
    //修改分类名称
    Type updateType(Long id, Type type);

    //删除分类名称
    void deleteType(Long id);

    Type getTypeName(String name);

}
