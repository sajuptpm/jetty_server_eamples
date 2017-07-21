/*
 * 
 * http://www.eclipse.org/jetty/documentation/9.3.x/continuations-using.html
 * 
 * 
 * 
 */

package jtservercontinuation;

import org.eclipse.jetty.continuation.Continuation;
import org.eclipse.jetty.continuation.ContinuationListener;

public class MyContinuationListener implements ContinuationListener {

	@Override
	public void onComplete(Continuation cont) {
		// some code
		System.out.println( "In MyContinuationListener.onComplete" );
	}

	@Override
	public void onTimeout(Continuation cont) {
		// some code
		System.out.println( "In MyContinuationListener.onTimeout" );
		//cont.suspend();
	}
	
}