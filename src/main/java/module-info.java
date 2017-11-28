module goldenglue.main {
    requires javafx.graphics;
    requires javafx.base;
    requires javafx.controls;
    requires jackson.databind;
    requires jackson.core;
    requires jackson.annotations;
    requires log4j.api;
    exports org.goldenglue.game;
    exports org.goldenglue.server;
    exports org.goldenglue.server.gamelogic;
}