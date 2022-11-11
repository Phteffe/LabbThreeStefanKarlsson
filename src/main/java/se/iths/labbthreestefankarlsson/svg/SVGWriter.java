package se.iths.labbthreestefankarlsson.svg;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import se.iths.labbthreestefankarlsson.Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SVGWriter {
    FileChooser fileChooser = new FileChooser();

    public void saveToFile(Model model) {

        newFileChooser();

        Path path = Path.of(fileChooser.showSaveDialog(new Stage()).toURI());

        List<String> svgStrings = new ArrayList<>();

        buildSvgString(model,svgStrings);

        try {
            Files.write(path,svgStrings);
        }catch (IOException e){
            e.printStackTrace();
        }


    }


    private void newFileChooser(){
        fileChooser.setTitle("Title");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SVGFiles","*.svg"));
    }

    private void buildSvgString(Model model, List<String> svgStrings) {
        svgStrings.add(headerOfSvgString());
        model.getShapes().forEach(shape -> svgStrings.add(shape.writeSvg()));
        svgStrings.add(svgEnding());
    }

    private String headerOfSvgString() {
        return String.join(" ",
                "<svg",
                "xmlns=\"http://www.w3.org/2000/svg\"",
                "version=\"1.1\"",
                "width=\"600.0\"",
                "height=\"332.0\">"
        );
    }

    private String svgEnding() {return "</svg>";}
}
