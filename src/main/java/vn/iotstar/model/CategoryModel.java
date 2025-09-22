package vn.iotstar.model;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message = "Không được bỏ trống")
    private String name;

    private String images;

    private int status;

    // 👉 Cờ để phân biệt Add hay Edit
    private Boolean isEdit = false;
}
