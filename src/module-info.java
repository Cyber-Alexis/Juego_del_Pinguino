module Juego_del_Pinguino {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.base;

    exports Controlador;
    exports Modelo;
    exports Vista;
    opens Vista to javafx.fxml;
}