package br.com.ss.spring.ai.rag;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModelsController {

    private final ChatClient chatClient;

    public ModelsController(ChatClient.Builder builder, VectorStore vectorStore) {
        this.chatClient = builder
                .defaultAdvisors(new QuestionAnswerAdvisor(vectorStore))
                .build();
    }

    //Imagine a Cenario to Conect in Database e Create a Response About products, share, market etc"
    @GetMapping("/rag/models")
    public Models faq(@RequestParam(value = "message", defaultValue = "Dê-me uma lista de todos os modelos do OpenAI junto com sua janela de contexto") String messsage) {
        return chatClient.prompt()
                .user(messsage)
                .call()
                .entity(Models.class);
    }
}
