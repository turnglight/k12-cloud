package k12.base.web.client.version;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class ClientVersionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String versionType = request.getHeader(ClientVersion.CLIENT_VERSION_TYPE);
        String versionChannel = request.getHeader(ClientVersion.CLIENT_VERSION_CHANNEL);
        String versionName = request.getHeader(ClientVersion.CLIENT_VERSION_NAME);
        String vestNo = request.getHeader(ClientVersion.CLIENT_VERSION_NO);
        if(StringUtils.isEmpty(vestNo)){
            vestNo = "0";
        }

        if (StringUtils.hasText(versionType) && StringUtils.hasText(versionChannel) && StringUtils.hasText(versionName)) {
            ClientVersionContext.set(new ClientVersion(versionType, versionChannel, versionName, vestNo));
        }

        filterChain.doFilter(request, response);

        ClientVersionContext.clear();
    }

}
