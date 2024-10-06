package com.example.demo.entity;

import lombok.Data;

@Data
public class ToDo {
	/** ID */
	private String id;
	/** タスク内容 */
	private String task;
	/** 期限 */
	private String timeLimit;
}
