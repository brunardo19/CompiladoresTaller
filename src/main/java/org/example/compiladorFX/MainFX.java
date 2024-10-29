package org.example.compiladorFX;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.gui.TreeViewer;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Map;

public class MainFX extends Application {

    private TextArea codeTextArea;
    private TextArea semanticErrorTextArea;
    private TextArea parserErrorTextArea;
    private SwingNode swingNodeForTree;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Interprete");

        // Source Code Editor
        codeTextArea = new TextArea();
        codeTextArea.setPromptText("Escribir codigo aqui...");

        // Parser Error Area
        parserErrorTextArea = new TextArea();
        parserErrorTextArea.setEditable(false);

        // Semantic Error and Symbol Table Area
        semanticErrorTextArea = new TextArea();
        semanticErrorTextArea.setEditable(false);

        // Buttons
        Button compileButton = new Button("Compilar");
        compileButton.setOnAction(e -> compileCode());

        Button loadButton = new Button("Cargar Archivo");
        loadButton.setOnAction(e -> loadFile());

        // Layout
        HBox buttonBox = new HBox(10, loadButton, compileButton);
        buttonBox.setPadding(new Insets(10));

        VBox topBox = new VBox(10, codeTextArea, buttonBox);
        topBox.setPadding(new Insets(10));


        // Columns for Errors
        GridPane errorGrid = new GridPane();
        errorGrid.setPadding(new Insets(10));
        errorGrid.setHgap(10);  // Spacing between columns

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50); // Each column takes 50% of the width
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        errorGrid.getColumnConstraints().addAll(col1, col2);


        errorGrid.add(new Label("Errores Sintacticos:"), 0, 0); // Column 0, Row 0
        errorGrid.add(parserErrorTextArea, 0, 1);                // Column 0, Row 1

        errorGrid.add(new Label("Errores Semanticos y Tabla de Simbolos:"), 1, 0); // Column 1, Row 0
        errorGrid.add(semanticErrorTextArea, 1, 1);                                // Column 1, Row 1


        // Code + tree grid

        // Create a SwingNode
        swingNodeForTree = new SwingNode();

        ScrollPane treeScrollPane = new ScrollPane(swingNodeForTree); // Wrap in ScrollPane
        treeScrollPane.setPrefWidth(1200); // Set a preferred width
        treeScrollPane.setFitToWidth(true);  // Resize content to fit width

        treeScrollPane.setPannable(true);
        //treeScrollPane.setPrefSize(120, 640);

        VBox treeBox = new VBox(10, new Label("Arbol"), treeScrollPane);

        GridPane topGrid = new GridPane();
        topGrid.setPadding(new Insets(10));
        topGrid.setHgap(10);  // Spacing between columns

        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(50); // Each column takes 50% of the width
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPercentWidth(50);
        topGrid.getColumnConstraints().addAll(col3, col4);

        topGrid.add(new Label("Codigo:"), 0, 0); // Column 0, Row 0
        topGrid.add(topBox, 0, 1);                // Column 0, Row 1

        topGrid.add(new Label("Arbol Sintactico"), 1, 0); // Column 1, Row 0
        topGrid.add(treeScrollPane, 1, 1);                                // Column 1, Row 1


        BorderPane root = new BorderPane();
        root.setTop(topGrid);
        root.setBottom(errorGrid);


        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir archivo");
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                String code = Files.readString(selectedFile.toPath(), StandardCharsets.UTF_8);
                codeTextArea.setText(code);
            } catch (IOException e) {
                semanticErrorTextArea.setText("Error cargando archivo" + e.getMessage());
            }
        }
    }


    private void compileCode() {
        String code = codeTextArea.getText();
        if (code.isEmpty()) {
            String message = "Codigo vacio, no se puede compilar";
            parserErrorTextArea.appendText(message);
            semanticErrorTextArea.appendText(message);
            return;
        }
        semanticErrorTextArea.clear();
        parserErrorTextArea.clear();

        CharStream input = CharStreams.fromString(code);
        gLexer lexer = new gLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        gParser parser = new gParser(tokens);

        // Error Handling
        parser.removeErrorListeners();
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                parserErrorTextArea.appendText("Error sintactico en la linea " + line + ":" + charPositionInLine + " " + msg + "\n");
            }
        });

        ParseTree tree = parser.program();
        MyVisitorFX visitor = new MyVisitorFX("");
        visitor.visit(tree);
        semanticErrorTextArea.appendText(visitor.getTextout());

        if (parser.getNumberOfSyntaxErrors() > 0) {
            parserErrorTextArea.appendText("El analizador sintactico encontro: " + parser.getNumberOfSyntaxErrors() + " errores\n");
        } else {
            semanticErrorTextArea.appendText("\n");
            semanticErrorTextArea.appendText("\n Tabla de Simbolos Global:\n");
            semanticErrorTextArea.appendText("-----------------------------------------------\n");
            for (Map.Entry<String, MyVisitorFX.Symbol> entry : visitor.symbolTableGlobal.entrySet()) {
                semanticErrorTextArea.appendText(entry.getKey() + ": " + entry.getValue() + "\n");
            }
            semanticErrorTextArea.appendText("-----------------------------------------------\n");
            parserErrorTextArea.appendText("No hay errores en el analizador sintactico\n");
        }

        Platform.runLater(() -> {
            // Set content into SwingNode
            TreeViewer viewer = new TreeViewer(Arrays.asList(parser.getRuleNames()), tree);

            // Set a preferred size for the TreeViewer component to ensure it triggers scrolling
            viewer.setScale(1); // Optional, scales the tree for better visibility

            JPanel panel = new JPanel();

            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

            panel.add(viewer);
            panel.setPreferredSize(viewer.getPreferredSize());
            swingNodeForTree.setContent(scrollPane);
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}