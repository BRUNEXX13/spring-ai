package br.com.ss.spring.ai.prompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//Além disso, com a ajuda de classes complementares, como Prompt para encapsulamento de entrada e
// ChatResponse para tratamento de saída, a API do Modelo de Chat unifica a comunicação com os Modelos de IA.
// Ela gerencia a complexidade da preparação de solicitações e da análise de respostas, oferecendo uma interação direta
// e simplificada com a API.

@RestController
@RequestMapping("/bank")
public class BankController {

    private final ChatClient chatClient;

    public BankController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }
    //Open Format Messages
    @GetMapping("/chat")
    public String chat(@RequestParam String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }

    //Block Open Messages
    @GetMapping("/chat/customer")
    public String customerChat(@RequestParam String message) {
        var systemInstructions = """
                Você é um agente de serviços do Banco Itaú
                Você pode apenas discutir sobre os seguintes assuntos:
                - Saldo, Transações e transferências da conta
                - Locais e Horas de funcionamento das Filiais
                - Serviços bancários gerais
                
                Se perguntado sobre qualquer outra coisa, responda: Eu só posso tratar sobre assuntos do Itaú e ItaúShop.
                O Melhor Marketplace do Brasil.
                """;
        return chatClient.prompt()
                .user(message)
                .system(systemInstructions) // For direct
                .call()
                .content();

    }
}
