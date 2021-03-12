import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.math.BigDecimal;

public class Controller {

    @FXML
    private TextField screen;

    private Operation currentOperation;
    private BigDecimal firstNumber;


    @FXML
    private void handleNumberPressed(ActionEvent event){
        Object obj = event.getSource();
        if (obj instanceof Button){
            Button button = (Button) obj;
            String text = button.getText();
            screen.appendText(text);
        }
    }

    @FXML
    private void handleMinusPressed(ActionEvent event){
        changeOperation(Operation.MINUS);
    }

    @FXML
    private void handlePlusPressed(ActionEvent event){
        changeOperation(Operation.PLUS);
    }

    @FXML
    private void handleMultiplyPressed(ActionEvent event){
        changeOperation(Operation.MULTIPLY);
    }

    @FXML
    private void handleDividePressed(ActionEvent event){
        changeOperation(Operation.DIVIDE);
    }


    private void changeOperation(Operation operation){
        currentOperation = operation;
        firstNumber = getNumberFromScreen();
        screen.clear();
    }

    private BigDecimal getNumberFromScreen() {
        String text = screen.getText();
        return new BigDecimal(text);
    }

    @FXML
    private void handleResultPressed(){
        String text = screen.getText();
        BigDecimal secondNumber = new BigDecimal(text);
        BigDecimal result = getResult(secondNumber);
        screen.setText(String.format("%f", result));
    }

    private BigDecimal getResult(BigDecimal secondNumber) {
        try {
            switch (currentOperation) {
                case PLUS:
                    return firstNumber.add(secondNumber);
                case MINUS:
                    return firstNumber.subtract(secondNumber);
                case MULTIPLY:
                    return firstNumber.multiply(secondNumber);
                case DIVIDE:
                    return firstNumber.divide(secondNumber);
            }
        } catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ArithmeticException");
            alert.setHeaderText("error");
            alert.setContentText("ERROR");

            alert.showAndWait();
        }
        return new BigDecimal(0);
    }

    @FXML
    public void handleClearPressed(){
        screen.clear();
    }

    @FXML
    public void handleCommaPressed(){
        String text = screen.getText();
        if (!text.contains(".")){
            screen.appendText(".");
        }
    }

}
