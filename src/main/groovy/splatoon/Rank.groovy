package splatoon

enum Rank {

    // Values should be ordered
    C_MINNUS('C-', 0),
    C('C', 1),
    C_PLUS('C+', 2),
    B_MINUS('B-', 3),
    B('B', 4),
    B_PLUS('B+', 5),
    A_MINUS('A-', 6),
    A('A', 7),
    A_PLUS('A+', 8),
    S_MINUS('S-', 9),
    S('S', 10),
    S_PLUS('S+', 11)

    String label
    int order

    Rank(String label, int order) {
        this.label = label
        this.order = order
    }

    @Override
    String toString() {
        return label
    }

    Collection<Rank> getHigherOrEqualRanks() {
        return values().findAll { it.order >= this.order }
    }
}