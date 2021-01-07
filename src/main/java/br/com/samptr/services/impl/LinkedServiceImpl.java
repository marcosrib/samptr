package br.com.samptr.services.impl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.samptr.models.LinkedEntity;
import br.com.samptr.repositories.LinkedRepository;
import br.com.samptr.services.LinkedService;

@Service
public class LinkedServiceImpl implements LinkedService {

	@Autowired
	private LinkedRepository respository;

	@Override
	public LinkedEntity save(LinkedEntity linkedEntity) {
		 linkedEntity.setHashUri(generateHash());
		return respository.save(linkedEntity);
	}

	private String generateHash() {
   
		try {
			String stringHash = LocalDateTime.now().toString();
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(stringHash.getBytes(), 0, stringHash.length());
			String hash = new BigInteger(1, m.digest()).toString(16);
			System.out.println("MD5: " + hash);

			return hash;
		} catch (NoSuchAlgorithmException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

	}

}
