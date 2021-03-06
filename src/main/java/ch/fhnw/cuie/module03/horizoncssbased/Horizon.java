package ch.fhnw.cuie.module03.horizoncssbased;

import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public class Horizon extends Region {
    private static final double PREFERRED_SIZE = 300;
    private static final double MINIMUM_SIZE   = 75;
    private static final double MAXIMUM_SIZE   = 800;

    //declare all parts
    private Region fixed;
    private Region horizontal;
    private Region vertical;

    // needed for resizing
    private Pane drawingPane;

    public Horizon() {
        initializeSelf();
        initializeParts();
        layoutParts();
    }

    private void initializeSelf() {
        // load stylesheets
        String fonts = getClass().getResource("fonts.css").toExternalForm();
        getStylesheets().add(fonts);

        String stylesheet = getClass().getResource("style.css").toExternalForm();
        getStylesheets().add(stylesheet);

        // initialize resizing constraints
        Insets padding           = getPadding();
        double verticalPadding   = padding.getTop() + padding.getBottom();
        double horizontalPadding = padding.getLeft() + padding.getRight();
        setMinSize(MINIMUM_SIZE + horizontalPadding, MINIMUM_SIZE + verticalPadding);
        setPrefSize(PREFERRED_SIZE + horizontalPadding, PREFERRED_SIZE + verticalPadding);
        setMaxSize(MAXIMUM_SIZE + horizontalPadding, MAXIMUM_SIZE + verticalPadding);
    }

    private void initializeParts() {
        fixed = new Region();
        fixed.getStyleClass().addAll("fixed1");

        horizontal = new Region();
        horizontal.getStyleClass().addAll("horizontal");

        vertical = new Region();
        vertical.getStyleClass().addAll("vertical");

        drawingPane = new Pane();
        drawingPane.getStyleClass().add("horizon");
        drawingPane.setMaxSize(PREFERRED_SIZE, PREFERRED_SIZE);
        drawingPane.setMinSize(PREFERRED_SIZE, PREFERRED_SIZE);
        drawingPane.setPrefSize(PREFERRED_SIZE, PREFERRED_SIZE);
    }

    private void layoutParts() {
        // add all parts to drawingPane
        drawingPane.getChildren().addAll(fixed, horizontal, vertical);

        getChildren().add(drawingPane);
    }

    //resize by scaling
    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        resize();
    }

    private void resize() {
        double width  = getWidth() - getInsets().getLeft() - getInsets().getRight();
        double height = getHeight() - getInsets().getTop() - getInsets().getBottom();
        double size   = Math.max(Math.min(Math.min(width, height), MAXIMUM_SIZE), MINIMUM_SIZE);

        double scalingFactor = size / PREFERRED_SIZE;

        if (width > 0 && height > 0) {
            drawingPane.relocate((getWidth() - PREFERRED_SIZE) * 0.5, (getHeight() - PREFERRED_SIZE) * 0.5);
            drawingPane.setScaleX(scalingFactor);
            drawingPane.setScaleY(scalingFactor);
        }
    }
}
