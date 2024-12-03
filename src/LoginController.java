import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class LoginController {
    @FXML
    private Button close;

    @FXML
    private Button loginBtn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    // DTATBASE CONNECTION

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public void loginAdmin(){
        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";

        connect = Database.connectDb();
        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username.getText());
            prepare.setString(2, password.getText());
            Alert alert;

            if(username.getText().isEmpty() || password.getText().isEmpty()){
                alert=new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
            }else{
                if (result.next()) {
                    alert=new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Succesfully Login"); 

                    loginBtn.getScene().getWindow().hide();
                    Parent root=FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
                    Stage stage=new Stage();
                    Scene scene =new Scene(root);

                    stage.setScene(scene);
                    stage.show();
                }else{
                    alert=new Alert(AlertType.INFORMATION);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username / Password"); 
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public void close(){
        System.exit(0);
    }
}
