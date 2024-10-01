package com.example.demo.entity;

public class ToDo {
	// コンストラクタ
	public ToDo(String id,String task,String timeLimit){
		id = this.id;
		task = this.task;
		timeLimit = this.timeLimit;
	}
	/** ID */
	public String id;
	/** タスク内容 */
	public String task;
	/** 期限 */
	public String timeLimit;
}
