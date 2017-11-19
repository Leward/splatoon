package splatoon

import org.springframework.web.multipart.MultipartFile

class TeamMember {

    String name
    String avatar
    PlayerType type
    MultipartFile file

    static transients = ['file']

    static belongsTo = [team: Team]

    static hasMany = [roles: PlayerRole]

    static constraints = {
        team nullable: false
        avatar nullable: true
        type nullable: false
        file nullable: true, validator: { val, obj ->
            if (val == null || val.empty) {
                return true
            }
            return ['jpeg', 'jpg', 'png'].any { extension ->
                val.originalFilename?.toLowerCase()?.endsWith(extension)
            }
        }
    }

    @Override
    String toString() {
        return name
    }
}

