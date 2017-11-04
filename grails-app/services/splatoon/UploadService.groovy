package splatoon

import com.amazonaws.regions.Region
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional
import grails.plugin.awssdk.AwsClientUtil
import grails.plugin.awssdk.s3.AmazonS3Service
import groovy.transform.CompileStatic

@CompileStatic
class UploadService {

    AmazonS3Service amazonS3Service

    String upload(String s3FileName, File file) {
        def request = new PutObjectRequest('splatoon', s3FileName, file).withCannedAcl(CannedAccessControlList.PublicRead)
        request.metadata = new ObjectMetadata()
        request.metadata.cacheControl = 'max-age=2592000'
        amazonS3Service.client.putObject(request)
        return buildAbsoluteUrl('splatoon', s3FileName)
    }

    private String buildAbsoluteUrl(String bucketName, String path) {
        def region = AwsClientUtil.buildRegion(amazonS3Service.config, amazonS3Service.serviceConfig) as Region
        return "https://${region.name == AwsClientUtil.DEFAULT_REGION ? 's3' : "s3-${region.name}"}.amazonaws.com/${bucketName}/${path}"
    }
}
