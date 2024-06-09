package com.louye.springlangchaindemo.controller;

import com.louye.springlangchaindemo.tool.AssistantTools;
import com.louye.springlangchaindemo.service.ai.AssistantService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequestMapping("/assistant")
@RestController
class AssistantController {

    @Resource
    private AssistantService assistant;

    @Resource
    private AssistantTools assistantTools;


    @GetMapping("/chat")
    public String chat(@RequestParam(value = "message", defaultValue = "What is the time now?") String message) {
        log.info("AssistantController.chat() called with message: {}", message);
        return assistant.chat(message);
    }
}
