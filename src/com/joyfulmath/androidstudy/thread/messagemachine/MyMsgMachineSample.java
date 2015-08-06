package com.joyfulmath.androidstudy.thread.messagemachine;

import com.joyfulmath.androidstudy.TraceLog;

public class MyMsgMachineSample extends MessageHandlerThread{
	
	MyHandler mhandler = null;
		
	@Override
	protected void onPrepared() {
		super.onPrepared();
		mhandler = new MyHandler(this.getMessageQueue());
		TraceLog.i();
	}

	public void doAction(int action)
	{
		TraceLog.i("action:"+action);
		Message msg = new Message();
		msg.what = action;
		mhandler.sendMessage(msg);
	}

	public static class  MyHandler extends MsgHandler{

		public MyHandler(MessageQueue mQueue) {
			super(mQueue);
		}

		@Override
		protected void onHandleMessage(Message msg) {
			TraceLog.i("action:"+msg.what);
		}
		
		
	}
}
