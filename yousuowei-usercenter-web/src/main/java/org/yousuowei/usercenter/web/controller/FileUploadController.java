package org.yousuowei.usercenter.web.controller;
//package you.suo.wei.pm.web.controller;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import net.sf.json.JSONSerializer;
//
//import org.apache.log4j.Logger;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.FileCopyUtils;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//import org.springframework.web.multipart.commons.CommonsMultipartFile;
//
//import com.baidu.inf.iis.bcs.BaiduBCS;
//import com.baidu.inf.iis.bcs.auth.BCSCredentials;
//import com.baidu.inf.iis.bcs.model.BCSClientException;
//import com.baidu.inf.iis.bcs.model.BCSServiceException;
//import com.baidu.inf.iis.bcs.model.ObjectMetadata;
//import com.baidu.inf.iis.bcs.model.X_BS_ACL;
//import com.baidu.inf.iis.bcs.request.PutObjectRequest;
//import com.baidu.inf.iis.bcs.response.BaiduBCSResponse;
//import com.chat.util.CreateFileUtil;
//import com.chat.util.PropertiesUtil; 
//import com.chat.util.StringUtil;
//
///**
// * @author Mr.hu
// * @version crateTime：2013-7-27 上午11:33:52
// * Class Explain: 文件上传
// */
//@Controller
//@RequestMapping(value = "/upload")
//public class FileUploadController {
//
//	
//	private Logger log = Logger.getLogger(this.getClass());
//	// ----------------------------------------
//	static String host = "bcs.duapp.com";
//	static String accessKey = "C31065438d5b22d3be1f02edec6b6ac8";
//	static String secretKey = "BD23d6f562a33d0a0331608fca2ff979"; 
//	static String bucket = "fk-chat";
//	// ---------------------------------------- 
//	
//	@RequestMapping(value = "sendMessage.do")
//	public void sendMessage(HttpServletRequest request, HttpServletResponse response) {
//		// /////////////////////////////////////////////////
//		OutputStream out = null;
//		try {
//			out = response.getOutputStream();
//		} catch (IOException e1) {
//			log.error("***** 获取OUT出错 *****");
//			e1.printStackTrace();
//		}
//		int errorCode = 0;
//		String newFileName ="";
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		// /////////////////////////////////////////////////
//		String downLoad="http://bcs.duapp.com/fk-chat/";
//		String sign="MBO:C31065438d5b22d3be1f02edec6b6ac8:kdWptm3iSvmAcEqBKoQmvNRfKMw%3D";  
//		
//		try {
//			MultipartHttpServletRequest multipartRequest =(MultipartHttpServletRequest) request;
//			//file 对象
//			CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("file");
//			String realFileName = file.getOriginalFilename();// 获得文件名
//			newFileName = StringUtil.getByNewFileName() + realFileName.substring(realFileName.indexOf("."));
//			//百度云存储
//			BCSCredentials credentials = new BCSCredentials(accessKey, secretKey);
//			BaiduBCS baiduBCS = new BaiduBCS(credentials, host);
//			baiduBCS.setDefaultEncoding("UTF-8"); // Default UTF-8
//			//获取inputstream
//			InputStream fileContent = file.getInputStream();
//			ObjectMetadata objectMetadata = new ObjectMetadata();
//			objectMetadata.setContentType("text/html");
//			objectMetadata.setContentLength(file.getSize());   
//			PutObjectRequest porequest = new PutObjectRequest(bucket, "/"+newFileName, fileContent, objectMetadata);
//			//设置文件访问权限
//			porequest.setAcl(X_BS_ACL.PublicRead);  
//			//上传并返回结果
//			ObjectMetadata result = baiduBCS.putObject(porequest).getResult();
//			log.info(result);
//			//文件下载路径
//			downLoad=downLoad+newFileName+"?sign="+sign;
//		}catch (BCSServiceException e) {
//			log.warn("Bcs return:" + e.getBcsErrorCode() + ", " + e.getBcsErrorMessage() + ", RequestId=" + e.getRequestId());
//		} catch (BCSClientException e) { 
//			errorCode = 200;
//			e.printStackTrace();
//		} catch (IOException e) { 
//			e.printStackTrace();
//		}
//
//		// /////////////////////////////////////////////////
//		if (errorCode != 0) {
//			resultMap.put("status", -1);
//			resultMap.put("errorCode", errorCode);
//			log.info("方法：" + Thread.currentThread().getStackTrace()[1].getMethodName() + "调用出错。错误码：" + errorCode);
//		} else { 
//			resultMap.put("status", 1);
//			resultMap.put("downLoad", downLoad);  
//		}
//		try {
//			out.write(JSONSerializer.toJSON(resultMap).toString().getBytes("UTF-8"));
//			out.flush();
//			out.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		// /////////////////////////////////////////////////
//		
//		
//	}
//	
//	
//
//	@RequestMapping(value = "fileUpload.do")
//	public void fileUpload(HttpServletRequest request, HttpServletResponse response) {
//		// /////////////////////////////////////////////////
//		OutputStream out = null;
//		try {
//			out = response.getOutputStream();
//		} catch (IOException e1) {
//			log.error("***** 获取OUT出错 *****");
//			e1.printStackTrace();
//		}
//		int errorCode = 0;
//		String newFileName ="";
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		// /////////////////////////////////////////////////
//	
//		try {
//			MultipartHttpServletRequest multipartRequest =(MultipartHttpServletRequest) request;
//			CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("file");
//			String realFileName = file.getOriginalFilename();// 获得文件名
//			newFileName = StringUtil.getByNewFileName() + realFileName.substring(realFileName.indexOf("."));
//			// 获取路径
//			String folderPath = PropertiesUtil.getUrl("uploadURL");// 获取文件夹路径
//			CreateFileUtil.createDir(folderPath); 
//			
//			File uploadFile = new File(folderPath+newFileName); 
//			FileCopyUtils.copy(file.getBytes(), uploadFile);
//			
//		} catch (Exception e) {
//			errorCode = 200;
//			e.printStackTrace();
//		}
//
//		
//		// /////////////////////////////////////////////////
//		if (errorCode != 0) {
//			resultMap.put("status", -1);
//			resultMap.put("errorCode", errorCode);
//			log.info("方法：" + Thread.currentThread().getStackTrace()[1].getMethodName() + "调用出错。错误码：" + errorCode);
//		} else { 
//			resultMap.put("status", 1);
//			resultMap.put("fileName", newFileName); 
//		}
//		try {
//			out.write(JSONSerializer.toJSON(resultMap).toString().getBytes("UTF-8"));
//			out.flush();
//			out.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		// /////////////////////////////////////////////////
//		
//		
//	}
//	
//	
//	
//}
