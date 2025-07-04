package com.SaharaAmussmentPark.Serviceimpl;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class OTPGenerateService {
	private static final Integer EXPIRE_MINS = 10;
	private final LoadingCache<String, String> otpCache;

	public OTPGenerateService() {
		super();
		otpCache = CacheBuilder.newBuilder().expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES)
				.build(new CacheLoader<String, String>() {
					public String load(final String key) {
						return null;
					}
				});
	}

	public String generateOTP(String key) {
		Random random = new Random();
		StringBuilder otp = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			otp.append(random.nextInt(5));
		}
		otpCache.put(key, otp.toString());
		return otp.toString();
	}

	public String getOtp(String key) {
		try {
			return otpCache.get(key);
		} catch (Exception e) {
			return null;
		}
	}

	public void clearOTP(String key) {
		otpCache.invalidate(key);
	}
}
