package br.com.samptr.services.impl;

import java.net.URLDecoder;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import br.com.samptr.services.DownloadFileService;

@Service
public class DownloadFileServiceImpl implements DownloadFileService {
	
	
	private static final String KEY_AUTH = "&authkey=";
	private static final String RESID = "resid=";
    @Autowired
	private RestTemplate restTemplate;
	
	@Override
	public Resource dawnloadFile() {
	

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
		HttpEntity<String> entity = new HttpEntity<>(headers);

		String uri = "https://onedrive.live.com/download?resid=302233C08EBB1671%21107&authkey=AC3Xpx6Tw63sj8o";

		String resid = getResid("https://onedrive.live.com/embed?cid=302233C08EBB1671&resid=302233C08EBB1671%21107&authkey=AC3Xpx6Tw63sj8o");
		String keyAuht =  getKeyAuth("https://onedrive.live.com/embed?cid=302233C08EBB1671&resid=302233C08EBB1671%21107&authkey=AC3Xpx6Tw63sj8o");
		try {
			String url = URLDecoder.decode(uri, "UTF-8");
			ResponseEntity<Resource> response = restTemplate.exchange(url, HttpMethod.GET, entity, Resource.class);
		
			return response.getBody();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

	}

	private String getResid(String uri) {
		return uri.split(RESID)[1].split(KEY_AUTH)[0];
	}
	
	private String getKeyAuth(String uri) {
		return uri.split(KEY_AUTH)[1];
	}
}
