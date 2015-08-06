package com.joyfulmath.androidstudy.thread.messagemachine;

/*store a message with id & object ..
 * 
 * */
public class Message {
	public MsgHandler target;
	public int what;
	@Override
	public String toString() {
		return "message:"+what;
	}
	
	
}
