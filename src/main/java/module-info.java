module com.gleb.twittertrends {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;


    opens com.gleb.twittertrends to javafx.fxml;
    exports com.gleb.twittertrends;
    exports com.gleb.twittertrends.Models.Country to com.fasterxml.jackson.databind;
    exports com.gleb.twittertrends.Models.State to com.fasterxml.jackson.databind;
}