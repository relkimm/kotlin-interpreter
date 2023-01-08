package interpreter

class Parser(
    val lexer: Lexer,
) {
    var curToken: Token
    var peekToken: Token

    init {
        val curToken = this.lexer.nextToken()
        val peekToken = this.lexer.nextToken()
        this.curToken = curToken
        this.peekToken = peekToken
    }

    fun nextToken() {
        this.curToken = this.peekToken
        this.peekToken = this.lexer.nextToken()
    }
}
