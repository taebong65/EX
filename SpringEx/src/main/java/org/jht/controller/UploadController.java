package org.jht.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.jht.domain.AttachFileDTO;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnailator;

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
		// 년/월/일 폴더의 생성
		private String getFolder() {
			//현재 날짜를 추출(Tue Jan 18:09:34:09 KST 2022)
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
			// (Tue Jan 18:09:34:09 KST 2022)
			Date date = new Date();
			String str=sdf.format(date);
			
			
			return str.replace("-",File.separator);
		}
		// 썸네일 이미지생성을 할 것인지 안할것인지에 대해 판단하는 메소드(사용자가 업로드 한 파일이 이미지이면 생성, 그렇지 않으면 생서안함)
	
		private boolean checkImage(File file) {
			try {
				// 파일의 타입을 알아내는 probeContentType메소드 호출하여 사용
				String contentType = Files.probeContentType(file.toPath());
				// 그 파일의 타입이 image이며 true,그렇지 않으면 false
				 return contentType.startsWith("image");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			
			
			
			return false;
		}
		
		
		
	// uploadAjax.jsp에서 ,ajax를 이용하여 파일 업로드 controller
	// upload.jsp에서 ,form태그를 이용하여 파일 업로드
		@PostMapping(value="uploadAjaxAction",produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
		public ResponseEntity<ArrayList<AttachFileDTO>> uploadAjaxAction(MultipartFile[] uploadFile) {
			
			//AttachFileDTO 에 저장되는 값이 여러파일에 대한 값이면 배열로 처리가 되어야 하므로 ArrayList타입이 되어야함
			ArrayList<AttachFileDTO> list = new ArrayList<>();
			
			// 파일 업로드 할 경로 지정 
			
			String uploadFolder="D:\\upload";
			
			
			
			// 폴더 생성                              (기존폴더            현재폴더)를 결합
			File uploadPath = new File(uploadFolder,getFolder());
			
			
			String uploadFolderPath=getFolder();
			
				System.out.println("uploadPath="+uploadPath);
				
			//현재 만들려고 하는 폴더가 없으면 
			if(uploadPath.exists()==false) {
				//폴더 생성
				uploadPath.mkdirs();
			}
			for(MultipartFile  multipartFile : uploadFile) {
				
				AttachFileDTO attachdto = new  AttachFileDTO();
				// UploadController에 있는 uploadAjaxAction에서 AttachFileDTO를 사용해서 값을 저장해야 되는데
				// 이럴경우 UploadController에 AttachFileDTO가 없으면 사용을 할 수가 없습니다.
				// 그래서 UploadController에 AttachFileDTO 포함을 시켜서 사용하여 값을 저장함 
			// 사용자가 업로드 한 실제 파일 이름	
			System.out.println("업로드 파일이름 = "+ multipartFile.getOriginalFilename());
			// 사용자가 업로드 한 실제 파일의 크기
			System.out.println("업로드 파일 크기 = "+ multipartFile.getSize());
			//사용자가 업로드 한 실제 파일의 형식
			System.out.println("업로드 파일 형식 = "+ multipartFile.getContentType());
			// 실제 파일명을 저장 
			String uploadFileName=multipartFile.getOriginalFilename();
			// 중복이 되지 않는 임의의 문자열을 생성 
			
			//실제 파일명(uploadFileName)을 AttachFileDTO클래스(attachdto)에 fileName에 저장 
			attachdto.setFileName(uploadFileName);
			
			
			UUID uuid = UUID.randomUUID();
			// UUID + "_" + getOriginalFilename()의 조합으로 파일명을 uploadFileName에 저장 
			uploadFileName=uuid.toString()+"_"+uploadFileName;
			
			// uploadFolder에 저장되어 있는 경로로 실제 파일명으로 저장
			
		
				try {
					File saveFile=new File(uploadPath,multipartFile.getOriginalFilename());
					// saveFile 변수에 저장되어 있는 폴더명으로 파일을 보내라.
					multipartFile.transferTo(saveFile);
					
					
					//실제 업로드경로(uploadFileName)을 AttachFileDTO클래스(attachdto)에 fileName에 저장(setFileName)
					
					attachdto.setUploadPath(uploadFolderPath);
						
					//uuid값(UUID)을 AttachFileDTO클래스(attachdto)에 uploadPath에 저장 (setUploadPath)
					
					attachdto.setUuid(uuid.toString());
					
					// 이미지 파일이면 
					if(checkImage(saveFile)) {
					//FileType값(image)을 AttachFileDTO클래스(attachdto)에 uploadPath에 저장 (setImage)
						
						attachdto.setImage(true);
					//썸네일 파일을 생성하기 전에 썸네일 파일 이름을 추출
						FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath,"s_"+uploadFileName));
					// 썸네일 파일을 생성함. 
						Thumbnailator.createThumbnail(multipartFile.getInputStream(),thumbnail,100,100);
					// 썸네일 종료(메모리 공간 회수)	
						thumbnail.close();
					}
					list.add(attachdto);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}// end try
			
			} // for문 end
			 //통신 상태가 정상적(HttpStatus.OK)이면 ArrayList(list)에 저장되어 있는 값을 uploadAjax.js에 있는 ajax에  success로 보내라.
			return new ResponseEntity<>(list,HttpStatus.OK);
		}	
		// 파일 업로드 한 파일타입이 이미지일때(.jpg,.png 웹브라우저에 이미지를 띄우기 위해서....
		@GetMapping("display")
		public ResponseEntity<byte[]> getFile(String fileName) { // getFile()은 문자열로 파일의 경로가 포함된 fileName을 매개변수를 받고  byte[](이진수)를 전송
			System.out.println("url주소를 통한 fileName="+fileName);
			File file = new File("D:\\upload\\"+fileName);
			
			System.out.println("file="+file);
			
			ResponseEntity<byte[]> result = null;
			// byte[]로 이미지 파일의 데이터를 전송할 때 브라우저에 보내는 MIME타입이 파일의 종류(JPG,PNG,XLS,PPT에 따라 달라진다.
			// 이 부분을  해결하기 위해서 probeContentType()을 이용해서 적절한 MIME 타입 데이터를 Http의 헤더 메세지에 포함 할 수 있도록 처리 
			
			
			try {
				HttpHeaders header = new HttpHeaders();
				//header.add("Content-Type", Files.probeContentType(file.toPath()));
				result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),header,HttpStatus.OK);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return result;
		}
		// 파일 업로드 한 파일타입이 이미지가 아닐 때(.xls,.ppt,.txt) 웹브라우저통해서 다운로드 할 수 잇도록...
		// 댓글 쓰기를 하기위한 RequestMapping
		// Mapping을 할 때 우리가 원하는 데이터  타입을 강제함으로써 오류상황을 줄일 수 있다.
		// consumes : 들어오는 데이터 타입정의
		// produces : 반환하는 데이터 타입 정의
		//* 생략을 하게되면, 웹브라우저가 알아서 타입을 판단*
		// 웹 브라우저가 이 파일은 download해야 하는 파일입니다. 라는 것을 인지할 수 있도록 봔환이 되이야 합니다.
		// 그러거ㅣ 위해서는 APPLICATION_OCTET_STREAM_VALUE 타입으로 반환 데이터 타입을 선언합니다. 
		
		@GetMapping(value="download",produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
		public ResponseEntity<Resource> downloadFile(String fileName){
			System.out.println("download fileName"+fileName);
			
			Resource resource = new FileSystemResource("D:\\upload\\"+fileName);
			System.out.println("download resource="+resource);
			
			String resourceName = resource.getFilename();
			HttpHeaders header = new HttpHeaders();
			
			try {
				
				header.add("Content-Disposition", "attachment; fileName="+new String(resourceName.getBytes("UTF-8"),"ISO-8859-1"));
			}catch(Exception e) {
				e.printStackTrace();
			}
			return new ResponseEntity<Resource>(resource,header,HttpStatus.OK);
		}
}