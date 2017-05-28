package splatoon

enum Rank {

    C_MINNUS(label: 'C-'),
    C(label: 'C'),
    C_PLUS(label: 'C+'),
    B_MINUS(label: 'B-'),
    B(label: 'B'),
    B_PLUS(label: 'B+'),
    A_MINUS(label: 'A-'),
    A(label: 'A'),
    A_PLUS(label: 'A+'),
    S_MINUS(label: 'S-'),
    S(label: 'S'),
    S_PLUS(label: 'S+')

    String label

    @Override
    String toString() {
        return label
    }
}