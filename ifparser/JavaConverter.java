package ifparser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class JavaConverter {
    public static void main(String[] args) throws IOException {
        // Read the Java input file
        String inputFilePath = "testcode/InputJavaFile.java";
        CharStream input = CharStreams.fromFileName(inputFilePath);

        // Create the lexer and parser
        Java8Lexer lexer = new Java8Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Java8Parser parser = new Java8Parser(tokens);

        // Create the parse tree
        ParseTree tree = parser.compilationUnit();

        // Create the field name mapping from the CSV file
        FieldNameMapping fieldNameMapping = new FieldNameMapping("testcode/mapping.csv");

        // Create the custom visitor and process the parse tree
        FieldNameChanger fieldNameChanger = new FieldNameChanger(fieldNameMapping, tokens);

        // String originalSourceCode = "your Java source code as a string";
        // FieldNameChanger fieldNameChanger = new FieldNameChanger(fieldNameMapping, tokens, originalSourceCode);
        fieldNameChanger.visit(tree);
        // Output the modified Java code
        String modifiedCode = fieldNameChanger.getModifiedCode();
        System.out.println(modifiedCode);

        // Optionally, write the modified code to a new file
        String outputFilePath = "testcode/output.java";
        Files.write(Paths.get(outputFilePath), modifiedCode.getBytes());
    }
}
