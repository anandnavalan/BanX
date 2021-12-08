package com.tis.in.BanX.jwt;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tis.in.BanX.common.CommonMessageConstants;
import com.tis.in.BanX.common.ResponseBuilder;
import com.tis.in.BanX.common.Utility;

@RestController
@CrossOrigin(origins = { "http://localhost", "http://localhost:3000", "http://localhost:4200" })
public class JwtAuthenticationRestController {

	@Value("${jwt.http.request.header}")
	private String tokenHeader;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

	@RequestMapping(value = "${jwt.get.token.uri}", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtTokenRequest authenticationRequest)
			throws AuthenticationException {

//		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = jwtInMemoryUserDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		ResponseBuilder builder = Utility.responseBuilder(
				Utility.getLocalizedMessage(CommonMessageConstants.SUCCESS_USER_CREATION), HttpStatus.CREATED.value());

		return ResponseEntity.ok(new JwtTokenResponse(token));
	}

	@RequestMapping(value = "${jwt.refresh.token.uri}", method = RequestMethod.GET)
	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
		String authToken = request.getHeader(tokenHeader);
		final String token = authToken.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		JwtUserDetails user = (JwtUserDetails) jwtInMemoryUserDetailsService.loadUserByUsername(username);

		if (jwtTokenUtil.canTokenBeRefreshed(token)) {
			String refreshedToken = jwtTokenUtil.refreshToken(token);
			return ResponseEntity.ok(new JwtTokenResponse(refreshedToken));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

//	@RequestMapping("/logout")
//	public void exit(HttpServletRequest request, HttpServletResponse response) {
//		// token can be revoked here if needed
//		new SecurityContextLogoutHandler().logout(request, null, null);
//		try {
//			// sending back to client app
//			response.sendRedirect(request.getHeader("referer"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

//	@RequestMapping(value = "/oauth/logout", method = RequestMethod.POST)
//	public ResponseEntity<String> revoke(HttpServletRequest request) {
//		try {
//			String authorization = request.getHeader("Authorization");
//			if (authorization != null && authorization.contains("Bearer")) {
//				String tokenValue = authorization.replace("Bearer", "").trim();
//
//				OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
//				tokenStore.removeAccessToken(accessToken);
//
//				// OAuth2RefreshToken refreshToken = tokenStore.readRefreshToken(tokenValue);
//				OAuth2RefreshToken refreshToken = accessToken.getRefreshToken();
//				tokenStore.removeRefreshToken(refreshToken);
//			}
//		} catch (Exception e) {
//			return ResponseEntity.badRequest().body("Invalid access token");
//		}
//
//		return ResponseEntity.ok().body("Access token invalidated successfully");
//	}

	@ExceptionHandler({ AuthenticationException.class })
	public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	}

	private void authenticate(String username, String password) {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new AuthenticationException("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new AuthenticationException("INVALID_CREDENTIALS", e);
		}
	}
}