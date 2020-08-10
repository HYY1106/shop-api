package com.fh.shop.api.common;

import javax.servlet.annotation.WebFilter;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

@WebFilter(
        filterName = "webFilter",
        urlPatterns = {"/api/*"}
)
public class WebContextFilter implements Filter {

    public void destroy(){

    }

   /* public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws HandlerException {
        try {
            WebContext.set((HttpServletRequest) req);
            chain.doFilter(req,resp);
        } finally {
            //删除
            WebContext.del();
        }
    }*/



    @Override
    public boolean isLoggable(LogRecord record) {
        return false;
    }
}
