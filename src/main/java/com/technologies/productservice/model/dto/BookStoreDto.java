package com.technologies.productservice.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BookStoreDto {

    @NotBlank(message = "Title cannot be blank")
    private String name;

    @NotBlank(message = "Location cannot be blank")
    private String location;
}
