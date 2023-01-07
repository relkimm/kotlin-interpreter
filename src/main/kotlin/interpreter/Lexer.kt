package interpreter

class Lexer(val input: String) {
    var position: Int = 0
    var readPosition: Int = 0
    var ch: Char? = null

    init { this.readChar() }

    fun readChar() {
        if (this.readPosition >= input.length) {
            this.ch = null
        } else {
            this.position = this.readPosition
            this.ch = this.input[this.position]
            this.readPosition++
        }
    }

    fun peekChar(): Char? {
        if (this.readPosition >= input.length) {
            return null
        }
        return this.input[this.readPosition]
    }

    fun readIdentifier(): String {
        val position = this.position
        while (isLetter(this.ch)) {
            this.readChar()
        }
        return this.input.slice(position until this.position)
    }

    fun readNumber(): String {
        val position = this.position
        while (isDigit(this.ch)) {
            this.readChar()
        }
        return this.input.slice(position until this.position)
    }

    fun skipWhitespace() {
        while (this.ch == ' ' || this.ch == '\n' || this.ch == '\t' || this.ch == '\r') {
            this.readChar()
        }
    }

    fun nextToken(): Token {
        this.skipWhitespace()
        val token = when (this.ch) {
            '=' -> {
                if (this.peekChar() == '=') {
                    val ch = this.ch
                    this.readChar()
                    val literal = ch.toString() + this.ch.toString()
                    Token(type = TokenType.EQ, literal = literal)
                } else {
                    Token(type = TokenType.ASSIGN, literal = this.ch)
                }
            }
            '!' -> {
                if (this.peekChar() == '=') {
                    val ch = this.ch
                    this.readChar()
                    val literal = ch.toString() + this.ch.toString()
                    Token(type = TokenType.NOT_EQ, literal = literal)
                } else {
                    Token(type = TokenType.BANG, literal = this.ch)
                }
            }
            ';' -> Token(type = TokenType.SEMICOLON, literal = this.ch)
            ',' -> Token(type = TokenType.COMMA, literal = this.ch)
            '(' -> Token(type = TokenType.LPAREN, literal = this.ch)
            ')' -> Token(type = TokenType.RPAREN, literal = this.ch)
            '{' -> Token(type = TokenType.LBRACE, literal = this.ch)
            '}' -> Token(type = TokenType.RBRACE, literal = this.ch)
            '+' -> Token(type = TokenType.PLUS, literal = this.ch)
            '-' -> Token(type = TokenType.MINUS, literal = this.ch)
            '*' -> Token(type = TokenType.ASTERISK, literal = this.ch)
            '/' -> Token(type = TokenType.SLASH, literal = this.ch)
            '>' -> Token(type = TokenType.GT, literal = this.ch)
            '<' -> Token(type = TokenType.LT, literal = this.ch)
            null -> Token(type = TokenType.EOF, literal = "")
            else -> {
                if (isLetter(this.ch)) {
                    val literal = this.readIdentifier()
                    val type = Token.lookupKeyword(target = literal)
                    return Token(type = type, literal = literal)
                } else if (isDigit(this.ch)) {
                    val literal = this.readNumber()
                    return Token(type = TokenType.INT, literal = literal)
                }
                Token(type = TokenType.ILLEGAL, literal = this.ch)
            }
        }
        this.readChar()
        return token
    }
}

fun isLetter(ch: Char?): Boolean {
    if (ch == null) {
        return false
    }
    return ch.isLetter() || ch == '_'
}

fun isDigit(ch: Char?): Boolean {
    if (ch == null) {
        return false
    }
    return ch.isDigit()
}
