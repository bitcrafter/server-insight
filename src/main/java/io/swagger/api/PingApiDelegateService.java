package io.swagger.api;

import java.io.IOException;
import java.net.InetAddress;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.swagger.model.Ping;

@Service
public class PingApiDelegateService implements PingApiDelegate {

	@Override
	public ResponseEntity<Ping> ping(String host) {
		boolean reachble = false;
		try {
			reachble = InetAddress.getByName(host).isReachable(1000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Ping ping = new Ping();
		ping.setHost(host);
		ping.setReachble(reachble);
		return new ResponseEntity<>(ping, HttpStatus.OK);
	}

}
