package br.com.ss.spring.ai.memory;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
A ChatMemory abstração foi projetada para gerenciar a memória do bate-papo . Ela permite armazenar e recuperar mensagens
relevantes para o contexto da conversa atual.No entanto, não é a melhor opção para armazenar o histórico do bate-papo .
Sevocê precisa manter um registro completo de todas as mensagens trocadas, considere usar uma abordagem diferente,
 como usar o Spring Data para armazenamento e recuperação eficientes do histórico completo do bate-papo
 */
@RestController
public class MemoryController {

    private final ChatClient chatClient;

                             //Adding memory for AI // The models on the web are stateless
    public MemoryController(ChatClient.Builder builder, ChatMemory chatMemory) {
        this.chatClient = builder // With Builder Add Memory
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
    }

    @GetMapping("/memory")
    public String home(@RequestParam String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }

}