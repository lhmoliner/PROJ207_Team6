/*
 * Project Workshop CMPP264 Java - Team 6
 * Instructor: Harvey Peters
 * Created by: Megha Patel(000679647)
 * Date: March/23/2015
 * */
import java.util.Date;
public class Package {
	private int pkgId;
	private String PackageName;
	private Date StartDate;
	private Date EndDate;
	private String PackageDesc;
	private double PackagePrice;
	private double AgnComm;
	public int getPkgId() {
		return pkgId;
	}
	public void setPkgId(int pkgId) {
		this.pkgId = pkgId;
	}
	public String getPackageName() {
		return PackageName;
	}
	public void setPackageName(String packageName) {
		PackageName = packageName;
	}
	public Date getStartDate() {
		return StartDate;
	}
	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}
	public Date getEndDate() {
		return EndDate;
	}
	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}
	public String getPackageDesc() {
		return PackageDesc;
	}
	public void setPackageDesc(String packageDesc) {
		PackageDesc = packageDesc;
	}
	public double getPackagePrice() {
		return PackagePrice;
	}
	public void setPackagePrice(double packagePrice) {
		PackagePrice = packagePrice;
	}
	public double getAgnComm() {
		return AgnComm;
	}
	public void setAgnComm(double agnComm) {
		AgnComm = agnComm;
	}
	
}
