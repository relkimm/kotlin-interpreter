package interpreter

import interpreter.TokenType.*

data class Token(
    val type: TokenType,
    val literal: String,
) {
    companion object {
        private val keywords = mapOf(
            "let" to LET,
            "fn" to FUNCTION,
            "if" to IF,
            "else" to ELSE,
            "return" to RETURN,
            "true" to TRUE,
            "false" to FALSE,
        )

        fun lookupKeyword(target: String): TokenType {
            return keywords[target] ?: IDENT
        }
    }
}
