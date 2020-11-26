package ParkingLayouttable;

public class ParkingLayoutBean 
{
    int flr,cap,vt,oc;
    
    ParkingLayoutBean()
    {
    }

	public ParkingLayoutBean(int flr, int cap, int vt, int oc) {
		super();
		this.flr = flr;
		this.cap = cap;
		this.vt = vt;
		this.oc = oc;
	}

	public int getFlr() {
		return flr;
	}

	public void setFlr(int flr) {
		this.flr = flr;
	}

	public int getCap() {
		return cap;
	}

	public void setCap(int cap) {
		this.cap = cap;
	}

	public int getVt() {
		return vt;
	}

	public void setVt(int vt) {
		this.vt = vt;
	}

	public int getOc() {
		return oc;
	}

	public void setOc(int oc) {
		this.oc = oc;
	}
    
}
