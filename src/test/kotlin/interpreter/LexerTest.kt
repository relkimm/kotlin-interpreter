package interpreter

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class LexerTest : FunSpec({
    test("Lexer 생성") {
        // given
        val input = """
          let five = 5;
        """
        // when
        val sut = Lexer(input = input)
        // then
        sut.input shouldBe input
        sut.position shouldBe 0
        sut.readPosition shouldBe 1
    }

    context("readChar 메서드는") {
        context("다음에 읽을 문자가 있는 경우") {
            test("ch 에 다음 문자를 할당한다.") {
                // given
                val input = "let five = 5;"
                val sut = Lexer(input = input)
                // when
                sut.readChar()
                // then
                sut.ch shouldBe 'e'
            }
        }

        context("더 이상 읽을 문자가 없는 경우") {
            test("ch 에 null 을 할당한다.") {
                // given
                val input = "let"
                val sut = Lexer(input = input)
                // when
                sut.readChar()
                sut.readChar()
                sut.readChar()
                // then
                sut.ch shouldBe null
            }
        }
    }
})
