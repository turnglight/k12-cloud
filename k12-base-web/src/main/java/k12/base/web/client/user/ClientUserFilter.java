package k12.base.web.client.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class ClientUserFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String userId = request.getHeader(ClientUser.CLIENT_USER_ID);
        String userName = request.getHeader(ClientUser.CLIENT_USER_NAME);
        if (StringUtils.hasText(userId) && StringUtils.hasText(userName)) {
            ClientUserContext.set(new ClientUser(Integer.valueOf(userId), userName));
        }

        filterChain.doFilter(request, response);

        ClientUserContext.clear();
    }

}
