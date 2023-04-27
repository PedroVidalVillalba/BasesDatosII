package gui.chatGPT;



import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;


public class ChatController implements Initializable{
    @FXML
    private TextField myTextField;
    @FXML
    private TextArea myTextArea;
    @FXML
    private Text texto;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        texto.setVisible(false);
        myTextArea.setWrapText(true);
        }//mensaje de error

    public void consultaCHAT() throws Exception {
        // Configura la clave de API de OpenAI
        String token = "";
        StringBuilder resultado = new StringBuilder();
        String resultado2;
        OpenAiService service = new OpenAiService(token);


        texto.setVisible(true);
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

        texto.setVisible(false);

    }
}


