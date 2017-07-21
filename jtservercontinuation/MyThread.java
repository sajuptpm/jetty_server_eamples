package jtservercontinuation;

import java.io.IOException;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.continuation.Continuation;

public class MyThread implements Runnable{

	private Continuation continuation;

	public MyThread(Continuation continuation) {
		this.continuation = continuation;
	}
	
	@Override
	public void run() {
		
		try {
			System.out.println("Sleep start....");
			Thread.sleep(10000);
			System.out.println("Sleep end....");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ServletResponse response = this.continuation.getServletResponse();
        response.setContentType("text/html;charset=utf-8");

        try {
			response.getWriter().println("<h1>Task Completed</h1>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		this.continuation.complete();
	}
	}
