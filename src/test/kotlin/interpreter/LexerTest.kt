package interpreter

import interpreter.TokenType.*
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

    context("peekChar 메서드는") {
        test("다음에 읽을 문자를 반환한다.") {
            val input = "let"
            val sut = Lexer(input = input)
            sut.peekChar() shouldBe 'e'
        }
    }

    context("readIdentifier 메서드는") {
        context("식별자를 전달하면") {
            test("식별자를 읽고 반환할 수 있다.") {
                val input = "let "
                val sut = Lexer(input = input)
                val actual = sut.readIdentifier()
                actual shouldBe "let"
            }
        }
    }

    context("readNumber 메서드는") {
        context("숫자를 전달하면") {
            test("숫자를 읽고 숫자 문자열을 반환할 수 있다.") {
                val input = "10;"
                val sut = Lexer(input = input)
                val actual = sut.readNumber()
                actual shouldBe "10"
            }
        }
    }

    context("nextToken 메서드는") {
        test("다음 토큰을 반환할 수 있다.") {
            val input = """
                let five = 5;
                let ten = 10;
                
                let add = fn(x, y) {
                    return x + y;
                };
                let result = add(five, ten);
                */<>!
                
                if (five == 5) {
                    return true;
                }
                
                if (ten != 10) {
                    return false;
                }
            """
            val sut = Lexer(input = input)
            val tests = listOf(
                LET to "let", IDENT to "five", ASSIGN to "=", INT to "5", SEMICOLON to ";",
                LET to "let", IDENT to "ten", ASSIGN to "=", INT to "10", SEMICOLON to ";",
                LET to "let", IDENT to "add", ASSIGN to "=", FUNCTION to "fn", LPAREN to "(", IDENT to "x", COMMA to ",", IDENT to "y", RPAREN to ")",
                LBRACE to "{", RETURN to "return", IDENT to "x", PLUS to "+", IDENT to "y", SEMICOLON to ";", RBRACE to "}", SEMICOLON to ";",
                LET to "let", IDENT to "result", ASSIGN to "=", IDENT to "add", LPAREN to "(", IDENT to "five", COMMA to ",", IDENT to "ten", RPAREN to ")", SEMICOLON to ";",
                ASTERISK to "*", SLASH to "/", LT to "<", GT to ">", BANG to "!",
                IF to "if", LPAREN to "(", IDENT to "five", EQ to "==", INT to "5", RPAREN to ")", LBRACE to "{",
                RETURN to "return", TRUE to "true", SEMICOLON to ";", RBRACE to "}",
                IF to "if", LPAREN to "(", IDENT to "ten", NOT_EQ to "!=", INT to "10", RPAREN to ")", LBRACE to "{",
                RETURN to "return", FALSE to "false", SEMICOLON to ";", RBRACE to "}",
                )
            tests.forEach { (tokenType, literal) ->
                val token = sut.nextToken()
                token.type shouldBe tokenType
                token.literal shouldBe literal
            }
        }
    }

    context("isLetter 함수는") {
        test("전달한 값이 문자인지 판별할 수 있다.") {
            isLetter(ch = 'a') shouldBe true
            isLetter(ch = 'z') shouldBe true
            isLetter(ch = '_') shouldBe true
            isLetter(ch = 'A') shouldBe true
            isLetter(ch = '1') shouldBe false
            isLetter(ch = '*') shouldBe false
        }
    }

    context("isDigit 함수는") {
        test("전달한 값이 숫자인지 판별할 수 있다.") {
            isDigit(ch = '0') shouldBe true
            isDigit(ch = '3') shouldBe true
            isDigit(ch = '9') shouldBe true
            isDigit(ch = 'a') shouldBe false
        }
    }

    context("skipWhitespace 함수는") {
        test("공백을 제거할 수 있다.") {
            val tests = listOf(" let", "\nlet", "\rlet", "\tlet")
            tests.forEach { test ->
                val sut = Lexer(input = test)
                sut.skipWhitespace()
                sut.ch shouldBe 'l'
            }
        }
    }
})
