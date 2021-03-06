package com.twf.springcloud.ExportPdf.po;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Description: 自定义响应数据结构 
 */
public class AvailableResult {

	// 定义jackson对象
	private static final ObjectMapper MAPPER = new ObjectMapper();

	// 响应业务状态
	private Integer status;

	// 响应消息
	private String msg;

	// 响应中的数据
	private Object data;

	private String ok; // 不使用

	public static AvailableResult build(Integer status, String msg, Object data) {
		return new AvailableResult(status, msg, data);
	}

	public static AvailableResult ok(Object data) {
		return new AvailableResult(data);
	}

	public static AvailableResult ok() {
		return new AvailableResult(null);
	}

	public static AvailableResult errorMsg(String msg) {
		return new AvailableResult(500, msg, null);
	}

	public static AvailableResult errorMap(Object data) {
		return new AvailableResult(501, "error", data);
	}

	public static AvailableResult errorTokenMsg(String msg) {
		return new AvailableResult(502, msg, null);
	}

	public static AvailableResult errorException(String msg) {
		return new AvailableResult(555, msg, null);
	}

	public AvailableResult() {

	}

	// public static LeeJSONResult build(Integer status, String msg) {
	// return new LeeJSONResult(status, msg, null);
	// }

	public AvailableResult(Integer status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public AvailableResult(Object data) {
		this.status = 200;
		this.msg = "OK";
		this.data = data;
	}

	public Boolean isOK() {
		return this.status == 200;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 
	 * @Description: 将json结果集转化为LeeJSONResult对象 需要转换的对象是一个类
	 * @param jsonData
	 * @param clazz
	 * @return
	 * 
	 * 
	 * @date 2016年4月22日 下午8:34:58
	 */
	public static AvailableResult formatToPojo(String jsonData, Class<?> clazz) {
		try {
			if (clazz == null) {
				return MAPPER.readValue(jsonData, AvailableResult.class);
			}
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if (clazz != null) {
				if (data.isObject()) {
					obj = MAPPER.readValue(data.traverse(), clazz);
				} else if (data.isTextual()) {
					obj = MAPPER.readValue(data.asText(), clazz);
				}
			}
			return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @Description: 没有object对象的转化
	 * @param json
	 * @return
	 * 
	 * 
	 * @date 2016年4月22日 下午8:35:21
	 */
	public static AvailableResult format(String json) {
		try {
			return MAPPER.readValue(json, AvailableResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Description: Object是集合转化 需要转换的对象是一个list
	 * @param jsonData
	 * @param clazz
	 * @return
	 * 
	 * 
	 * @date 2016年4月22日 下午8:35:31
	 */
	public static AvailableResult formatToList(String jsonData, Class<?> clazz) {
		try {
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if (data.isArray() && data.size() > 0) {
				obj = MAPPER.readValue(data.traverse(),
						MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
			}
			return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
		} catch (Exception e) {
			return null;
		}
	}

	public String getOk() {
		return ok;
	}

	public void setOk(String ok) {
		this.ok = ok;
	}

}
