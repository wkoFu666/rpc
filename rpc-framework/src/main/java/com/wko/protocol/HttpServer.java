package com.wko.protocol;

import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

/**
 * ClassName: HttpServer
 * Package: com.wko.protocol
 * Description:
 *
 * @Author wko
 * @Create 2022/12/7 20:45
 * @Version 1.0
 */
public class HttpServer {

    public static final String TOMCAT = "Tomcat";
    public static final String DISPATCHER = "dispatcher";

    /**
     * java方式启动Tomcat
     *
     * @param hostname 主机名称
     * @param port     端口
     */
    public void start(String hostname, Integer port) {
        //读取用户配置，形如配置文件中的server.name,，或者启动参数中的配置信息，亦或是配置中心的配置信息

        Tomcat tomcat = new Tomcat();

        Server server = tomcat.getServer();
        Service service = server.findService(TOMCAT);

        Connector connector = new Connector();
        connector.setPort(port);

        Engine engine = new StandardEngine();
        engine.setDefaultHost(hostname);

        Host host = new StandardHost();
        host.setName(hostname);

        String ContextPath = "";
        Context context = new StandardContext();
        context.setPath(ContextPath);
        context.addLifecycleListener(new Tomcat.FixContextListener());

        host.addChild(context);
        engine.addChild(host);

        service.setContainer(engine);
        service.addConnector(connector);

        //处理请求
        tomcat.addServlet(ContextPath, DISPATCHER,new DispatcherServlet());
        context.addServletMappingDecoded("/*",DISPATCHER);

        try {
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }
}
