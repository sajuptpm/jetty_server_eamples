package jtservercontinuation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import org.eclipse.jetty.continuation.Continuation;
import org.eclipse.jetty.continuation.ContinuationSupport;

public class MyCallbackHandler extends AbstractHandler {

	@Override
	public void handle(String arg0, Request request, HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) throws IOException, ServletException {

		request.setHandled(true);
		
		System.out.println( "in MyCallbackHandler.handle" );
		
		//Some code

		Continuation continuation = ContinuationSupport.getContinuation(servletRequest);
		continuation.setTimeout(20000);

		continuation.addContinuationListener(new MyContinuationListener());

		System.out.println( "in MyCallbackHandler.handle goint to suspend" );
		continuation.suspend(servletResponse);
		System.out.println( "in MyCallbackHandler.handle suspended" );

		//spawn this continuation to working thread
		MyThread mt = new MyThread(continuation);
		Thread nt = new Thread(mt);
		nt.start();
}

}


