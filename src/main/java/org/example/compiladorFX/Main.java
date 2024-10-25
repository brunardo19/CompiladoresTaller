package org.example.compiladorFX;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        // Leer el archivo de texto
        String filePath = "test.lang";
        String s = "";
        try {
            s = Files.readString(Paths.get(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return; // Salir si no se puede leer el archivo
        }

        // ----------- ANALIZADOR LEXICO -----------

        CharStream input = CharStreams.fromString(s);
        gLexer lexer = new gLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        gParser parser = new gParser(tokens);

        // ----------- ANALIZADOR SINTACTICO -----------
        // Reescribir error listener
        parser.removeErrorListeners();

        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                System.err.println("Error sintactico en la linea " + line + ":" + charPositionInLine + " " + msg);
            }
        });

        ParseTree tree = parser.program(); // Invoca la regla inicial

        MyVisitor visitor = new MyVisitor();
        visitor.visit(tree);

        if (parser.getNumberOfSyntaxErrors() > 0) {
            System.err.println("El analizador sintactico encontro: " + parser.getNumberOfSyntaxErrors() + " errores");
        } else {
            System.out.println();
            System.out.println("Symbol Table:");
            for (Map.Entry<String, MyVisitor.Symbol> entry : visitor.symbolTable.entrySet()) { // Assuming 'parser' is your parser instance
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            System.out.println("No hay errores en el analizador sintactico"); // Imprime el árbol sintáctico
        }
    }
}