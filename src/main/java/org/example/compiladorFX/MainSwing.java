package org.example.compiladorFX;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.gui.TreeViewer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Map;

public class MainSwing extends JFrame {

    private JTextArea codeTextArea;
    private JTextArea semanticErrorTextArea;
    private JTextArea parserErrorTextArea;
    private JPanel treePanel;


    public MainSwing() {
        setTitle("Interprete");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800); // Increased size for better visibility

        // Source Code Editor
        codeTextArea = new JTextArea();
        codeTextArea.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding


        // Parser Error Area
        parserErrorTextArea = new JTextArea();
        parserErrorTextArea.setEditable(false);


        // Semantic Error and Symbol Table Area
        semanticErrorTextArea = new JTextArea();
        semanticErrorTextArea.setEditable(false);

        // Buttons
        JButton compileButton = new JButton("Compilar");
        compileButton.addActionListener(e -> compileCode());

        JButton loadButton = new JButton("Cargar Archivo");
        loadButton.addActionListener(e -> loadFile());

        //BorderLayout for better organization
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Align buttons to the left
        buttonPanel.add(loadButton);
        buttonPanel.add(compileButton);

        topPanel.add(new JScrollPane(codeTextArea), BorderLayout.CENTER); // Add scroll to codeTextArea
        topPanel.add(buttonPanel, BorderLayout.SOUTH);


        JPanel errorPanel = new JPanel(new GridLayout(1, 2));
        errorPanel.add(new JScrollPane(parserErrorTextArea));
        errorPanel.add(new JScrollPane(semanticErrorTextArea));


        treePanel = new JPanel(); // Initialize the tree panel

        JSplitPane topSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, topPanel, treePanel);
        topSplitPane.setResizeWeight(0.5);

        JSplitPane mainSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topSplitPane, errorPanel);
        mainSplitPane.setResizeWeight(0.7);

        add(mainSplitPane);
        setVisible(true);
    }



    private void loadFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Abrir archivo");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt")); //Optional file filter
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
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
            parserErrorTextArea.append(message);
            semanticErrorTextArea.append(message);
            return;
        }
        semanticErrorTextArea.setText(""); // Se usa setText para borrar
        parserErrorTextArea.setText("");

        CharStream input = CharStreams.fromString(code);
        gLexer lexer = new gLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        gParser parser = new gParser(tokens);

        // Error Handling
        parser.removeErrorListeners();
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                parserErrorTextArea.append("Error sintactico en la linea " + line + ":" + charPositionInLine + " " + msg + "\n");
            }
        });

        ParseTree tree = parser.program();
        MyVisitorFX visitor = new MyVisitorFX("");
        visitor.visit(tree);
        semanticErrorTextArea.append(visitor.getTextout());



        if (parser.getNumberOfSyntaxErrors() > 0) {
            parserErrorTextArea.append("El analizador sintactico encontro: " + parser.getNumberOfSyntaxErrors() + " errores\n");
        } else {
            semanticErrorTextArea.append("\n");
            semanticErrorTextArea.append("\n Tabla de Simbolos Global:\n");
            semanticErrorTextArea.append("-----------------------------------------------\n");
            for (Map.Entry<String, MyVisitorFX.Symbol> entry : visitor.symbolTableGlobal.entrySet()) {
                semanticErrorTextArea.append(entry.getKey() + ": " + entry.getValue() + "\n");
            }
            semanticErrorTextArea.append("-----------------------------------------------\n");
            parserErrorTextArea.append("No hay errores en el analizador sintactico\n");
        }



        TreeViewer viewer = new TreeViewer(Arrays.asList(parser.getRuleNames()), tree);

        treePanel.removeAll();
        JScrollPane treeScrollPane = new JScrollPane(viewer); // Wrap in ScrollPane
        treeScrollPane.setPreferredSize(new Dimension(treePanel.getWidth(), treePanel.getHeight()));
        treePanel.add(treeScrollPane);
        treePanel.revalidate();
        treePanel.repaint();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainSwing());
    }
}