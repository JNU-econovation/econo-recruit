package com.econovation.recruit.api.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SwaggerUiForwardController {

    @GetMapping("/swagger-ui/{filename:.+}")
    public String forwardSwaggerUiAsset(@PathVariable String filename) {
        return "forward:/webjars/swagger-ui/" + filename;
    }
}
