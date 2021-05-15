package grupo10.util;

/**
 * Funciones útiles para cadenas
 * @author dperez
 */
public class StringUtils {
    
    public static String escaparHtml(String entrada) {
        StringBuilder builder = new StringBuilder();
        if (entrada != null) {
            for (char c : entrada.toCharArray()) {
                switch (c) {
                    
                    case '&':
                        builder.append("&amp;");
                        break;
                    
                    case '\"':
                        builder.append("&quot;");
                        break;
                    
                    case '<':
                        builder.append("&lt;");
                        break;
                        
                    case '>':
                        builder.append("&gt;");
                        break;
                        
                    default:
                        builder.append(c);
                    
                }
            }
        }
        return builder.toString();
    }
    
    public static String escaparSQLLike(String entrada) {
        StringBuilder builder = new StringBuilder();
        if (entrada != null) {
            for (char c : entrada.toCharArray()) {
                switch (c) {
                    
                    case '.':
                        builder.append("..");
                        break;
                    
                    case '%':
                        builder.append(".%");
                        break;
                    
                    case '_':
                        builder.append("._");
                        break;
                        
                    default:
                        builder.append(c);
                    
                }
            }
        }
        return builder.toString();
    }
    
}
