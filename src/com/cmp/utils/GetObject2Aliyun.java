package com.cmp.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.OSSObject;
import com.cmp.model.FilesBaseConfig;

/**
 * This sample demonstrates how to get started with basic requests to Aliyun OSS 
 * using the OSS SDK for Java.
 */
public class GetObject2Aliyun {
    
    private static String endpoint;
    private static String accessKeyId;
    private static String accessKeySecret;
    private static String bucketName;
    
    private void initConfig(FilesBaseConfig config) {
    	endpoint = config.getEndPoint();
    	accessKeyId = config.getAccessKeyId();
    	accessKeySecret = config.getAccessKeySecret();
    	bucketName = config.getBucketName();
    }
    
    public boolean getObject(FilesBaseConfig config, String key, String downloadFileName, HttpServletResponse response) throws IOException {
    	initConfig(config);
    	
    	boolean isOutputed = true;
    	InputStream is = null;
        /*
         * Constructs a client instance with your account for accessing OSS
         */
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        
        try {

            /*
             * Determine whether the bucket exists
             */
            if (!ossClient.doesBucketExist(bucketName)) {
            	
            }
            /*
             * Download an object from your bucket
             */
            System.out.println("Downloading an object");
            OSSObject object = ossClient.getObject(bucketName, key);
            System.out.println("Content-Type: "  + object.getObjectMetadata().getContentType());
            
            is = object.getObjectContent();
            response.setContentType("multipart/form-data");
            
//            downloadFileName = new String(downloadFileName.getBytes(), "ISO8859-1");
            response.setHeader("Content-Disposition","attachment; filename="  + downloadFileName);  
//            response.setHeader("Content-Disposition","attachment; filename=\""  + URLEncoder.encode(downloadFileName, "ISO8859-1") + "\"");  
            OutputStream output = response.getOutputStream();
			
			try {
				
	            byte[] b = new byte[2048];
	            int len;

	            while((len = is.read(b)) > 0){
	              output.write(b, 0, len);
	            }
	            
			} catch (Exception e) {
				throw e;
				
			} finally {
				is.close();
	            output.flush();
	            output.close();   //關閉串流
			}

        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorCode());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
            isOutputed = false;
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
            isOutputed = false;
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            /*
             * Do not forget to shut down the client finally to release all allocated resources.
             */
            ossClient.shutdown();
        }
		return isOutputed;
    }
}
