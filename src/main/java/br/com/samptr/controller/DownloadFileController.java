package br.com.samptr.controller;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.samptr.services.DownloadFileService;

@RestController
@RequestMapping("download")
public class DownloadFileController {
	
	@Autowired
	private DownloadFileService service;
	
	
    @GetMapping
	public HttpEntity<Resource> downloadFile(HttpServletRequest request) {
    	
    	Resource resource = service.dawnloadFile();
    	
    	String contentType = null;
    	
    	try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    	if(contentType == null) {
    		contentType = "application/octet-stream";
    	}
    	System.err.println(resource.getFilename());
    	
   String nome = "foto.png";

    	
    	return ResponseEntity.ok()
    			.contentType(MediaType.parseMediaType(contentType))
    			.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+nome+"\"")
    			.body(resource);
	}
    
    
   
}
