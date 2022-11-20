package constants

import util.Util

enum class LettersEng(
    val letter: Char,
    val value: Int,
    val count: Int,
    val keyEventCode: Long = Util.getKeyCode(letter.code)
) {
    A('A', 1, 9),
    B('B', 3, 2),
    C('C', 3, 2),
    D('D', 2, 4),
    E('E', 1, 12),
    F('F', 4, 2),
    G('G', 2, 3),
    H('H', 4, 2),
    I('I', 1, 9),
    J('J', 8, 1),
    K('K', 5, 1),
    L('L', 1, 4),
    M('M', 3, 2),
    N('N', 1, 6),
    O('O', 1, 8),
    P('P', 3, 2),
    Q('Q', 10, 1),
    R('R', 1, 6),
    S('S', 1, 4),
    T('T', 1, 6),
    U('U', 1, 4),
    V('V', 4, 2),
    W('W', 4, 2),
    X('X', 8, 1),
    Y('Y', 4, 2),
    Z('Z', 10, 1)
}