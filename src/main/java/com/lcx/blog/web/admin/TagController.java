package com.lcx.blog.web.admin;

import com.lcx.blog.model.Tag;
import com.lcx.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * created Lcxin
 **/
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 7, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, Model model){
        model.addAttribute("page", tagService.listTags(pageable));
        return "admin/tags";
    }

    //添加
    @GetMapping("/tags/add")
    public String add(Model model){
        model.addAttribute("tag", new Tag());
        return "admin/TagRelease";
    }

    //后端索引验证：name值不能为空

    /**
     * 添加标签后台处理
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult result, RedirectAttributes attributes){
        //与用户提交的分类名称和数据库进行比对,判断输入的名称是否重复
        if (tagService.findByTagName(tag.getName()) != null){
            result.rejectValue("name", "nameError", "名称不能重复");
        }
        if(result.hasErrors()){
            return "admin/TagRelease";
        }
        tagService.saveTag(tag);
        if (tagService.saveTag(tag) == null){
            //如果添加失败，返回“添加失败”字眼
            attributes.addFlashAttribute("message", "添加失败");
        }else{
            //新增成功
            attributes.addFlashAttribute("message", "添加成功");
        }
        return "redirect:/admin/tags";
    }

    //修改
    @GetMapping("/tags/{id}/update")
    public String update(Model model, @PathVariable Long id){
        model.addAttribute("tag", tagService.getTag(id));
        return "admin/TagRelease";
    }



    /**
     * 修改标签后台处理
     * @param result
     * @param id
     * @param attributes
     * @return
     */
    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag tag, BindingResult result,@PathVariable Long id ,RedirectAttributes attributes){
        //与用户提交的分类名称和数据库进行比对,判断输入的名称是否重复
        if (tagService.findByTagName(tag.getName()) != null){
            result.rejectValue("name", "nameError", "名称不能重复");
        }
        if(result.hasErrors()){
            return "admin/TagRelease";
        }
        tagService.updateTag(tag, id);
        if (tagService.updateTag(tag, id) == null){
            attributes.addFlashAttribute("message", "修改失败");
        }else{
            attributes.addFlashAttribute("message", "修改成功");
        }
        return "redirect:/admin/tags";
    }

    //删除
    @GetMapping("/tags/{id}/delete")
    public String delete(RedirectAttributes attributes, @PathVariable Long id){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }
}
