package com.louye.springlangchaindemo.config;

import com.louye.springlangchaindemo.service.ai.AssistantService;
import com.louye.springlangchaindemo.service.ai.Factory;
import com.louye.springlangchaindemo.tool.AssistantTools;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.time.Duration.ofSeconds;

@Configuration
class AssistantConfiguration {

    @Bean
    AssistantService assistantService(ChatLanguageModel chatLanguageModel, AssistantTools assistantTools) {
        return AiServices.builder(AssistantService.class)
                .chatLanguageModel(chatLanguageModel)
                .chatMemory(MessageWindowChatMemory.builder()
                        .maxMessages(10).build())
                .tools(assistantTools)
                .build();
    }

    @Bean
    Factory extractInfoService() {
        ChatLanguageModel chatLanguageModel = OpenAiChatModel.builder()
                .baseUrl(System.getenv("OPENAI_API_URL"))
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .timeout(ofSeconds(60))
//                .responseFormat("json_object")
                .build();
        return AiServices.create(Factory.class, chatLanguageModel);
    }



}
