package vn.iotstar.controllers.admin;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import vn.iotstar.entity.Category;
import vn.iotstar.model.CategoryModel;
import vn.iotstar.services.CategoryService;

@Controller
@RequestMapping("admin/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    // 👉 Thêm mới
    @GetMapping("add")
    public String add(ModelMap model) {
        CategoryModel cateModel = new CategoryModel();
        cateModel.setIsEdit(false);
        model.addAttribute("category", cateModel);
        return "admin/categories/AddOrEdit";
    }

    // 👉 Chỉnh sửa
    @GetMapping("edit/{id}")
    public String edit(ModelMap model, @PathVariable("id") Long id) {
        Optional<Category> opt = categoryService.findById(id);
        if (opt.isPresent()) {
            CategoryModel cateModel = new CategoryModel();
            BeanUtils.copyProperties(opt.get(), cateModel);
            cateModel.setIsEdit(true);
            model.addAttribute("category", cateModel);
            return "admin/categories/AddOrEdit";
        }
        model.addAttribute("message", "Category not found");
        return "redirect:/admin/categories/searchpaging";
    }

    // 👉 Lưu hoặc cập nhật
    @PostMapping("saveOrUpdate")
    public String saveOrUpdate(ModelMap model,
                               @Valid @ModelAttribute("category") CategoryModel cateModel,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "admin/categories/AddOrEdit";
        }

        Category entity = new Category();
        BeanUtils.copyProperties(cateModel, entity);
        categoryService.save(entity);

        model.addAttribute("message", "Category is saved!");
        return "redirect:/admin/categories/searchpaging";
    }

    // 👉 Xoá
//    @GetMapping("delete/{id}")
//    public String delete(ModelMap model, @PathVariable("id") Long id) {
//        categoryService.deleteById(id);
//        model.addAttribute("message", "Category deleted!");
//        return "redirect:/admin/categories/searchpaging";
//    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        categoryService.deleteById(id);
        return "redirect:/admin/categories/searchpaging";
    }

    // 👉 Hiển thị danh sách có phân trang & tìm kiếm
    @RequestMapping("searchpaging")
    public String search(ModelMap model,
                         @RequestParam(name = "name", required = false) String name,
                         @RequestParam("page") Optional<Integer> page,
                         @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        Page<Category> resultPage;

        if (StringUtils.hasText(name)) {
            resultPage = categoryService.findByNameContaining(name, pageable);
            model.addAttribute("name", name);
        } else {
            resultPage = categoryService.findAll(pageable);
        }

        model.addAttribute("categoryPage", resultPage);
        int totalPages = resultPage.getTotalPages();
        if (totalPages > 0) {
            int start = Math.max(1, currentPage - 2);
            int end = Math.min(currentPage + 2, totalPages);
            model.addAttribute("pageNumbers", 
                java.util.stream.IntStream.rangeClosed(start, end).boxed().toList());
        }

        return "admin/categories/searchpaging";
    }
}
