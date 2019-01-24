package io.swagger.api;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.swagger.model.Host;

@Service
public class HostApiDelegateService implements HostApiDelegate {

	@Override
	public ResponseEntity<Host> host() {
		Host host = new Host();
		try {
			host.setName(InetAddress.getLocalHost().getHostName());
			host.setIp(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Host>(host, HttpStatus.OK);
	}

}
