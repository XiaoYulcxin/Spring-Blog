package com.lcx.blog.web.admin;

import com.lcx.blog.model.Type;
import com.lcx.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


/**
 * created Lcxin
 * 分类页控制层
 **/
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    //遍历分类标签，并进行分页，每个页面有八条数据，根据id进行倒序排序
    public String list(@PageableDefault(size = 8, sort = {"id"}, direction = Sort.Direction.DESC)
                                   Pageable pageable, Model model){
        model.addAttribute("page", typeService.listType(pageable));
        return "admin/types";
    }

    //添加
    @GetMapping("/types/add")
    public String add(Model model){
        model.addAttribute("type", new Type());
        return "admin/TypeRelease";
    }

    //后端索引验证：name值不能为空

    /**
     * 添加标签后台处理
     * @param type
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes){
        //与用户提交的分类名称和数据库进行比对,判断输入的名称是否重复
        if (typeService.getTypeName(type.getName()) != null){
            result.rejectValue("name", "nameError", "名称不能重复");
        }
        if(result.hasErrors()){
            return "admin/TypeRelease";
        }
        typeService.saveType(type);
        if (typeService.saveType(type) == null){
            //如果添加失败，返回“添加失败”字眼
            attributes.addFlashAttribute("message", "添加失败");
        }else{
            //新增成功
            attributes.addFlashAttribute("message", "添加成功");
        }
        return "redirect:/admin/types";
    }

    //修改
    @GetMapping("/types/{id}/update")
    public String update(Model model, @PathVariable Long id){
        model.addAttribute("type", typeService.getType(id));
        return "admin/TypeRelease";
    }



    /**
     * 修改标签后台处理
     * @param type
     * @param result
     * @param id
     * @param attributes
     * @return
     */
    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult result,@PathVariable Long id ,RedirectAttributes attributes){
        //与用户提交的分类名称和数据库进行比对,判断输入的名称是否重复
        if (typeService.getTypeName(type.getName()) != null){
            result.rejectValue("name", "nameError", "名称不能重复");
        }
        if(result.hasErrors()){
            return "admin/TypeRelease";
        }
        typeService.updateType(id, type);
        if (typeService.updateType(id, type) == null){
            attributes.addFlashAttribute("message", "修改失败");
        }else{
            attributes.addFlashAttribute("message", "修改成功");
        }
        return "redirect:/admin/types";
    }

    //删除
    @GetMapping("/types/{id}/delete")
    public String delete(RedirectAttributes attributes, @PathVariable Long id){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }
}
