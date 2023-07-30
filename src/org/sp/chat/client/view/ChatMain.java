package org.sp.chat.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ChatMain extends JFrame{
	JPanel p_center;//각 컨텐츠 페이지들이 들어올 빈 영역
	JPanel p_south;//내비가 들어올 영역
	String[] naviIcon= {"res/friend1.png","res/chatting1.png","res/mypage.png"};
	ArrayList<JLabel> navi;//아이콘 이미지를 담게 될 라벨들
	
	
	public static final int FRIEND=0;//친구 목록
	public static final int CHATTING=1;//채팅 목록
	public static final int MYPAGE=2;//마이 페이지
	
	
	Page[] pages;//컨텐츠 페이지
	

	public ChatMain() {
		p_center = new JPanel();
		p_south = new JPanel();
		pages = new Page[3];
		
		//페이지 생성
		pages[FRIEND] = new FriendPage();
		pages[CHATTING] = new ChattingPage();
		pages[MYPAGE] = new MyPage();
		
		
		//스타일
		p_south.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
		p_center.setBackground(Color.YELLOW);
		
		//조립
		for(int i=0; i<pages.length; i++) {
			p_center.add(pages[i]);
		}
		createNavi();
		add(p_center);
		add(p_south, BorderLayout.SOUTH);
		
		setSize(400,600);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//최초로 친구목록 보여지게
		showHide(FRIEND);
		
		for(int i=0; i<navi.size();i++) {
			JLabel obj=navi.get(i);
			obj.addMouseListener(new MouseAdapter() {
				
				//클릭시 해당 페이지 보여주기
				public void mouseClicked(MouseEvent e) {
					int index=navi.indexOf(e.getSource());//이벤트를 일으킨 JLabel이 몇번째 인지
					System.out.println("클릭");
					showHide(index);
				}
			});
		}

		
	}
	
	public void createNavi() {
		navi=new ArrayList<JLabel>();
		
		for(int i=0; i<naviIcon.length;i++) {
			URL url=ClassLoader.getSystemResource(naviIcon[i]);
			
			try {
				BufferedImage buffImg = ImageIO.read(url);
				Image image=buffImg;
				image=image.getScaledInstance(70, 50, Image.SCALE_SMOOTH);
				JLabel la_icon = new JLabel(new ImageIcon(image));
				la_icon.setPreferredSize(new Dimension(120,50));
				navi.add(la_icon);
				p_south.add(la_icon);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void showHide(int n) {//보이게하고 싶은 index만 넘겨받는다
		for(int i=0; i<pages.length;i++) {
			if(i==n) {//넘겨받은 매개변수와 i가 일치할때만 보이게
				pages[i].setVisible(true);//보이게 처리
				
			}else {
				pages[i].setVisible(false);//안보이게 처리
				
			}
		}
	}

	
	public static void main(String[] args) {
		new ChatMain();
	}
}
