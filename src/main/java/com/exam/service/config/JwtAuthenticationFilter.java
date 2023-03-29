package com.exam.service.config;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;

import com.exam.service.impl.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	private JwtUtils jwtUtils;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String requestTokenHeader = request.getHeader("Authorization");
		System.out.println(requestTokenHeader);

		String username = null;
		String jwtToken = null;

		if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer "))
		{
			jwtToken = requestTokenHeader.substring(7);
			
			try {
				username = this.jwtUtils.extractUsername(jwtToken);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(username);
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
			{
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		else
		{
			System.out.println("token is not validated");
		}
	
		
			filterChain.doFilter(request, response);
		} 
	}


