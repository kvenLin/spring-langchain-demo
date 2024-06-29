package com.louye.springlangchaindemo.controller;

import com.louye.springlangchaindemo.service.ProductService;
import com.louye.springlangchaindemo.service.UserService;
import com.louye.springlangchaindemo.service.ai.AssistantService;
import com.louye.springlangchaindemo.service.ai.DataBaseQueryService;
import com.louye.springlangchaindemo.service.ai.Factory;
import com.louye.springlangchaindemo.service.ai.TokenStreamService;
import com.louye.springlangchaindemo.tool.AssistantTools;
import com.louye.springlangchaindemo.tool.SseEmitterUTF8;
import dev.langchain4j.service.TokenStream;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private AssistantTools assistantTools;

    @Resource
    private UserService userService;
    @Resource
    private Factory factory;
    @Resource
    private ProductService productService;
    @Resource
    private DataBaseQueryService dataBaseQueryService;
    @Resource
    private AssistantService assistantService;
    @Resource
    private TokenStreamService tokenStreamService;

    @PostMapping("/test")
    public String test() {
        return "test success";
    }

    @PostMapping("login")
    public String login(String username, String password) {
        return userService.login(username, password);
    }

    @GetMapping("showTableDDL")
    public String showTableDDL(String tableName) {
        return userService.showTableDDL(tableName);
    }
    @GetMapping("generate")
    public String generate() {
//        String tableStructure = "create table  product\n" +
//                "(\n" +
//                "    id          int primary key,\n" +
//                "    name        varchar(50) comment '商品名称',\n" +
//                "    price       decimal(10, 2) comment '商品价格',\n" +
//                "    description text comment '商品描述',\n" +
//                "    creat_time  datetime comment '创建时间',\n" +
//                "    update_time datetime comment '更新时间'\n" +
//                ") auto_increment = 10000 comment '商品表';";
//
//        TableDataGeneratePrompt tableDataGeneratePrompt = new TableDataGeneratePrompt();
//        tableDataGeneratePrompt.setTableName("product");d
////        tableDataGeneratePrompt.setTableStructure(tableStructure);
//        List<Product> products = factory.generateTestDataForProduct(tableDataGeneratePrompt);
//        log.info("生成测试数据成功，数据如下：{}", products);
//        productService.saveBatch(products);
//        return String.format("生成测试数据成功，数据如下：{}", products);
        return "生成测试数据成功";
    }

    @GetMapping("queryData")
    public String queryData(String message) {
        return dataBaseQueryService.answer(message);
    }

    //text/event-stream
    @GetMapping(value = "test/{clientId}", produces = "text/event-stream;charset=UTF-8")
    public SseEmitter test(@PathVariable("clientId") String clientId, @RequestParam(value = "message") String message) {
        final SseEmitterUTF8 emitter = new SseEmitterUTF8(0L);
        try {
            TokenStream tokenStream = tokenStreamService.chat(clientId, message);
            tokenStream.onNext((content) -> {
                try {
                    log.info("receive message: {}", content);
                    emitter.send(content);
                } catch (IOException e) {
                    log.error("send message error", e);
                    throw new RuntimeException(e);
                }
            }).onComplete((content) -> {
                log.info("complete");
                try {
                    emitter.send("complete");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).onError((e) -> {
                log.error("error", e);
                try {
                    emitter.send("error");
                } catch (IOException e1) {
                    throw new RuntimeException(e1);
                }
            }).start();
            emitter.send("connect success");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return emitter;
    }



    @GetMapping(value = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter sseStream() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE); // 设置为 Long.MAX_VALUE 表示永不超时

        // 可以在一个单独的线程中发送数据
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    emitter.send(SseEmitter.event()
                            .data("Hello, SSE! " + i)
                            .name("message"));
                    TimeUnit.SECONDS.sleep(1); // 每秒发送一次数据
                }
                emitter.completeWithError(new IOException("Completed with error")); // 完成并发送一个错误
            } catch (IOException | InterruptedException e) {
                emitter.completeWithError(e); // 发送异常
            }
        }).start();

        return emitter;
    }
}
