module Juego_del_Pinguino {
    requires javafx.controls;
    requires javafx.fxml;

    exports Controlador;
    exports Modelo;
    exports Vista;
    opens Vista to javafx.fxml;
}