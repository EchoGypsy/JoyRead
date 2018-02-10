package com.demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.TreeMap;

public class CFTest {
	public static double[][] CFCalc(int[][] CFArr) {
		double[][] resArr = new double[CFArr.length][CFArr.length];
		double[] aveArr = new double[CFArr.length];
		int numerator;
		double left_denominator, right_denominator, result, average;
		for (int i = 0; i < CFArr.length; i++) {
			for (int j = 0; j < CFArr.length; j++) {
				if (i == j) {
					resArr[i][j] = 1;
				}
			}
		}
		for (int i = 0; i < CFArr.length; i++) {
			average = 0;
			for (int j = 0; j < CFArr[i].length; j++) {
				average += CFArr[i][j];
			}
			average = average / CFArr[i].length;
			aveArr[i] = average;
		}
		for (int i = 0; i < CFArr.length - 1; i++) {
			for (int j = i + 1; j < CFArr.length; j++) {
				numerator = 0;
				left_denominator = 0;
				right_denominator = 0;
				for (int k = 0; k < CFArr[i].length; k++) {
					left_denominator += (CFArr[i][k] - aveArr[i]) * (CFArr[i][k] - aveArr[i]);
					right_denominator += (CFArr[j][k] - aveArr[j]) * (CFArr[j][k] - aveArr[j]);
					if (CFArr[i][k] != 0 && CFArr[j][k] != 0) {
						numerator += (CFArr[i][k] - aveArr[i]) * (CFArr[j][k] - aveArr[j]);
					}
				}
				left_denominator = Math.sqrt(left_denominator);
				right_denominator = Math.sqrt(right_denominator);
				result = numerator / (left_denominator * right_denominator);
				result = (double) Math.round(result * 100) / 100;
				resArr[i][j] = result;
				resArr[j][i] = result;
			}
		}
		return resArr;
	}
	public static void CFCommand(int[][] CFArr, double[][] resArr){
		Stack<Integer> stack1;//数组中有值的数
		Stack<Integer> stack2;//数组中值为0的数
		Map<Integer, Double> map;
		List<String> strList = new ArrayList<String>();
		String str;
		for(int i = 0;i < CFArr[0].length;i++){
			map = new TreeMap<Integer, Double>();
			stack1 = new Stack<Integer>();
			stack2 = new Stack<Integer>();
			for(int j = 0;j < CFArr.length;j++){
				if(CFArr[j][i] == 0){
					stack2.push(j);
				}else{
					stack1.push(j);
				}
			}
			if(stack1.size() != 0){
				for(int x = 0;x < stack1.size();x++){
					for(int y = 0;y < stack2.size();y++){
						if(resArr[stack1.get(x)][stack2.get(y)] > 0.0){
							map.put(stack2.get(y),resArr[stack1.get(x)][stack2.get(y)]);
						}
					}
				}	
			}
			List<Entry<Integer, Double>> list = new ArrayList<Entry<Integer, Double>>(map.entrySet());
			Collections.sort(list, new Comparator<Map.Entry<Integer, Double>>() {  
			    public int compare(Map.Entry<Integer, Double> o1,Map.Entry<Integer, Double> o2) {  
			        return (int)(o2.getValue() - o1.getValue());  
			    }  
			});
			str = "";
			str += "用户" + (i + 1) + " ";
			System.out.print("用户" + (i + 1) + " ");
			if(list.size() != 0){
				if(list.size() >= 5){
					for(int k = 0;k < 5;k++){
						Entry<Integer, Double> ent=list.get(k);
						if(ent.getValue() != 0){
							str += ent.getKey() + " ";
							System.out.print(ent.getKey() + " ");
						}
					}
				}
				else{
					for(int k = 0;k < list.size();k++){
						Entry<Integer, Double> ent=list.get(k);
						if(ent.getValue() != 0){
							str += ent.getKey() + " ";
							System.out.print(ent.getKey() + " ");
						}
					}
				}
			}
			System.out.println();
			str += "\r\n";
			strList.add(str);
		}
		FileWriter writer;
		try {
			writer = new FileWriter("sb.txt");
			for(int i = 0;i < strList.size();i++){
				writer.write(strList.get(i));
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static int[][] getBS(){
		String line;
		List<String> list = new ArrayList<String>();
		int[][] CFArr = null;
		try{
			BufferedReader in =new BufferedReader(new FileReader("bs.txt"));
			while((line=in.readLine())!=null){
				list.add(line);
	        }
			String[] listArr = list.get(0).split(" ");
			CFArr = new int[list.size()][listArr.length];
			for(int j = 0;j < list.size();j++){
				listArr = list.get(j).split(" ");
				for(int k = 0;k < listArr.length;k++){
					CFArr[j][k] = Integer.parseInt(listArr[k]);
				}
			}
		}catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
		return CFArr;
	}
	public static void main(String[] args) {
//		int[][] testArr = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 6, 6, 5, 0, 0, 0, 6, 5, 5 },
//				{ 0, 0, 5, 0, 1, 0, 1, 0, 0, 1 }, { 0, 5, 0, 5, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 5, 5, 5, 5, 0 },
//				{ 0, 1, 5, 1, 0, 1, 0, 0, 0, 0 }, { 0, 1, 0, 1, 0, 0, 0, 0, 1, 0 }, { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
//				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
		int[][] testArr = getBS();
//		for (int i = 0; i < testArr.length; i++) {
//			for (int j = 0; j < testArr[i].length; j++) {
//				System.out.print(testArr[i][j] + " ");
//			}
//			System.out.println();
//		}
		double[][] resArr = CFCalc(testArr);
//		for (int i = 0; i < resArr.length; i++) {
//			for (int j = 0; j < resArr[i].length; j++) {
//				System.out.print(resArr[i][j] + " ");
//			}
//			System.out.println();
//		}
		CFCommand(testArr,resArr);
	}
}
