package splatoon

enum Rank {

    C_MINNUS('C-'),
    C('C'),
    C_PLUS('C+'),
    B_MINUS('B-'),
    B('B'),
    B_PLUS('B+'),
    A_MINUS('A-'),
    A('A'),
    A_PLUS('A+'),
    S_MINUS('S-'),
    S('S'),
    S_PLUS('S+')

    String label

    Rank(String label) {
        this.label = label
    }

    @Override
    String toString() {
        return label
    }
}