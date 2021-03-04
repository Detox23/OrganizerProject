package com.example.src.dtos;

import com.example.src.entities.EmailTemplateType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailTemplateForCreation {
    private EmailTemplateType type;
    private String content;
}
