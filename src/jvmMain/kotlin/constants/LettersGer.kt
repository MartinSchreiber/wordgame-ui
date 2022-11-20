package constants

import util.Util

enum class LettersGer(
    val letter: Char,
    val value: Int,
    val count: Int,
    val keyEventCode: Long = Util.getKeyCode(letter.code)
) {
    A('A', 1, 5),
    B('B', 3, 2),
    C('C', 4, 2),
    D('D', 1, 4),
    E('E', 1, 15),
    F('F', 4, 2),
    G('G', 2, 3),
    H('H', 2, 4),
    I('I', 1, 5),
    J('J', 5, 1),
    K('K', 4, 2),
    L('L', 2, 3),
    M('M', 3, 4),
    N('N', 1, 9),
    O('O', 2, 3),
    P('P', 4, 1),
    Q('Q', 10, 1),
    R('R', 1, 6),
    S('S', 1, 7),
    T('T', 1, 6),
    U('U', 1, 6),
    V('V', 5, 1),
    W('W', 3, 1),
    X('X', 8, 1),
    Y('Y', 10, 1),
    Z('Z', 3, 1),
    AE('Ä', 5, 1),
    OE('Ö', 8, 1),
    UE('Ü', 5, 1),
    SZ('ß', 10, 1)
}