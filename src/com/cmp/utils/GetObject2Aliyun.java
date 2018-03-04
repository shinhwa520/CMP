package com.cmp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
	private static Log log = LogFactory.getLog(GetObject2Aliyun.class);
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
    
    public boolean getObject(
    		FilesBaseConfig config, Map<String, String> keyMap, HttpServletResponse response) throws IOException {
    	initConfig(config);
    	
    	boolean isOutputed = true;
    	InputStream in = null;
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
            for (Entry<String, String> entry : keyMap.entrySet()) {
            	OSSObject object = ossClient.getObject(bucketName, entry.getKey());
                
                in = object.getObjectContent();
                response.setContentType("multipart/form-data");
                
                response.setHeader("Content-Disposition","attachment; filename="  + entry.getValue());  
                
            	OutputStream output = response.getOutputStream();
    			
    			try {
    				
    	            byte[] b = new byte[2048];
    	            int len;

    	            while((len = in.read(b)) > 0){
    	              output.write(b, 0, len);
    	            }
    	            
    			} catch (Exception e) {
    				throw e;
    				
    			} finally {
    				in.close();
    	            output.flush();
    	            output.close();   //關閉串流
    			}
            }

        } catch (OSSException oe) {
            log.error("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            log.error("Error Message: " + oe.getErrorCode());
            log.error("Error Code:       " + oe.getErrorCode());
            log.error("Request ID:      " + oe.getRequestId());
            log.error("Host ID:           " + oe.getHostId());
            isOutputed = false;
        } catch (ClientException ce) {
            log.error("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            log.error("Error Message: " + ce.getMessage());
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
    
    public boolean getObjectWithWaterMark(
    		FilesBaseConfig config, Map<String, String> keyMap, List<String> waterMarks, String outputPath, HttpServletResponse response) throws IOException {
    	
    	initConfig(config);
    	
    	boolean isOutputed = true;
    	InputStream in = null;
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
            for (Entry<String, String> entry : keyMap.entrySet()) {
            	OSSObject object = ossClient.getObject(bucketName, entry.getKey());
                
                in = object.getObjectContent();
                
                WaterMarkUtils.mark(in, waterMarks, outputPath, entry.getValue());
            }
            
            //進行壓縮打包輸出
            File folder = new File(outputPath);
            File[] files = null;
            List<File> fileList = null;
            if (folder.exists() && folder.isDirectory()) {
            	files = folder.listFiles();

            	if (files != null) {
            		fileList = new ArrayList<File>();
            		fileList = Arrays.asList(files);
            	}
            }

            String downloadFileName = "productDM_" + System.currentTimeMillis() + ".zip";
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition","attachment; filename=" + downloadFileName);  
            
            this.zip(fileList, downloadFileName, response);

        } catch (OSSException oe) {
            log.error("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            log.error("Error Message: " + oe.getErrorCode());
            log.error("Error Code:       " + oe.getErrorCode());
            log.error("Request ID:      " + oe.getRequestId());
            log.error("Host ID:           " + oe.getHostId());
            isOutputed = false;
        } catch (ClientException ce) {
            log.error("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            log.error("Error Message: " + ce.getMessage());
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
    
    public static File zip(List<File> files, String filename, HttpServletResponse response) throws IOException {
        File zipfile = new File(filename);
        // Create a buffer for reading the files
        byte[] buf = new byte[1024];
        ZipOutputStream out = null;
        try {
            // create the ZIP file
            out = new ZipOutputStream(response.getOutputStream());
            // compress the files
            for(int i=0; i<files.size(); i++) {
                FileInputStream in = new FileInputStream(files.get(i).getCanonicalPath());
                // add ZIP entry to output stream
                out.putNextEntry(new ZipEntry(files.get(i).getName()));
                // transfer bytes from the file to the ZIP file
                int len;
                while((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                // complete the entry
                out.closeEntry();
                in.close();
            }
            
            return zipfile;
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            
        } finally {
        	if (out != null) {
        		// complete the ZIP file
                out.close();
        	}
        }
        return null;
    }
}
