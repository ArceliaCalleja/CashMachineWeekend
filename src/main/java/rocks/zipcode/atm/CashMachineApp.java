package rocks.zipcode.atm;

import javafx.scene.control.Label;
import rocks.zipcode.atm.bank.Bank;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;

import javax.xml.soap.Text;
import java.util.Scanner;

/**
 * @author ZipCodeWilmington
 */
public class CashMachineApp extends Application {

    private TextField field = new TextField();
    private CashMachine cashMachine = new CashMachine(new Bank());

    private Parent createContent() {
        VBox vbox = new VBox(10);
        vbox.setPrefSize(600, 600);

        TextArea areaInfo = new TextArea();

        TextField idText=new TextField();
        TextField nameText=new TextField();
        TextField emailText=new TextField();
        TextField balanceText=new TextField();

        Label idLabel=new Label("ID: ");
        Label nameLabel=new Label ("Name : ");
        Label emailLabel=new Label("Email: ");
        Label balanceLabel=new Label("Balance: ");


        VBox accountPane=new VBox();

        accountPane.getChildren().add(idLabel);
        accountPane.getChildren().add(idText);

        accountPane.getChildren().add(nameLabel);
        accountPane.getChildren().add(nameText);

        accountPane.getChildren().add(emailLabel);
        accountPane.getChildren().add(emailText);

        accountPane.getChildren().add(balanceLabel);
        accountPane.getChildren().add(balanceText);


        Button btnSubmit = new Button("Set Account ID");
        Button btnDeposit = new Button("Deposit");
        Button btnWithdraw = new Button("Withdraw");
        Button btnExit = new Button("Exit");


        btnSubmit.setOnAction(e -> {
            int id = Integer.parseInt(field.getText());
            cashMachine.login(id);
            if (cashMachine.getAccountData()==null){
                System.out.println("Ya Mamorgan");
                btnDeposit.setDisable(true);
                btnWithdraw.setDisable(true);
                btnExit.setDisable(true);

            } else {
                btnDeposit.setDisable(false);
                btnWithdraw.setDisable(false);
                btnExit.setDisable(false);
                idText.setText(String.valueOf(cashMachine.getAccountData().getId()));
                nameText.setText(cashMachine.getAccountData().getName());
                nameText.setStyle("-fx-text-inner-color: blue;");
                emailText.setText(cashMachine.getAccountData().getEmail());
                balanceText.setText(String.valueOf(cashMachine.getAccountData().getBalance()));
            }
            areaInfo.setText(cashMachine.toString());
        });


        btnDeposit.setOnAction(e -> {
            int amount = Integer.parseInt(field.getText());
            cashMachine.deposit(amount);

            areaInfo.setText(cashMachine.toString());
        });


        btnWithdraw.setOnAction(e -> {
            int amount = Integer.parseInt(field.getText());
            cashMachine.withdraw(amount);

            areaInfo.setText(cashMachine.toString());
        });


        btnExit.setOnAction(e -> {
            cashMachine.exit();

            areaInfo.setText(cashMachine.toString());
        });

        FlowPane flowpane = new FlowPane();

        flowpane.getChildren().add(btnSubmit);
        flowpane.getChildren().add(btnDeposit);
        flowpane.getChildren().add(btnWithdraw);
        flowpane.getChildren().add(btnExit);
        vbox.getChildren().addAll(field, flowpane, areaInfo);
        vbox.getChildren().add(accountPane);
        return vbox;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
