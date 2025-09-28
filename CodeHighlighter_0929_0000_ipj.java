// 代码生成时间: 2025-09-29 00:00:49
import org.codehaus.groovy.grails.web.servlet.FlashScope;
import grails.transaction.Transactional;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * CodeHighlighter class is responsible for highlighting code syntax.
 * It follows the best practices and is designed to be easily maintained and extended.
 */
public class CodeHighlighter {

    private static final String KEYWORD_PATTERN = "\b(abstract|assert|boolean|break|byte|case|catch|char|class|const|continue|default|do|double|else|enum|extends|final|finally|float|for|goto|if|implements|import|instanceof|int|interface|long|native|new|package|private|protected|public|return|short|static|strictfp|super|switch|synchronized|this|throw|throws|transient|try|void|volatile|while)\b";
    private static final String COMMENT_PATTERN = "(//.*\
|/\*[^*]*\*+(?:[^/*][^*]*\*+)*/)";
    private static final String STRING_PATTERN = ""(\\.|[^\"])*\"";

    /**
     * Highlights the syntax of the given code.
     * @param code The code to be highlighted.
     * @return The highlighted code.
     */
    public String highlightSyntax(String code) {
        // Initialize the highlighted code as empty
        String highlightedCode = "";

        // Highlight keywords
        highlightedCode = highlightKeywords(highlightedCode, code);

        // Highlight comments
        highlightedCode = highlightComments(highlightedCode, code);

        // Highlight strings
        highlightedCode = highlightStrings(highlightedCode, code);

        return highlightedCode;
    }

    private String highlightKeywords(String highlightedCode, String code) {
        // Replace keywords with ANSI escape code for red color
        return code.replaceAll(KEYWORD_PATTERN, Ansi.ansi().fg(Ansi.Color.RED).toString() + "$0" + Ansi.ansi().reset().toString());
    }

    private String highlightComments(String highlightedCode, String code) {
        // Replace comments with ANSI escape code for green color
        return code.replaceAll(COMMENT_PATTERN, Ansi.ansi().fg(Ansi.Color.GREEN).toString() + "$0" + Ansi.ansi().reset().toString());
    }

    private String highlightStrings(String highlightedCode, String code) {
        // Replace strings with ANSI escape code for yellow color
        return code.replaceAll(STRING_PATTERN, Ansi.ansi().fg(Ansi.Color.YELLOW).toString() + "$0" + Ansi.ansi().reset().toString());
    }

    /**
     * Main method for testing the CodeHighlighter.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        AnsiConsole.systemInstall();

        CodeHighlighter highlighter = new CodeHighlighter();
        String code = "public class Main { public static void main(String[] args) { System.out.println("Hello, World!"); } }";
        String highlightedCode = highlighter.highlightSyntax(code);

        System.out.println(highlightedCode);
    }
}
