package ifparser;

import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;

public class FieldNameChanger extends Java8ParserBaseVisitor<Void> {
    private final FieldNameMapping fieldNameMapping;
    private final TokenStreamRewriter rewriter;

    private String getTextIncludingHiddenTokens(int start, int stop) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= stop; i++) {
            sb.append(rewriter.getTokenStream().get(i).getText());
        }
        return sb.toString();
    }

    public FieldNameChanger(FieldNameMapping fieldNameMapping, TokenStream tokens) {
        this.fieldNameMapping = fieldNameMapping;
        this.rewriter = new TokenStreamRewriter(tokens);
    }

    @Override
    public Void visitVariableDeclaratorId(Java8Parser.VariableDeclaratorIdContext ctx) {
        String oldName = ctx.Identifier().getText().replaceAll("\\s", "");
        String newName = fieldNameMapping.getNewName(oldName).replaceAll("\\s", "");

        if (!oldName.equals(newName)) {
            rewriter.replace(ctx.Identifier().getSymbol(), newName);
        }
        System.out.println(oldName);
        System.out.println(newName);


        return super.visitVariableDeclaratorId(ctx);
    }

    public String getModifiedCode() {
        return rewriter.getText();
    }
}
