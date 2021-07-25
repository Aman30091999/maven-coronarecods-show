package com.corona.coronaapp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoronaappApplication {

	public static String getData(String c) throws IOException {
		StringBuffer br = new StringBuffer();
		br.append("<html>");
		String url = "https://www.worldometers.info/coronavirus/country/" + c + "/";
		br.append(c.toUpperCase()+"<br>");
		Document doc = Jsoup.connect(url).get();
		// System.out.println("Title"+doc.title());
		// System.out.println(doc.body().html());
		// #maincounter-wrap
		Elements elements = doc.select("#maincounter-wrap");
		// System.out.println(elements.html());
		elements.forEach((e) -> {
			// System.out.println(e.html());
			String text = e.select("h1").text();
			String count = e.select(".maincounter-number>span").text();
			// System.out.println(text + " " + count);
			br.append(text).append(count).append("<br>");
		});
		br.append("</html>");
		return br.toString();
	}

	public static void main(String[] args) throws Exception {
		// SpringApplication.run(CoronaappApplication.class, args);
		// jsoup
		/*
		 * System.out.println("hello"); Scanner sc = new Scanner(System.in); String u =
		 * sc.nextLine(); System.out.println(getData(u));
		 */
		JFrame f = new JFrame("Details in corona Country");
		f.setSize(500, 500);
		Font font = new Font("Serif", Font.BOLD| Font.ITALIC, 30);
		JTextField tf = new JTextField();
		tf.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel l = new JLabel("Welcome");
		l.setHorizontalAlignment(SwingConstants.CENTER);
		tf.setFont(font);
		l.setFont(font);
		tf.setBackground(Color.lightGray);
		l.setBackground(Color.cyan);
		JButton b = new JButton("Get");
		b.setBackground(Color.red);
		b.addActionListener(e -> {
			try {
				String n = tf.getText();
				String w = getData(n);
				System.out.println(w);
				l.setText(w);
			} catch (Exception r) {
				r.printStackTrace();
			}
		});
		f.setLayout(new BorderLayout());
		f.add(tf, BorderLayout.NORTH);
		f.add(l,BorderLayout.CENTER);
		f.add(b, BorderLayout.SOUTH);
		f.setVisible(true);
	}

}
