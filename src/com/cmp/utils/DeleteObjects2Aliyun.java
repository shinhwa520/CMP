package com.cmp.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.cmp.controller.OutsiteController;
import com.cmp.model.FilesBaseConfig;

/**
 * This sample demonstrates how to delete objects under specfied bucket 
 * from Aliyun OSS using the OSS SDK for Java.
 */
public class DeleteObjects2Aliyun {
    
    private static String endpoint;
    private static String accessKeyId;
    private static String accessKeySecret;
    private static String bucketName;
    private static Log log = LogFactory.getLog(DeleteObjects2Aliyun.class);
    
    private void initConfig(FilesBaseConfig config) {
    	endpoint = config.getEndPoint();
    	accessKeyId = config.getAccessKeyId();
    	accessKeySecret = config.getAccessKeySecret();
    	bucketName = config.getBucketName();
    }
    
    public void deleteObjects(FilesBaseConfig config, List<String> keys) throws IOException {    
    	initConfig(config);
        /*
         * Constructs a client instance with your account for accessing OSS
         */
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        
        try {
            /*
             * Delete all objects uploaded recently under the bucket
             */
            DeleteObjectsResult deleteObjectsResult = client.deleteObjects(
                    new DeleteObjectsRequest(bucketName).withKeys(keys));
            List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();

        } catch (OSSException oe) {
            log.error("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            log.error("Error Message: " + oe.getErrorCode());
            log.error("Error Code:       " + oe.getErrorCode());
            log.error("Request ID:      " + oe.getRequestId());
            log.error("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            log.error("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            log.error("Error Message: " + ce.getMessage());
        } finally {
            /*
             * Do not forget to shut down the client finally to release all allocated resources.
             */
            client.shutdown();
        }
    }
}
