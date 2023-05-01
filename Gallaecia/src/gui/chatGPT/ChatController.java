package gui.chatGPT;



import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Clase controlador del patrón Modelo-Vista-Controlador. Tiene asociada una vista del mismo nombre
 * Controla la vista de consulta a ChatGPT utilizando una API
 */
public class ChatController {
    @FXML
    private TextField myTextField;
    @FXML
    private TextArea myTextArea;

    /**
     * Utilizando la API de OpenAI, escribe una consulta a chatGPT y muestra su respuesta con un límite de 75 tokens
     */
    public void consultaCHAT() {
        // Configura la clave de API de OpenAI
        String token = "";
        StringBuilder resultado = new StringBuilder();
        String resultado2;
        OpenAiService service = new OpenAiService(token);

        myTextArea.setPromptText("Streaming chat completion...");
        final List<ChatMessage> messages = new ArrayList<>();
        final ChatMessage systemMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(), myTextField.getText());
        messages.add(systemMessage);
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model("gpt-3.5-turbo")
                .messages(messages)
                .n(1)
                .maxTokens(75)
                .logitBias(new HashMap<>())
                .build();

        for(ChatCompletionChoice completion: service.createChatCompletion(chatCompletionRequest).getChoices()){
            resultado.append(completion.getMessage());
        }

        resultado2=resultado.toString().substring(36);//Cortamos cosas innecesarias

        System.out.println(resultado2);
        myTextArea.setText(resultado2);
        myTextArea.setPromptText("");
    }
}


