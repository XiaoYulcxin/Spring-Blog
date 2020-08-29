package com.lcx.blog.web;

import com.lcx.blog.model.Type;
import com.lcx.blog.service.BlogService;
import com.lcx.blog.service.TypeService;
import com.lcx.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * created Lcxin
 **/
@Controller
public class TypesShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String type(@PathVariable Long id, @PageableDefault(sort = {"updateTime"}, direction = Sort.Direction.DESC, size = 3)
                               Pageable pageable, Model model){
        List<Type> types = typeService.listTypeTop(10000); //查询全部数据
        if (id == -1){ //如果传入id为-1
            id = types.get(0).getId(); //设置当前id值为用户选中得分类页面
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        model.addAttribute("types", types); //分类
        model.addAttribute("page", blogService.listBlogs(pageable,blogQuery)); //对应博客的列表
        model.addAttribute("activeTypeId",id); //将id传回静态页面
        return "types";
    }
}
