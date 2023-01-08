package interpreter

import interpreter.ast.Program
import interpreter.ast.expression.Identifier
import interpreter.ast.statement.LetStatement
import interpreter.ast.statement.Statement

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

    fun expectPeek(tokenType: TokenType): Boolean {
        if (this.peekToken.type == tokenType) {
            this.nextToken()
            return true
        }
        return false
    }

    fun parseProgram(): Program {
        val program = Program()
        while (this.curToken.type != TokenType.EOF) {
            val statement = this.parseStatement()
            program.statements.add(statement)
            this.nextToken()
        }
        return program
    }

    fun parseStatement(): Statement {
        return when (this.curToken.type) {
            TokenType.LET -> parseLetStatement()
            else -> throw IllegalStateException()
        }
    }

    fun parseLetStatement(): Statement {
        val letStmt = LetStatement(token = this.curToken)
        if (this.expectPeek(TokenType.IDENT).not()) {
            throw IllegalStateException()
        }
        letStmt.name = Identifier(token = this.curToken, value = this.curToken.literal)

        if (this.expectPeek(TokenType.ASSIGN).not()) {
            throw IllegalStateException()
        }

        while (this.curToken.type != TokenType.SEMICOLON) {
            this.nextToken()
        }

        return letStmt
    }
}
