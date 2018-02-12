import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


/**
* Graphical user interface for Banking application
*
* Created by Dayan
* Last Updated:
*/
public class Assignment4 extends Application {
  private Customer customer = new Customer("Dayan", 12231);
  private BankAccount bankAccount = new BankAccount(customer);
  private double newDeposit = 0;
  private double newWithdrawl = 0;
  private double accountBalance = bankAccount.getBalance();

  //Main method
  public static void main(String[] args) {
    launch(args);
  }

  public void start(Stage primaryStage) {
    StackPane root = new StackPane();

    //Vbox containing all hboxes
    VBox vBox = new VBox(30);

    //Hbox containing the balance and a label for it
    HBox balanceBox = new HBox(10);
    Label statBalance = new Label("Balance: " + accountBalance);
    balanceBox.getChildren().add(statBalance);

    //Hbox containing Customer info
    HBox accountHolderBox = new HBox(10);
    Label accountHolderName = new Label("Account Holder Name: " + customer.getName());
    Label accountHolderID = new Label("Account ID: " + customer.getID());
    accountHolderBox.getChildren().addAll(accountHolderName, accountHolderID);

    //HBox Containing Buttons for withdrawl and deposit
    HBox buttons = new HBox();
    Button withdrawl = new Button("Withdrawl");
    Button deposit = new Button("Deposit");


    //Vbox Containing the user input area
    VBox changeInMoney = new VBox();
    TextField entry = new TextField("Enter Withdrawl or Deposit Amount");
    entry.setPrefWidth(100);
    changeInMoney.getChildren().add(entry);

    //Create actions for buttons
    HandleButtonClick depositAction = new HandleButtonClick(statBalance, entry,
                                                            bankAccount, 1);
    deposit.setOnAction(depositAction);


    HandleButtonClick withdrawlAction = new HandleButtonClick(statBalance, entry,
                                                              bankAccount, -1);
    withdrawl.setOnAction(withdrawlAction);


    buttons.getChildren().addAll(withdrawl, deposit);

    vBox.getChildren().addAll(accountHolderBox, balanceBox, buttons, changeInMoney);
    root.getChildren().add(vBox);
    Scene scene = new Scene(root, 500, 300);

    primaryStage.setTitle("BankAccount");
    primaryStage.setScene(scene);
    primaryStage.show();

  }
}
