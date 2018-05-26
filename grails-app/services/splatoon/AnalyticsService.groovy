package splatoon

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import groovy.transform.CompileStatic
import org.apache.commons.io.IOUtils
import org.springframework.beans.factory.annotation.Value

import javax.annotation.PostConstruct
import java.nio.charset.StandardCharsets

@CompileStatic
class AnalyticsService {

    @Value('${splatoon.analytics.enabled:false}')
    boolean enabled = false

    @Value('${splatoon.analytics.serviceAccount.id:null}')
    String serviceAccountId

    @Value('${splatoon.analytics.serviceAccount.jsonKey:null}')
    String jsonKey

    private Credential credential

    @PostConstruct
    public void init() {
        if (!isEnabled()) {
            return
        }
        credential = GoogleCredential.fromStream(IOUtils.toInputStream(jsonKey, StandardCharsets.UTF_8))
                .createScoped(['https://www.googleapis.com/auth/analytics.readonly'])
    }

    String getAccessToken() {
        if (!isEnabled() || !credential.refreshToken()) {
            return null
        }
        return credential.accessToken
    }

    boolean isEnabled() {
        return enabled && serviceAccountId != null && jsonKey != null
    }

}
