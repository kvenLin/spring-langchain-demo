package com.louye.springlangchaindemo.template;

import dev.langchain4j.model.input.structured.StructuredPrompt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@StructuredPrompt("""
        generate data for table_name: {{tableName}}.
        test number: {{num}}
        data id must be bigger than {{maxId}}, but not too big.
        """)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableDataGeneratePrompt {
    private String tableName;
    private Integer num;
    private Integer maxId;
}
