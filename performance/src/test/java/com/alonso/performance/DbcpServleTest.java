package com.alonso.performance;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

public class DbcpServleTest {
	private static final String file_path = "/home/alonso/data.csv";
	private static final String[] sex = { "male", "female" };

	public static void preperForData() {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(file_path);
			bw = new BufferedWriter(fw);
			StringBuilder sb = new StringBuilder();
			Random random = new Random();
			long times = 1L;
			long count = 10000000;
			for (long i = 0; i < count; i++) {
				sb.delete(0, sb.length());
				sb.append(String.valueOf(i)); // uid
				sb.append(",");
				sb.append(UUID.randomUUID().toString()); // name
				sb.append(",");
				sb.append(sex[random.nextInt(2)]); // sex
				sb.append(",");
				sb.append(random.nextInt(30)); // age
				sb.append("\n");
				bw.write(sb.toString());
				if (i % 100000 == 0) {
					bw.flush();
					System.out.println("flush times : " + times++);
				}
			}
			bw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		preperForData();
	}
}
