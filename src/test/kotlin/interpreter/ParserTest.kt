package interpreter

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalStateException

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

    context("parseProgram 메서드는") {
        test("파싱한 프로그램을 반환한다.") {
            // given
            val input = "let foo = 5;"
            val lexer = Lexer(input = input)
            val parser = Parser(lexer = lexer)
            // when
            val actual = parser.parseProgram()
            // then
            actual shouldNotBe null
        }
    }

    context("expectPeek 메서드는") {
        context("전달한 토큰 타입이 다음 토큰과 같으면") {
            test("true 를 반환하고 다음 토큰으로 넘어갈 수 있다.") {
                // given
                val input = "let five = 5;"
                val lexer = Lexer(input = input)
                val sut = Parser(lexer = lexer)
                // when
                val actual = sut.expectPeek(tokenType = TokenType.IDENT)
                // then
                actual shouldBe true
                sut.curToken.type shouldBe TokenType.IDENT
            }
        }
        context("전달한 토큰 타입이 다음 토큰과 다르면") {
            test("false 를 반환한다.") {
                // given
                val input = "let five = 5;"
                val lexer = Lexer(input = input)
                val sut = Parser(lexer = lexer)
                // when
                val actual = sut.expectPeek(tokenType = TokenType.ASSIGN)
                // then
                actual shouldBe false
                sut.curToken.type shouldBe TokenType.LET
            }
        }
    }

    context("parseLetStatement 메서드는") {
        test("파싱한 let 명령문을 반환한다.") {
            // given
            val input = """
               let five = 5;
            """
            val lexer = Lexer(input = input)
            val sut = Parser(lexer = lexer)
            // when
            val statement = sut.parseLetStatement()
            // then
            statement.tokenLiteral() shouldBe "let"
            sut.curToken.type shouldBe TokenType.SEMICOLON
        }

        context("let 다음으로 식별자가 아닌 경우") {
            test("예외가 발생한다.") {
                // given
                val input = """
                   let 5 = 5;
                """
                val lexer = Lexer(input = input)
                val sut = Parser(lexer = lexer)
                // when & then
                assertThrows<IllegalStateException> { sut.parseLetStatement() }
            }
        }

        context("식별자 다음으로 '=' 가 아닌 경우") {
            test("예외가 발생한다.") {
                // given
                val input = """
                   let five 5;
                """
                val lexer = Lexer(input = input)
                val sut = Parser(lexer = lexer)
                // when & then
                assertThrows<IllegalStateException> { sut.parseLetStatement() }
            }
        }
    }
})
