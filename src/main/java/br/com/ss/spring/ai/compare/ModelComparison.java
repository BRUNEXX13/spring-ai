package br.com.ss.spring.ai.compare;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModelComparison {

    private final ChatClient chatClient;

    public ModelComparison(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/models")
    public String models(){
        return chatClient.prompt()
                .user("Você pode me dar uma  lista dos modelos de linguagem de grande porte (LLMs) populares até a última atualização ?")
                .call()
                .content();
    }

    // Add a prompt with your information // Static Information
    @GetMapping("/models/prompt")
    public String prompt(){
          var system = """
                    Se lhe perguntarem sobre modelos de linguagem atualizados e sua janela de contexto, aqui estão algumas informações para ajudar você com sua resposta:
                [
                  { "company": "OpenAI",        "model": "GPT-4o",                 "context_window_size": 128000 },
                  { "company": "OpenAI",        "model": "o1-preview",             "context_window_size": 128000 },
                
                  { "company": "Anthropic",     "model": "Claude Opus 4",          "context_window_size": 200000 },
                  { "company": "Anthropic",     "model": "Claude Sonnet 4",        "context_window_size": 200000 },
                
                  { "company": "Google",        "model": "Gemini 2.5 Pro",         "context_window_size": 1000000 },
                  { "company": "Google",        "model": "Gemini 2.0 Pro (Exp.)",  "context_window_size": 2000000 },
                  { "company": "Google",        "model": "Gemini 2.0 Flash",       "context_window_size": 1000000 },
                
                  { "company": "Meta AI",       "model": "Llama 3.1 405B",         "context_window_size": 128000 },
                
                  { "company": "xAI",           "model": "Grok 3",                 "context_window_size": 1000000 },
                
                  { "company": "Mistral AI",    "model": "Mistral Large 2",        "context_window_size": 128000 },
                
                  { "company": "Alibaba Cloud", "model": "Qwen 2.5 72B",           "context_window_size": 128000 },
                
                  { "company": "DeepSeek",      "model": "DeepSeek R1",            "context_window_size": 128000 }
                ]
                """;
          return chatClient.prompt()
                  .user("Can you give me an up to date list of popular large language models and their current context window size")
                  .system(system)
                  .call()
                  .content();
    }



}
