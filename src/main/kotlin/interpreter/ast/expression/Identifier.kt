package interpreter.ast.expression

import interpreter.Token

class Identifier(
    val token: Token,
    val value: String,
): Expression {
    override fun tokenLiteral(): String {
        return this.token.literal
    }
}
