package interpreter.ast.statement

import interpreter.Token
import interpreter.ast.expression.Expression
import interpreter.ast.expression.Identifier

class LetStatement(
    val token: Token,
    var name: Identifier? = null,
    var value: Expression? = null,
): Statement {
    override fun tokenLiteral(): String {
        return this.token.literal
    }
}
