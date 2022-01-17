package org.jht.controller;

import java.io.File;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController{
	@GetMapping("upload")
	public void uploadForm() {
	
		System.out.println("파일 업로드 화면..");
	}
	
	@GetMapping("uploadAjax")
	public void uploaAjaxdForm() {
	
		System.out.println("파일 업로드 화면..");
	}
	
	// upload.jsp에서 ,form태그를 이용하여 파일 업로드
	@PostMapping("uploadAction")
	public void uploadAction(MultipartFile[] uploadFile) {
		
		// 파일 업로드 할 경로 지정 
		
		String uploadFolder="D:\\upload";
		for(MultipartFile  multipartFile : uploadFile) {
		// 사용자가 업로드 한 실제 파일 이름	
		System.out.println("업로드 파일이름 = "+ multipartFile.getOriginalFilename());
		// 사용자가 업로드 한 실제 파일의 크기
		System.out.println("업로드 파일 크기 = "+ multipartFile.getSize());
		//사용자가 업로드 한 실제 파일의 형식
		System.out.println("업로드 파일 형식 = "+ multipartFile.getContentType());
		// uploadFolder에 저장되어 있는 경로로 실제 파일명으로 저장
		File saveFile=new File(uploadFolder,multipartFile.getOriginalFilename());
	
			try {
				multipartFile.transferTo(saveFile);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// end try
		
		} // for문 end
	
	}	
	// uploadAjax.jsp에서 ,ajax를 이용하여 파일 업로드 controller
	// upload.jsp에서 ,form태그를 이용하여 파일 업로드
		@PostMapping("uploadAjaxAction")
		public void uploadAjaxAction(MultipartFile[] uploadFile) {
			
			// 파일 업로드 할 경로 지정 
			
			String uploadFolder="D:\\upload";
			for(MultipartFile  multipartFile : uploadFile) {
			// 사용자가 업로드 한 실제 파일 이름	
			System.out.println("업로드 파일이름 = "+ multipartFile.getOriginalFilename());
			// 사용자가 업로드 한 실제 파일의 크기
			System.out.println("업로드 파일 크기 = "+ multipartFile.getSize());
			//사용자가 업로드 한 실제 파일의 형식
			System.out.println("업로드 파일 형식 = "+ multipartFile.getContentType());
			// uploadFolder에 저장되어 있는 경로로 실제 파일명으로 저장
			File saveFile=new File(uploadFolder,multipartFile.getOriginalFilename());
		
				try {
					multipartFile.transferTo(saveFile);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}// end try
			
			} // for문 end
		
		}	
}