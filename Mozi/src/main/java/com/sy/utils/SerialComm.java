package com.sy.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class SerialComm {

	/**
	 * 打开串口
	 * 
	 * @param portName
	 *            串口名
	 * @param waverate
	 *            波特率
	 */
	public void connect(String portName, int waverate) {
		CommPortIdentifier portIdentifier = null;
		try {
			portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
			if (portIdentifier.isCurrentlyOwned()) {
				System.out.println("Error: Port is currently in use");
			} else {
				CommPort commPort = null;
				commPort = portIdentifier.open(this.getClass().getName(), 2000);
				if (commPort instanceof SerialPort) {
					SerialPort serialPort = (SerialPort) commPort;
					serialPort.setSerialPortParams(waverate,
							SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
							SerialPort.PARITY_NONE);
					InputStream in = serialPort.getInputStream();
					OutputStream out = serialPort.getOutputStream();
					serialPort.addEventListener(new SerialReader(in, out));
					serialPort.notifyOnDataAvailable(true);
				}
			}
		} catch (Exception e) {
			//System.out.println(e.getMessage());
		}
	}

	/**
	 * 处理输入来自串行端口，启动一个监听器
	 * 
	 * @author suyunlong
	 *
	 */
	public static class SerialReader implements SerialPortEventListener {
		private InputStream in;
		private OutputStream out;
		private byte[] buffer = new byte[1024];

		public SerialReader(InputStream in, OutputStream out) {
			this.in = in;
			this.out = out;
		}

		public void serialEvent(SerialPortEvent arg0) {
			boolean rt = true;
			int data;
			try {
				int len = 0;
				while ((data = in.read()) > -1) {
					if (data == '\n') {
						break;
					}
					buffer[len++] = (byte) data;
				}
				System.out.println("硬件数据===="+new String(buffer, 0, len));
				String sts = new String(buffer, 0, len);
				if (sts.subSequence(0, 1).equals("j")) {
					String s = sts.substring(1, sts.length());
					System.out.println("硬件打印的数据==========="+s);
					rt=false;
				}else if(sts.subSequence(0, 1).equals("Z")) {
					System.out.println("程序已经结束。。。。。。。。。。。。。。。");
				}else {
					if(rt){
						if(sts.equals("7FFF6F61")){
							out.write("6a82".getBytes());
							System.out.println("响应硬件端数据====6a82");
						}else {
							String stx = Flowtcp.folwoperating(null, sts);
							String S =stx.substring(10, stx.length())+"Z";
							out.write(S.getBytes());
							System.out.println("响应硬件端数据====" + S);
						}
				
					}
				}
			} catch (IOException e) {
				//System.out.println(e.getMessage());
				System.exit(-1);
			}
		}
	}

	public static void main(String[] args) {
		(new SerialComm()).connect("COM4", 921600);
	}
}
