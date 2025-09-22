package vn.iotstar.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

class Info {
    private String key;
    private String value;

    public Info(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}

@Controller
public class WebController {

    @GetMapping("/profile")
    public String profile(Model model) {
        // Tạo danh sách thông tin cá nhân
        List<Info> profile = new ArrayList<>();
        profile.add(new Info("Họ và tên", "Trần Hồ Phương Nguyên"));
        profile.add(new Info("Nickname", "Nguyên"));
        profile.add(new Info("Email", "nguyen@example.com"));
        profile.add(new Info("Website", "https://nguyen.dev"));
        profile.add(new Info("Sở thích", "Lập trình, Đọc sách, Du lịch"));

        // Đưa danh sách vào Model
        model.addAttribute("profile", profile);

        // Trả về trang profile.html
        return "profile";  // ✅ templates/profile.html
    }

    @GetMapping("/")
    public String home() {
        return "index";   // ✅ templates/index.html
    }
}
