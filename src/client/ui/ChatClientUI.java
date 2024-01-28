package client.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChatClientUI extends Application {
    private TextArea messageArea;
    private TextField messageInput;
    private TextField usernameInput;
    private ChatClient chatClient;

    @Override
    public void start(Stage primaryStage) throws Exception {
        chatClient = new ChatClient(this::onMessageReceived);
        chatClient.connectToServer("localhost", 12345); // Replace with your server's address and port

        // Set up UI Components
        usernameInput = new TextField();
        messageInput = new TextField();
        Button sendButton = new Button("Send");
        messageArea = new TextArea();

        sendButton.setOnAction(event -> {
            String message = usernameInput.getText() + ": " + messageInput.getText();
            chatClient.sendMessage(message);
            messageInput.clear();
        });

        VBox layout = new VBox(10, new Label("Username:"), usernameInput, new Label("Message:"), messageInput, sendButton, new Label("Chat:"), messageArea);
        Scene scene = new Scene(layout, 400, 500);

        primaryStage.setTitle("Chat Client");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void onMessageReceived(String message) {
        // Update UI with the received message
        messageArea.appendText(message + "\n");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
