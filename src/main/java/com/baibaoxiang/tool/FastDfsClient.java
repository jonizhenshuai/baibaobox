package com.baibaoxiang.tool;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.stereotype.Component;

/**
 * @author chenlin
 */
@Component
public class FastDfsClient {
	private TrackerClient trackerClient = null;
	private TrackerServer trackerServer = null;
	private StorageServer storageServer = null;
	private StorageClient1 storageClient = null;
	
	public FastDfsClient() throws Exception {
		String path =this.getClass().getResource("/properties/client.conf").getPath();
		ClientGlobal.init(path);
		trackerClient = new TrackerClient();
		trackerServer = trackerClient.getConnection();
		//给dfs发送一个消息
		ProtoCommon.activeTest(trackerServer.getSocket());
		storageServer = null;
		storageClient = new StorageClient1(trackerServer, storageServer);
	}
	
	/**
	 * 上传文件方法
	 * <p>Title: uploadFile</p>
	 * <p>Description: </p>
	 * @param fileName 文件全路径
	 * @param extName 文件扩展名，不包含（.）
	 * @param metas 文件扩展信息
	 * @return
	 * @throws Exception
	 */
	public String uploadFile(String fileName, String extName, NameValuePair[] metas) throws Exception {
		return storageClient.upload_file1(fileName, extName, metas);
	}
	
	public String uploadFile(String fileName) throws Exception {
		return uploadFile(fileName, null, null);
	}
	
	public String uploadFile(String fileName, String extName) throws Exception {
		return uploadFile(fileName, extName, null);
	}
	
	/**
	 * 上传文件方法
	 * <p>Title: uploadFile</p>
	 * <p>Description: </p>
	 * @param fileContent 文件的内容，字节数组
	 * @param extName 文件扩展名
	 * @param metas 文件扩展信息
	 * @return
	 * @throws Exception
	 */
	public String uploadFile(byte[] fileContent, String extName, NameValuePair[] metas) throws Exception {
		  return storageClient.upload_file1(fileContent, extName, metas);
	}
	
	public String uploadFile(byte[] fileContent) throws Exception {
		return uploadFile(fileContent, null, null);
	}

	/**
	 * @param fileContent 文件字节流
	 * @param extName 文件扩展名
	 * @return
	 * @throws Exception
	 */
	public String uploadFile(byte[] fileContent, String extName) throws Exception {
		return uploadFile(fileContent, extName, null);
	}


	/**
	 * @param fileName 组名+文件名
	 * @throws Exception
	 */
	public void deleteFile( String fileName) throws Exception{
		 storageClient.delete_file1(fileName);
	}
}
