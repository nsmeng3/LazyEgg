package io.lazyegg.util;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtUtils {
    private static final String SECRET = "XX#$%()(#*!()!KL<><MQLMNQNQJQK sdfkjsdrow32234545fdf>?N<:{LWPW";
    private static final String APP_ID = "xxxxxxxxxxxxxxxxx";
    private static final String APP_SECRET = "cccxxxxxxxxxxxxx555555555555555ccc";

    public static String createJWT(String secret, Map<String, Object> claims) {
        String key1 = DigestUtils.md5DigestAsHex(secret.getBytes(StandardCharsets.UTF_8));
        secret = key1 + key1;
        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder()
            .setIssuedAt(now)
            .setClaims(claims)
            .signWith(signingKey);

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();

    }

    public static Claims parseJWT(String jwt, String secret) {
        String key1 = DigestUtils.md5DigestAsHex(secret.getBytes(StandardCharsets.UTF_8));
        secret = key1 + key1;
        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
            .build().parseClaimsJws(jwt).getBody();
        return claims;
    }

}
