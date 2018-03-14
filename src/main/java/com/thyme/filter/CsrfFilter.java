package com.thyme.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//20160216 修复CSRF漏洞
public class CsrfFilter implements Filter{
    protected final Logger logger = LoggerFactory.getLogger(super.getClass());
    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub

        HttpServletRequest httpReq = (HttpServletRequest) request;
        String uri = httpReq.getRequestURI();
        logger.info("uri: " + uri);



        if(uri.contains("要拦截的路由")){

            DiskFileItemFactory fac = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(fac);
            //upload.setHeaderEncoding("utf-8");
            List fileList = null;
            try {
                fileList = upload.parseRequest(httpReq);
            } catch (FileUploadException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            String random_form = ((FileItem)fileList.get(0)).getString();   //获取directory参数

            //MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)(request);
            //String random_form=(String)httpReq.getAttribute("random_form"); //行不通
            String random_session=(String)httpReq.getSession().getAttribute("random_session_form");
            logger.info("random_form:"+random_form);
            logger.info("random_session:"+random_session);

            httpReq.getSession().removeAttribute("random_session_form");
            if(random_form!=null&&random_session!=null&&random_form.equalsIgnoreCase(random_session)){
                chain.doFilter(request, response);
            }else{
                throw new ServletException("Potential CSRF detected!!");
            }
        }else{
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }



}
