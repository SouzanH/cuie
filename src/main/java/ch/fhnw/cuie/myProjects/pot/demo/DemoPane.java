package ch.fhnw.cuie.myProjects.pot.demo;

import ch.fhnw.cuie.myProjects.pot.PotHeightControl;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * @author Viviane Bendjus
 */
public class DemoPane extends BorderPane {
    private PotHeightControl customControl;

    private TextField   valueField;
    private CheckBox    timerRunningBox;
    private Slider      pulseSlider;
    private ColorPicker colorPicker;

    public DemoPane() {
        initializeControls();
        layoutControls();
        addBindings();
    }

    private void initializeControls() {
        setPadding(new Insets(10));

        customControl = new PotHeightControl();

        valueField = new TextField();
        valueField.setText("Burj Kalifa");

        timerRunningBox = new CheckBox("Timer running");
        timerRunningBox.setSelected(false);

        pulseSlider = new Slider(0.5, 2.0, 1.0);
        pulseSlider.setShowTickLabels(true);
        pulseSlider.setShowTickMarks(true);

        colorPicker = new ColorPicker();
    }

    private void layoutControls() {
        setCenter(customControl);
        VBox box = new VBox(10, new Label("Control Properties"), valueField, timerRunningBox, pulseSlider, colorPicker);
        box.setPadding(new Insets(10));
        box.setSpacing(10);
        setRight(box);
    }

    private void addBindings() {
        customControl.valueProperty().bindBidirectional(valueField.textProperty());

        customControl.timerIsRunningProperty().bindBidirectional(timerRunningBox.selectedProperty());
        customControl.pulseProperty().bind(Bindings.createObjectBinding(() -> Duration.seconds(pulseSlider.getValue()), pulseSlider.valueProperty()));

        colorPicker.valueProperty().bindBidirectional(customControl.baseColorProperty());
    }

}