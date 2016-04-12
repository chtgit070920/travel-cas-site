package com.travel.cas.site.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jasig.cas.web.support.WebUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;


/**
 * @description 验证码
 */
public class CaptchaController extends AbstractController{
	
	
	private int w = 70;
	private int h = 26;

	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setDateHeader("Expires", 0L);
		response.setHeader("Cache-Control","no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("image/jpeg");
		
		/*
		 * 得到参数高，宽，都为数字时，则使用设置高宽，否则使用默认值
		 */
		String width = request.getParameter("width");
		String height = request.getParameter("height");
		if (StringUtils.isNumeric(width) && StringUtils.isNumeric(height)) {
			w = NumberUtils.toInt(width);
			h = NumberUtils.toInt(height);
		}
		

		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		
		
		/*
		 * 生成背景
		 */
		createBackground(g);
		
		
		/*
		 * 生成字符 并存入session
		 */
		String capText = createCharacter(g);
		WebUtils.putCapacha(request, capText);
		
		
		g.dispose();
		OutputStream out = response.getOutputStream();
		ImageIO.write(image, "JPEG", out);
		out.close();
		
		return null;
	}
	
	
	private void createBackground(Graphics g) {
		// 填充背景
		g.setColor(getRandColor(220,250)); 
		g.fillRect(0, 0, w, h);
		// 加入干扰线条
		for (int i = 0; i < 8; i++) {
			g.setColor(getRandColor(40,150));
			Random random = new Random();
			int x = random.nextInt(w);
			int y = random.nextInt(h);
			int x1 = random.nextInt(w);
			int y1 = random.nextInt(h);
			g.drawLine(x, y, x1, y1);
		}
	}
	
	private Color getRandColor(int fc,int bc) { 
		int f = fc;
		int b = bc;
		Random random=new Random();
        if(f>255) {
        	f=255; 
        }
        if(b>255) {
        	b=255; 
        }
        return new Color(f+random.nextInt(b-f),f+random.nextInt(b-f),f+random.nextInt(b-f)); 
	}
	
	private String createCharacter(Graphics g) {
		char[] codeSeq = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J',
				'K', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
				'X', 'Y', 'Z','a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j',
				'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
				'x', 'y', 'z','1','2', '3', '4', '5', '6', '7', '8', '9' };
				
		String[] fontTypes = {"Arial","Arial Black","AvantGarde Bk BT","Calibri"}; 
		Random random = new Random();
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			String r = String.valueOf(codeSeq[random.nextInt(codeSeq.length)]);//random.nextInt(10));
			g.setColor(new Color(50 + random.nextInt(100), 50 + random.nextInt(100), 50 + random.nextInt(100)));
			g.setFont(new Font(fontTypes[random.nextInt(fontTypes.length)],Font.BOLD,26)); 
			g.drawString(r, 15 * i + 5, 19 + random.nextInt(8));
//			g.drawString(r, i*w/4, h-5);
			s.append(r);
		}
		return s.toString();
	}

	
}
