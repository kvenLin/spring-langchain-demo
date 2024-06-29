package com.louye.springlangchaindemo.config;

import com.louye.springlangchaindemo.service.ai.AssistantService;
import com.louye.springlangchaindemo.service.ai.DataBaseQueryService;
import com.louye.springlangchaindemo.service.ai.Factory;
import com.louye.springlangchaindemo.service.ai.TokenStreamService;
import com.louye.springlangchaindemo.tool.AssistantTools;
import dev.langchain4j.experimental.rag.content.retriever.sql.SqlDatabaseContentRetriever;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static java.time.Duration.ofSeconds;

@Configuration
class AssistantConfiguration {

    @Value("${langchain4j.open-ai.streaming-chat-model.model-name}")
    private String modelName;


    @Value("${langchain4j.open-ai.streaming-chat-model.base-url}")
    private String baseUrl;

    @Value("${langchain4j.open-ai.streaming-chat-model.api-key}")
    private String apiKey;


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
    TokenStreamService tokenStreamService(AssistantTools assistantTools) {
        OpenAiStreamingChatModel.OpenAiStreamingChatModelBuilder builder = OpenAiStreamingChatModel
                .builder()
                .modelName(modelName)
                .baseUrl(baseUrl)
                .apiKey(apiKey)
                .timeout(Duration.of(60, ChronoUnit.SECONDS));
        OpenAiStreamingChatModel openAiStreamingChatModel = builder.build();
        return AiServices.builder(TokenStreamService.class)
                .streamingChatLanguageModel(openAiStreamingChatModel)
                .chatMemoryProvider(
                        sessionId -> MessageWindowChatMemory.withMaxMessages(20))
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

    @Bean
    DataBaseQueryService dataBaseQueryService(DataSource dataSource, ChatLanguageModel chatLanguageModel) {
        ContentRetriever contentRetriever = SqlDatabaseContentRetriever.builder()
                .dataSource(dataSource)
                .chatLanguageModel(chatLanguageModel)
                .build();
        return AiServices.builder(DataBaseQueryService.class)
                .chatLanguageModel(chatLanguageModel)
                .contentRetriever(contentRetriever)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                .build();
    }

}
