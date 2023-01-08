package interpreter.ast

import interpreter.ast.statement.Statement

class Program: Node {
    val statements: MutableList<Statement> = mutableListOf()
    override fun tokenLiteral(): String {
        if (statements.isNotEmpty()) {
            return statements[0].tokenLiteral()
        }
        return ""
    }
}
