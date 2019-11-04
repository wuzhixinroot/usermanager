package com.wuzhixin.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//seven禁止浏览器缓存随机图片
		response.setDateHeader("Expires", -1);//ie
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Pragma", "no-cache");
		
		//six 通知客户机以图片方式打开
		response.setHeader("Content-Type", "img/jpeg");
		
		//one 在内存中创建一张图片
		BufferedImage image = new BufferedImage(80,30,BufferedImage.TYPE_INT_BGR);
		
		//two 得到画笔
		Graphics g = image.getGraphics();
		
		g.setColor(Color.white);
		g.fillRect(0, 0, 80, 30); //矩形框是白色
		
		g.setColor(Color.black);
		g.setFont(new Font(null,Font.BOLD,20));//字体是黑色
		String  num = makeNum();//得到一个随机数
		System.out.println(num);
		request.getSession().setAttribute("checkcode", num);
		g.drawString(num, 0, 20);//把num画上去
		
	//写入浏览器
		ImageIO.write(image, "jpg", response.getOutputStream());
		
		
	}

		public String makeNum() {
		Random random  = new Random();
		String num = random.nextInt(9999)+"";
		StringBuffer sb = new StringBuffer();
		for(int i = 0;i<4-num.length();i++){
			sb.append("0");
		}
		return sb+num;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
