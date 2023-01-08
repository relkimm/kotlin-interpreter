package interpreter.ast

import interpreter.ast.statement.Statement

class Program(val statements: List<Statement>): Node {
    override fun tokenLiteral(): String {
        if (statements.isNotEmpty()) {
            return statements[0].tokenLiteral()
        }
        return ""
    }
}
