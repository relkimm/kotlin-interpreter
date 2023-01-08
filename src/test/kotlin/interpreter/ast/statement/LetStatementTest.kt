package interpreter.ast.statement

import interpreter.Token
import interpreter.TokenType
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class LetStatementTest : FunSpec({
    context("tokenLiteral 메서드는") {
        test("토큰의 literal 값을 반환한다.") {
            // given
            val sut = LetStatement(token = Token(type = TokenType.LET, literal = "let"))
            // when
            val actual = sut.tokenLiteral()
            // then
            actual shouldBe "let"
        }
    }

})
