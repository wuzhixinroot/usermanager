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
		//seven��ֹ������������ͼƬ
		response.setDateHeader("Expires", -1);//ie
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Pragma", "no-cache");
		
		//six ֪ͨ�ͻ�����ͼƬ��ʽ��
		response.setHeader("Content-Type", "img/jpeg");
		
		//one ���ڴ��д���һ��ͼƬ
		BufferedImage image = new BufferedImage(80,30,BufferedImage.TYPE_INT_BGR);
		
		//two �õ�����
		Graphics g = image.getGraphics();
		
		g.setColor(Color.white);
		g.fillRect(0, 0, 80, 30); //���ο��ǰ�ɫ
		
		g.setColor(Color.black);
		g.setFont(new Font(null,Font.BOLD,20));//�����Ǻ�ɫ
		String  num = makeNum();//�õ�һ�������
		System.out.println(num);
		request.getSession().setAttribute("checkcode", num);
		g.drawString(num, 0, 20);//��num����ȥ
		
	//д�������
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
