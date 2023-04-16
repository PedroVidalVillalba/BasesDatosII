package gui.chatGPT;



import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class ChatController {
    @FXML
    private TextField myTextField;
    @FXML
    private Label myLabel;

    public void consultaCHAT() throws Exception {
        // Configura la clave de API de OpenAI
        String apiKey = "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";
        StringBuilder resultado = new StringBuilder();
        OpenAiService service = new OpenAiService(apiKey);
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(myTextField.getText())
                .model("ada")
                .echo(true)
                .build();


        for(CompletionChoice completion: service.createCompletion(completionRequest).getChoices()){
            resultado.append(completion.getText());
        }
        myLabel.setText(resultado.toString());
    }
}


