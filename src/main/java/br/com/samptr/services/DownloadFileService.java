package br.com.samptr.services;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public interface DownloadFileService {
	Resource dawnloadFile(String hashUri);
}
