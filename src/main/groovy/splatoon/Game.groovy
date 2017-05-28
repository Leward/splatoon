package splatoon

enum Game {

    SPLATOON_1(label: 'Splatoon'),
    SPLATOON_2(label: 'Splatoon 2')

    /**
     * How the value should be displayed
     */
    String label


    @Override
    String toString() {
        return label
    }
}