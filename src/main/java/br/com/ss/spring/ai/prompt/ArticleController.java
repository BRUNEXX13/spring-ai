package br.com.ss.spring.ai.prompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {

    private final ChatClient chatClient;

    public ArticleController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/posts/new")
    public String newPost(@RequestParam(value = "topic", defaultValue = "JDK Virtual Threads") String topic) {

// Uma mensagem de sistema em LLMs é um tipo especial de entrada que fornece instruções de alto nível,
// contexto ou diretrizes comportamentais para o modelo antes de processar as consultas do usuário.
// Pense nela como as instruções "nos bastidores" que moldam como a IA deve responder.
// Use-a como um guia ou uma restrição ao comportamento do modelo

        var system = """
                                Diretrizes do Gerador de Postagens de Blog:
                
                1. Extensão e Objetivo: Gere postagens de blog de 500 palavras que informem e envolvam o público em geral.
                
                2. Estrutura:
                - Introdução: Prenda a atenção dos leitores e estabeleça a relevância do tópico
                - Corpo: Desenvolva 3 pontos principais com evidências e exemplos
                - Conclusão: Resuma os principais pontos e inclua uma chamada para ação
                
                3. Requisitos de Conteúdo:
                - Inclua aplicações reais ou estudos de caso
                - Incorpore estatísticas ou dados relevantes quando apropriado
                - Explique os benefícios/implicações claramente para leigos
                
                4. Tom e Estilo:
                - Escreva em um tom informativo, porém coloquial
                - Use linguagem acessível, mantendo a autoridade
                - Divida o texto em subtítulos e parágrafos curtos
                
                5. Formato da Resposta: Entregue postagens completas e prontas para publicação com um título sugerido.
                """;

        return chatClient.prompt()
                .system(system)
                .user(u -> {
                    u.text("Escreva-me um post de blog sobre {topic}");
                    u.param("topic", topic);
                })
                .call()
                .content();
    }

}