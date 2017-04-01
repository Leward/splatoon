package splatoon

enum AdType {

    LOOKING_FOR_TEAM_AD(matchingStrings: ['LOOKING_FOR_TEAM_AD', 'TEAM']),
    LOOKING_FOR_TEAMMATE_AD(matchingStrings: ['LOOKING_FOR_TEAMMATE_AD', 'TEAMMATE'])

    List<String> matchingStrings

    static AdType from(String string) {
        return values().find {
            it.matchingStrings.contains(string)
        }
    }

}
