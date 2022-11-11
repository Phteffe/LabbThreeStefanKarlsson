module com.example.labbthreestefankarlsson {
    requires javafx.controls;
    requires javafx.fxml;

    opens se.iths.labbthreestefankarlsson to javafx.fxml;
    exports se.iths.labbthreestefankarlsson;
}