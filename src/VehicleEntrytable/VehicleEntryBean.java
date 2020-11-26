package VehicleEntrytable;

public class VehicleEntryBean 
{
int rid,vt,st,flr;
String cus,vn,ed,mn,et;
float bill;
	VehicleEntryBean()
	{
	}
	public VehicleEntryBean(int rid, int vt, int st, int flr, String cus, String vn, String ed, String mn, String et,float bill) 
	{
		super();
		this.rid = rid;
		this.vt = vt;
		this.st = st;
		this.flr = flr;
		this.cus = cus;
		this.vn = vn;
		this.ed = ed;
		this.mn = mn;
		this.et = et;
		this.bill=bill;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getVt() {
		return vt;
	}
	public void setVt(int vt) {
		this.vt = vt;
	}
	public int getSt() {
		return st;
	}
	public void setSt(int st) {
		this.st = st;
	}
	public int getFlr() {
		return flr;
	}
	public void setFlr(int flr) {
		this.flr = flr;
	}
	public String getCus() {
		return cus;
	}
	public void setCus(String cus) {
		this.cus = cus;
	}
	public String getVn() {
		return vn;
	}
	public void setVn(String vn) {
		this.vn = vn;
	}
	public String getEd() {
		return ed;
	}
	public void setEd(String ed) {
		this.ed = ed;
	}
	public String getMn() {
		return mn;
	}
	public void setMn(String mn) {
		this.mn = mn;
	}
	public String getEt() {
		return et;
	}
	public void setEt(String et) {
		this.et = et;
	}
	public float getBill() {
		return bill;
	}
	public void setBill(float bill) {
		this.bill = bill;
	}
}
