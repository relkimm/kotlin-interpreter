package interpreter

enum class TokenType(val value: String) {
    ILLEGAL("ILLEGAL"),
    EOF("EOF"),
    IDENT("IDENT"),
    LET("LET"),
    INT("INT"),
    FUNCTION("FUNCTION"),
    IF("IF"),
    ELSE("ELSE"),
    RETURN("RETURN"),
    TRUE("TRUE"),
    FALSE("FALSE"),
    COMMA(","),
    SEMICOLON(";"),
    LPAREN("("),
    RPAREN(")"),
    LBRACE("{"),
    RBRACE("}"),
    ASSIGN("="),
    EQ("=="),
    NOT_EQ("!="),
    PLUS("+"),
    MINUS("-"),
    ASTERISK("*"),
    SLASH("/"),
    BANG("!"),
    LT("<"),
    GT(">"),
    ;
}
