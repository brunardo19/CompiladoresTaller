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
import java.util.Objects;

public class MainSwing extends JFrame {

    private final JTextPane codeTextArea;
    private final JTextArea semanticErrorTextArea;
    private final JTextArea parserErrorTextArea;
    private final JTextArea outputTextArea;
    private final CardLayout treeCardLayout;
    private final JPanel cardPanel;
    private final JPanel parseTreePanel;
    private final JPanel semanticTreePanel;
    private final TextLineNumber codeTextNumber;
    private final JButton switchTreeButton;
    private String currentTree = "";

    public MainSwing() {
        setTitle("Interprete");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800); // Tamaño aumentado para mejor visibilidad.

        // Editor de código fuente.
        codeTextArea = new JTextPane();
        codeTextArea.setBorder(new EmptyBorder(10, 10, 10, 10)); // Añade padding.

        // Editor de código fuente.
        codeTextNumber = new TextLineNumber(codeTextArea);

        // Área de errores del parser.
        parserErrorTextArea = new JTextArea();
        parserErrorTextArea.setEditable(false);

        // Área de errores semánticos y tabla de símbolos.
        semanticErrorTextArea = new JTextArea();
        semanticErrorTextArea.setEditable(false);

        // Área de salida.
        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);

        // Botones.
        JButton compileButton = new JButton("Compilar");
        compileButton.addActionListener(e -> compileCode()); // Listener para el botón de compilación.

        JButton loadButton = new JButton("Cargar Archivo");
        loadButton.addActionListener(e -> loadFile()); // Listener para el botón de carga de archivo.

        switchTreeButton = new JButton("Ver Árbol Semántico");
        switchTreeButton.addActionListener(e -> switchTree());

        // Diseño BorderLayout
        JPanel topPanel = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Alinea los botones a la izquierda.
        buttonPanel.add(loadButton);
        buttonPanel.add(compileButton);
        buttonPanel.add(switchTreeButton);

        JScrollPane codeScroll = new JScrollPane(codeTextArea);

        codeScroll.setRowHeaderView(codeTextNumber);

        topPanel.add(codeScroll, BorderLayout.CENTER); // Añade scroll al editor de código.
        topPanel.add(buttonPanel, BorderLayout.SOUTH); // Panel de botones en la parte inferior.

        JPanel errorPanel = new JPanel(new GridLayout(1, 3)); // GridLayout de 1 filas y 3 columnas.
        errorPanel.add(new JScrollPane(parserErrorTextArea));
        errorPanel.add(new JScrollPane(semanticErrorTextArea));
        errorPanel.add(new JScrollPane(outputTextArea));

        JPanel errorLabelPanel = new JPanel(new GridLayout(1, 3)); // GridLayout de 1 filas y 3 columnas.
        errorLabelPanel.add(new JLabel("Errores sintácticos"));
        errorLabelPanel.add(new JLabel("Acciones semánticas/Tabla de simbolos"));
        errorLabelPanel.add(new JLabel("Salida"));

        semanticTreePanel = new JPanel();
        parseTreePanel = new JPanel();

        treeCardLayout = new CardLayout();
        cardPanel = new JPanel(treeCardLayout);

        cardPanel.add(semanticTreePanel, "Semantic Tree");  // "Semantic Tree" is the identifier
        cardPanel.add(parseTreePanel, "Parse Tree");        // "Parse Tree" is the identifie


        // Divide la parte superior horizontalmente para el editor de código y el árbol.
        JSplitPane topSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, topPanel, cardPanel);
        topSplitPane.setResizeWeight(0.5); // Proporción de espacio entre el editor y el árbol.

        // Divide la ventana verticalmente para la parte superior (editor y árbol) y la parte inferior (errores).
        JSplitPane mainSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topSplitPane, errorPanel);
        mainSplitPane.setResizeWeight(0.7); // Proporción de espacio entre la parte superior e inferior.


        add(mainSplitPane);
        setVisible(true);
    }

    private void loadFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Abrir archivo");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt")); // Filtro de archivos opcional.
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) { // Si el usuario selecciona un archivo.
            File selectedFile = fileChooser.getSelectedFile();
            try {
                String code = Files.readString(selectedFile.toPath(), StandardCharsets.UTF_8); // Lee el archivo.
                codeTextArea.setText(code); // Muestra el código en el editor.
            } catch (IOException e) { // Maneja errores de lectura.
                semanticErrorTextArea.setText("Error cargando archivo" + e.getMessage());
            }
        }
    }

    private void switchTree(){
        System.out.println("LOL");
        if (Objects.equals(currentTree, "")){
            semanticErrorTextArea.append("NO HAY CÓDIGO COMPILADO!!!");
        } else if (Objects.equals(currentTree, "Parse Tree")) {
            treeCardLayout.show(cardPanel,"Semantic Tree");
            switchTreeButton.setText("Ver Árbol Sintáctico");
            currentTree = "Semantic Tree";
        } else if (Objects.equals(currentTree, "Semantic Tree")) {
            treeCardLayout.show(cardPanel,"Parse Tree");
            switchTreeButton.setText("Ver Árbol Semántico");
            currentTree = "Parse Tree";
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
        outputTextArea.setText("");

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

        TreeViewer viewer = new TreeViewer(Arrays.asList(parser.getRuleNames()), tree);

        parseTreePanel.removeAll();
        JScrollPane treeScrollPane = new JScrollPane(viewer); // Wrap in ScrollPane
        treeScrollPane.setPreferredSize(new Dimension(parseTreePanel.getWidth(), parseTreePanel.getHeight()));
        parseTreePanel.add(treeScrollPane);
        parseTreePanel.revalidate();
        parseTreePanel.repaint();

        treeCardLayout.show(cardPanel,"Parse Tree");
        currentTree = "Parse Tree";

        if (parser.getNumberOfSyntaxErrors() > 0) {
            parserErrorTextArea.append("El analizador sintactico encontro: " + parser.getNumberOfSyntaxErrors() + " errores\n");
            return;
        }

        MyVisitorFX visitor = new MyVisitorFX("", "");
        visitor.visit(tree);
        semanticErrorTextArea.append(visitor.getErrorOut());
        outputTextArea.append(visitor.getTextOut());

        semanticErrorTextArea.append("\n");
        semanticErrorTextArea.append("\n Tabla de Simbolos Global:\n");
        semanticErrorTextArea.append("-----------------------------------------------\n");
        for (Map.Entry<String, MyVisitorFX.Symbol> entry : visitor.symbolTableGlobal.entrySet()) {
            semanticErrorTextArea.append(entry.getKey() + ": " + entry.getValue() + "\n");
        }
        semanticErrorTextArea.append("-----------------------------------------------\n");
        parserErrorTextArea.append("No hay errores en el analizador sintactico\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainSwing::new);
    }
}