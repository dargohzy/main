package igrad.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class CapPanel extends UiPart<Region> {

    public static final String FXML = "CapPanel.fxml";

    @FXML
    VBox capPanel;
/*
    @FXML
    Label currentCAP;

    @FXML
    Label goalCAP;

    @FXML
    Label targetCAP;

  */

    public CapPanel() {
        super(FXML);

        this.capPanel = new VBox();
    }

    public void showPanels() {

    }

    public void setCAP(String cap) {

    }

    public void setGoalCap(String cap) {

    }

    public void setTargetCap(String cap) {

    }
}
