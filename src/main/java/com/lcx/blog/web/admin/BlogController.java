package com.lcx.blog.web.admin;

import com.lcx.blog.model.Blog;
import com.lcx.blog.model.Type;
import com.lcx.blog.model.User;
import com.lcx.blog.service.BlogService;
import com.lcx.blog.service.TagService;
import com.lcx.blog.service.TypeService;
import com.lcx.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


/**
 * created Lcxin
 * 后台博客管理页面
 **/
@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String LIST="admin/BlogsMess";
    private static final String INPUT="admin/BlogRelease";
    private static final String REDIRECT_LIST="redirect:/admin/blogs";

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    //定义分页配置的注解，规定每一个页面显示几条博客
    public String blogs(@PageableDefault(size = 3, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                    Model model, Pageable pageable, BlogQuery blog){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("page", blogService.listBlogs(pageable, blog));
        return LIST;
    }

    @PostMapping("/blogs/search")
    //定义分页配置的注解，规定每一个页面显示几条博客
    public String search(@PageableDefault(size = 3, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                Model model, Pageable pageable, BlogQuery blog){
        model.addAttribute("page", blogService.listBlogs(pageable, blog));
        return "admin/BlogsMess :: blogList";
    }

    /**
     * 添加
     * @return
     */
    @GetMapping("/blogs/add")
    public String add(Model model){
//        初始化数据
        setTypeAndTag(model);
        model.addAttribute("blog", new Blog());
        return INPUT;
    }

    private void setTypeAndTag(Model model){
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
    }

    @GetMapping("/blogs/{id}/update")
    public String edit(Model model, @PathVariable Long id){
        //初始化数据
        setTypeAndTag(model);
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog", blog);
         return INPUT;
    }

    @PostMapping("/blogs")
    public String post(Blog blog, HttpSession session , RedirectAttributes attributes){
        blog.setUser((User) session.getAttribute("user"));
        // 获取前端页面中类型的id
        blog.setType(typeService.getType(blog.getType().getId()));
        // 获取页面中标签列表的id
        blog.setTags(tagService.listTag(blog.getTagIds()));
        Blog blog1;
        if (blog.getId() == null){
            blog1 = blogService.saveBlog(blog);
        }else{
            blog1 = blogService.updateBlog(blog,blog.getId());
        }
        if (blog1 != null){
            attributes.addFlashAttribute("message","博客操作成功");
        }else{
            attributes.addFlashAttribute("message", "博客操作失败");
        }
        return REDIRECT_LIST;
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","删除成功");
        return REDIRECT_LIST;
    }
}
