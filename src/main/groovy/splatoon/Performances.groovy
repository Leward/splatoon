package splatoon

import groovy.json.JsonOutput

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
        return map[monthYear] ?: new Performance()
    }

    String getChartDataAsJson() {
        def json = [
                labels: map.keySet().collect { it.toString() },
                datasets: [
                        [
                                label: 'Wins',
                                backgroundColor: 'rgb(75, 192, 192)',
                                data: map.values().collect { it.wins }
                        ],
                        [
                                label: 'Loses',
                                backgroundColor: 'rgb(255, 99, 132)',
                                data: map.values().collect { it.loses * -1 }
                        ],
                ]
        ]
        return JsonOutput.prettyPrint(JsonOutput.toJson(json))
    }

    @Override
    Iterator<Map.Entry<MonthYear, Performance>> iterator() {
        return map.iterator()
    }
}
