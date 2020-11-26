package CarParkingtable;

public class carparkingBean 
{
    String mn,nm,add,ct;
    carparkingBean()
    {   	
    }
	public carparkingBean(String mn, String nm, String add, String ct) {
		super();
		this.mn = mn;
		this.nm = nm;
		this.add = add;
		this.ct = ct;
	}
	public String getMn() {
		return mn;
	}
	public void setMn(String mn) {
		this.mn = mn;
	}
	public String getNm() {
		return nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}
	public String getAdd() {
		return add;
	}
	public void setAdd(String add) {
		this.add = add;
	}
	public String getCt() {
		return ct;
	}
	public void setCt(String ct) {
		this.ct = ct;
	}
    
}
