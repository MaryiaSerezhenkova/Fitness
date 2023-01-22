package by.academy.fitness.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class CorsFilter extends OncePerRequestFilter {

	private static final String EXPOSE_HEADERS = "Token, X-AMOUNT-BUGS, X-AMOUNT-COMMENTS";
	private static final String ALLOW_HEADERS = "Content-Type, Accept, Authorization, Info, Token, Amount, X-Requested-With, Tenant, Pragma";
	private static final String ALLOW_METHODS = "POST, GET, OPTIONS, DELETE, PUT, PATCH";
	private static final String ALLOW_CREDENTIALS = "true";
	private static final String MAX_AGE = "3600";

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {

		httpServletResponse.addHeader("Access-Control-Allow-Credentials", ALLOW_CREDENTIALS);
		httpServletResponse.addHeader("Access-Control-Expose-Headers", EXPOSE_HEADERS);
		if (httpServletRequest.getHeader("Access-Control-Request-Method") != null
				&& "OPTIONS".equals(httpServletRequest.getMethod())) {
			httpServletResponse.addHeader("Access-Control-Allow-Methods", ALLOW_METHODS);
			httpServletResponse.addHeader("Access-Control-Max-Age", MAX_AGE);
			httpServletResponse.addHeader("Access-Control-Allow-Headers", ALLOW_HEADERS);
			return;
		}

		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
}