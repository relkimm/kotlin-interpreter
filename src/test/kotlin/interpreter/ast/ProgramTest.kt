package interpreter.ast
import interpreter.Token
import interpreter.TokenType
import interpreter.ast.statement.LetStatement
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ProgramTest : FunSpec({
    context("tokenLiteral 메서드는") {
        context("프로그램 내 명령문이 있으면") {
            test("첫번째 명령문의 tokenLiteral 을 반환한다.") {
                // given
                val statement = LetStatement(token = Token(type = TokenType.LET, literal = "let"))
                val sut = Program()
                sut.statements.add(statement)
                // when
                val actual = sut.tokenLiteral()
                // then
                actual shouldBe "let"
            }
        }

        context("프로그램 내 명령문이 없으면") {
            test("공백을 반환한다.") {
                // given
                val sut = Program()
                // when
                val actual = sut.tokenLiteral()
                // then
                actual shouldBe ""
            }
        }
    }
})
