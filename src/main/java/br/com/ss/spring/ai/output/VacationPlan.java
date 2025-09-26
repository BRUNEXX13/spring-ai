package br.com.ss.spring.ai.output;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VacationPlan {

    private final ChatClient chatClient;

    public VacationPlan(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    // To get a result with no Context Data
    @GetMapping("/vacation/unstructured")
    public String vacationUnstructured() {
        return chatClient.prompt()
                .user("Estou querendo viajar para Bahia, Me de uma lista de que coisas para fazer")
                .call()
                .content();
    }

    // To a Data Structured
    @GetMapping("/vacation/structured")
    public Itinerary vacationStructured(@RequestParam(value = "destination", defaultValue = "São Paulo") String destination) {
        return chatClient.prompt()
                .user(u -> {
                    u.text(" Qual é um bom plano de férias enquanto estou em {destination} por 3 dias?");
                    u.param("destination", destination);
                })
                .call()
                .entity(Itinerary.class);
    }

}