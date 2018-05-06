package splatoon

import org.springframework.mock.web.MockMultipartFile
import spock.lang.Specification

class AvatarTest extends Specification {

    def "get extension of PNG file"() {
        setup:
        def file = new MockMultipartFile("file", "my_avatar.png", "image/png", new byte[0])
        def avatar = new Avatar(file: file)
        expect:
        avatar.extension == 'png'
    }

    def "get extension of JPG file"() {
        setup:
        def file = new MockMultipartFile("file", "test_123.jpg", "image/jpeg", new byte[0])
        def avatar = new Avatar(file: file)
        expect:
        avatar.extension == 'jpg'
    }
}
