package splatoon

class Performances implements Iterable<Map.Entry<MonthYear, Performance>> {

    TreeMap<MonthYear, Performance> map = [:]

    void add(Ladder ladder) {
        if(!map[ladder.monthYear]) {
            map[ladder.monthYear] = new Performance()
        }
        map[ladder.monthYear].add(ladder)

        // Fill gaps if any
        def min = map.firstKey()
        def max = map.lastKey()
        def monthYear = min.next()
        while(monthYear < max) {
            if(!map[monthYear]) {
                map[monthYear] = new Performance()
            }
            monthYear = monthYear.next()
        }
    }

    Performance getAt(MonthYear monthYear) {
        return map[monthYear]
    }

    @Override
    Iterator<Map.Entry<MonthYear, Performance>> iterator() {
        return map.iterator()
    }
}
