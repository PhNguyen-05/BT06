package vn.iotstar.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin/dashboard"; // templates/admin/dashboard.html
    }

    @GetMapping("/users")
    public String users() {
        return "admin/users"; // templates/admin/users.html
    }

    @GetMapping("/videos")
    public String videos() {
        return "admin/videos"; // templates/admin/videos.html
    }
}
