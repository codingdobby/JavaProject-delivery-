package JaBae.Customer.VO;

public class SearchVO {
	private int order_no;
	private String name;
	private String tel;
	private String GName;
	private String start_date;
	private String end_date;
	private String loc;
	private String gtel;
	private String Object_name;
	private String Sname;

	public String getSname() {
		return Sname;
	}

	public void setSname(String sname) {
		Sname = sname;
	}

	public String getGtel() {
		return gtel;
	}

	public void setGtel(String gtel) {
		this.gtel = gtel;
	}

	public String getObject_name() {
		return Object_name;
	}

	public void setObject_name(String object_name) {
		Object_name = object_name;
	}

	public int getOrder_no() {
		return order_no;
	}

	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getGName() {
		return GName;
	}

	public void setGName(String gName) {
		GName = gName;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

}
