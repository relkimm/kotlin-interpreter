package interpreter

class Lexer(val input: String) {
    var position: Int = 0
    var readPosition: Int = 0
    var ch: Char? = null

    init { this.readChar() }

    fun readChar() {
        if (this.readPosition >= input.length) {
            this.ch = null
        } else {
            this.position = this.readPosition
            this.ch = this.input[this.position]
            this.readPosition++
        }
    }
}
