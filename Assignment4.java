import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.Scanner;


/**
* Graphical user interface for Banking application
*
* Created by Dayan
* Last Updated: 12.Feb.2018
*/
public class Assignment4 extends Application {
  private final int VBOX_WIDTH = 30;
  private final int HBOX_WIDTH = 10;
  private final int SCENE_WIDTH = 500; 
  private final int SCENE_LENGTH = 300;
  private final int TEXTFIELD_PREFERRED_WIDTH = 100;
  private final int MATH_SIGN = 1;
  
  private Customer customer;
  private BankAccount bankAccount = new BankAccount(customer);
  private double newDeposit = 0;
  private double newWithdrawl = 0;
  private double accountBalance = bankAccount.getBalance();


  //Added a setCustomer method - Dayan
  public void setUserCustomer() {
    String name;
    int id;
    System.out.println("Enter a customer Name: ");
    Scanner keyName = new Scanner(System.in);
    name = keyName.next();

    System.out.println("Enter a customer ID number: ");
    Scanner keyID = new Scanner(System.in);
    id = keyID.nextInt();

    customer = new Customer(name, id);
  }
  //Main method
  public static void main(String[] args) {
    launch(args);
  }

  public void start(Stage primaryStage) {
    setUserCustomer();

    StackPane root = new StackPane();

    //Vbox containing all hboxes
    VBox vBox = new VBox(VBOX_WIDTH); 

    //Hbox containing the balance and a label for it
    HBox balanceBox = new HBox(HBOX_WIDTH); 
    Label statBalance = new Label("Balance: " + accountBalance);
    balanceBox.getChildren().add(statBalance);

    //Hbox containing Customer info
    HBox accountHolderBox = new HBox(HBOX_WIDTH);
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
    entry.setPrefWidth(TEXTFIELD_PREFERRED_WIDTH); 
    changeInMoney.getChildren().add(entry);

    //Create actions for buttons
    HandleButtonClick depositAction = new HandleButtonClick(statBalance, entry,
                                                            bankAccount, MATH_SIGN); 
    deposit.setOnAction(depositAction);


    HandleButtonClick withdrawlAction = new HandleButtonClick(statBalance, entry,
                                                              bankAccount, -MATH_SIGN);
    withdrawl.setOnAction(withdrawlAction);


    buttons.getChildren().addAll(withdrawl, deposit);

    vBox.getChildren().addAll(accountHolderBox, balanceBox, buttons, changeInMoney);
    root.getChildren().add(vBox);
    Scene scene = new Scene(root, SCENE_WIDTH, SCENE_LENGTH); 

    primaryStage.setTitle("BankAccount");
    primaryStage.setScene(scene);
    primaryStage.show();

  }
}
