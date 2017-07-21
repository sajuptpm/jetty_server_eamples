package jtservercontinuation;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.Handler;

public class Jtserver {

	private Server server;	
	
    public void startServer() throws Exception
    {
        server = new Server();
        
        ServerConnector connector = new ServerConnector(server);
        connector.setHost("127.0.0.1");
        connector.setPort(8080);
        
        Connector[] connectors = new Connector[] {connector};
        server.setConnectors(connectors);
        
        ContextHandlerCollection contexts = new ContextHandlerCollection();
        
        //Create context1 and add path and callback handler
        ContextHandler myContext = new ContextHandler();
        myContext.setContextPath("/site");
        Handler myCallbackHandler = new MyCallbackHandler();
        myContext.setHandler(myCallbackHandler);
        
        //Ass all contexts to context collection
        contexts.addHandler(myContext);
        
        
        HandlerCollection handlers = new HandlerCollection();
        handlers.setHandlers(new Handler[] { contexts, new DefaultHandler() });
        
        server.setHandler(handlers);      
        
        //server.setHandler(new HelloJettyHandler());
        server.start();
        System.out.println( "Jtserver Started" );
        server.join();
    }
	
}
