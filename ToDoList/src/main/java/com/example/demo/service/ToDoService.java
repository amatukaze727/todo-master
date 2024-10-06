package com.example.demo.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.constants.ToDoConstants;
import com.example.demo.entity.ToDo;

@Service
public class ToDoService {
	 /**
	  * やることリストをディレクトリから取得する
	  *
	  * @return やることリスト
	  * @throws Exception 
	  */
	public List<ToDo> selectAll() throws Exception {
		File dir = new File(ToDoConstants.DIRECTORY);
		File[] toDoList = dir.listFiles();
		ArrayList<ToDo> toDoDataList = new ArrayList<>();
		// ディレクトリにファイルがない場合はnullを返却する
		if(toDoList == null) {
			return null;
		}
		for(File toDo: toDoList) {
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(toDo));
				String line = br.readLine();
				br.close();
				ToDo toDoData = convertToDo(line);
				toDoDataList.add(toDoData);
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception();
			} finally {
			br.close();
			}
		}
		return toDoDataList;
	}

	/**
	 * やることリストを追加する
	 *
	 * @param toDo やることリスト
	 * @throws Exception 
	 */
	public void add(ToDo toDo) throws Exception {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
		String now = format.format(LocalDateTime.now());
		String fileName = ToDoConstants.DIRECTORY + "\\" + now + ".txt";
		toDo.setId(now);
		File file = new File(fileName);
		PrintWriter printWriter = null;
		try {
			// ファイルを作成
			file.createNewFile();
			printWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
			// ファイル書き込み
			printWriter.print(toDo.getId());
			printWriter.print(",");
			printWriter.print(toDo.getTask());
			printWriter.print(",");
			printWriter.print(toDo.getTimeLimit());
			// ファイルを閉じる
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception();
		} finally {
			printWriter.close();
		}
	}

	/**
	 * やることリストを削除する
	 *
	 * @param toDo やることリスト
	 */
	public void delete(ToDo toDo) {
		File file = new File(ToDoConstants.DIRECTORY + "\\" + toDo.getId() + ".txt");
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
		ToDo toDo = new ToDo();
		toDo.setId(toDoData[0]);
		toDo.setTask(toDoData[1]);
		toDo.setTimeLimit(toDoData[2]);
		return toDo;
	}

}