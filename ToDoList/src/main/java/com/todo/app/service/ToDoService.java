package com.todo.app.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.todo.app.constants.ToDoConstants;
import com.todo.app.entity.ToDo;

@Service
public class ToDoService {
	 /**
	  * やることリストをディレクトリから取得する
	  *
	  * @return やることリスト
	  */
	public static List<ToDo> selectAll() {
		File dir = new File(ToDoConstants.DIRECTORY);
		File[] toDoList = dir.listFiles();
		ArrayList<ToDo> toDoDataList = new ArrayList<>();
		if(toDoList == null) {
			return null;
		}
		try {
			for(File toDo: toDoList) {
				BufferedReader br = new BufferedReader(new FileReader(toDo));
				String line = br.readLine();
				br.close();
				ToDo toDoData = convertToDo(line);
				toDoDataList.add(toDoData);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return toDoDataList;
	}

	/**
	 * やることリストを追加する
	 *
	 * @param toDo やることリスト
	 */
	public static void add(ToDo toDo) {
		LocalDateTime now = LocalDateTime.now();
		String fileName = ToDoConstants.DIRECTORY + "/" + now + ".txt";
		toDo.id = String.valueOf(now);
		File file = new File(fileName);
		try {
			// ファイルを作成
			file.createNewFile();
			PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
			// ファイル書き込み
			printWriter.print(toDo.id);
			printWriter.print(",");
			printWriter.print(toDo.task);
			printWriter.print(",");
			printWriter.print(toDo.timeLimit);
			// ファイルを閉じる
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * やることリストを削除する
	 *
	 * @param toDo やることリスト
	 */
	public static void delete(ToDo toDo) {
		File file = new File(ToDoConstants.DIRECTORY + toDo.id + ".txt");
		file.delete();
	}
	
	/**
	 * 受け取った文字列をやることリスト型に変換する
	 *
	 * @param line 文字列
	 * @return やることリスト
	 */
	public static ToDo convertToDo(String line) {
		String[] toDoData = line.split(",");
		return new ToDo(toDoData[0],toDoData[1],toDoData[2]);
	}

}