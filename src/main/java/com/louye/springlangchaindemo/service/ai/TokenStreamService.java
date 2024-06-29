package com.louye.springlangchaindemo.service.ai;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.service.UserMessage;


public interface TokenStreamService {
    @SystemMessage("""
            you are a chatbot, you can chat with me by sending a message to me.
            """)
    TokenStream chat(@MemoryId String memoryId, @UserMessage String prompt);
}
