package br.com.ss.spring.ai.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class SimpleChatController {

    private final ChatClient chatClient;

    public SimpleChatController(ChatClient.Builder builder) {
        this.chatClient = builder
                .build();
    }

    @GetMapping("/itau")
    public String chat() {
        return chatClient.prompt()
                .user("Me conte um fato interessante sobre o banco Itaú")
                .call()
                .content();
    }

    // A simple Result
    @GetMapping("/joke")
    public String joke() {
        return chatClient.prompt()
                .user("Me conte uma piada sobre cachorros")
                .call()
                .content();
    }
    //To get a more information and metadaos
    @GetMapping("/teste")
    public ChatResponse piada() {
        return chatClient.prompt()
                .user("Hello")
                .call()
                .chatResponse();
    }

    //For User With Spring WebFlux
    @GetMapping("/stream")
    public Flux<String> stream (){
        return chatClient.prompt()
                .user("Visitarei São Paulo em breve, me forneça 10 lugares que eu devo visitar?")
                .stream()
                .content();
    }

    @GetMapping("/places")
    public String places (){
        return chatClient.prompt()
                .user("Visitarei São Paulo em breve, me forneça 10 lugares que eu devo visitar?")
                .call()
                .content();
    }




}