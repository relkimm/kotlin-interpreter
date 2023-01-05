package interpreter

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class TokenTest: FunSpec({
    context("lookupKeyword 메서드는") {
        context("전달하는 값이") {
            context("예약어이면") {
                test("예약어 토큰 타입을 반환한다.") {
                    // given
                    val tests = listOf(
                        Pair("fn", TokenType.FUNCTION),
                        Pair("let", TokenType.LET),
                        Pair("if", TokenType.IF),
                        Pair("else", TokenType.ELSE),
                        Pair("return", TokenType.RETURN),
                        Pair("true", TokenType.TRUE),
                        Pair("false", TokenType.FALSE),
                    )
                    // when & then
                    tests.forEach { (keyword: String, expected: TokenType) ->
                        val actual = Token.lookupKeyword(target = keyword)
                        actual shouldBe expected
                    }
                }
            }

            context("예약어가 아니면") {
                test("식별자 토큰 타입을 반환한다.") {
                    // given
                    val identifier = "foo"
                    // when
                    val actual = Token.lookupKeyword(target = identifier)
                    // then
                    actual shouldBe TokenType.IDENT
                }
            }
        }
    }
})
