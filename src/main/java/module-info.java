module com.example.tellmekeyparser {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
                        
    opens com.example.tellmekeyparser to javafx.fxml;


    exports com.example.tellmekeyparser;


}