package splatoon

import com.amazonaws.regions.Region
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import grails.plugin.awssdk.AwsClientUtil
import grails.plugin.awssdk.s3.AmazonS3Service
import groovy.transform.CompileStatic
import org.springframework.web.multipart.MultipartFile

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

    String upload(String s3FileName, MultipartFile multipartFile) {
        def metadata = new ObjectMetadata()
        metadata.cacheControl = 'max-age=2592000'
        return amazonS3Service.storeMultipartFile('splatoon', s3FileName, multipartFile, CannedAccessControlList.PublicRead, metadata)
    }

    private String buildAbsoluteUrl(String bucketName, String path) {
        def region = AwsClientUtil.buildRegion(amazonS3Service.config, amazonS3Service.serviceConfig) as Region
        return "https://${region.name == AwsClientUtil.DEFAULT_REGION ? 's3' : "s3-${region.name}"}.amazonaws.com/${bucketName}/${path}"
    }
}
