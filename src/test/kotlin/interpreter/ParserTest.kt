package interpreter

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ParserTest : FunSpec({
    context("nextToken 메서드는") {
        test("다음 토큰으로 넘어갈 수 있다.") {
            // given
            val input = "let foo = 5;"
            val lexer = Lexer(input = input)
            val sut = Parser(lexer = lexer)
            // when
            sut.nextToken()
            // then
            sut.curToken shouldBe Token(type = TokenType.IDENT, literal = "foo")
            sut.peekToken shouldBe Token(type = TokenType.ASSIGN, literal = "=")
        }
    }
})
