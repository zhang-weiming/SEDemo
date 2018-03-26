package main.entities;

/**
 * 锤子
 * @author zwm12
 * @version v0.1
 */
public class Hammer {
	/**
	 * 编号
	 */
	private String id;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 归类
	 */
	private String classification;
	
	/**
	 * 重量
	 */
	private double weight;
	
	/**
	 * 无参构造方法
	 * 链接-<a href="https://www.baidu.com"><i>百度</i></a>
	 */
	public Hammer() {
		;
	}
	
	/**
	 * 全参构造方法
	 * @param id
	 * @param name
	 * @param classification
	 * @param weight
	 */
	public Hammer(String id, String name, String classification, double weight) {
		this.id = id;
		this.name = name;
		this.classification = classification;
		this.weight = weight;
	}

	/**
	 * 使用参数字符串数组初始化类
	 * @param items
	 */
	public Hammer(String[] items) {
		this.id = items[0];
		this.name = items[1];
		this.classification = items[2];
		this.weight = Double.parseDouble(items[3].trim());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
//		String res = String.format(
//				"<hammer><id>%s</id><name>%s</name><classification>%s</classification><weight>%f</weight></hammer>",
//				this.id, this.name, this.classification, this.weight);
		String res = String.format(
				"Hammer: id=%s, name=%s, classification=%s, weight=%.2fKg",
				this.id, this.name, this.classification, this.weight);
		return res;
	}
}
