package br.com.samptr.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.samptr.services.DownloadFileService;

@RestController
@RequestMapping("download")
public class DownloadFileController {
	
	@Autowired
	private DownloadFileService service;
	
	
    @GetMapping("{hash}")
	public HttpEntity<Resource> downloadFile(@PathVariable("hash") String hash,HttpServletRequest request) {
    	
    	Resource resource = service.dawnloadFile(hash);
    	
    	String contentType = null;
    	
    	try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (Exception e) {
		}
    	
    	if(contentType == null) {
    		contentType = "application/octet-stream";
    	}
	
    	return ResponseEntity.ok()
    			.contentType(MediaType.parseMediaType(contentType))
    			.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+resource.getFilename()+"\"")
    			.body(resource);
	}
    
    
   
}
