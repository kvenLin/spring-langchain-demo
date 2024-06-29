package com.louye.springlangchaindemo.service.ai;

import dev.langchain4j.service.SystemMessage;

public interface AssistantService {

    @SystemMessage("""
        you are system assistant, you can help me to do some works in this system.
        if user want to generate table data, must input the table name and the number of rows.
        """)
    String chat(String message);
}
