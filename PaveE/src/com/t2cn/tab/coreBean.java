package com.t2cn.tab;

public class coreBean {

	public static final String FIELDID = "FIELDID";
	public static final String LOCATION_ID = "LOCATION_ID";
	public static final String COREID = "COREID";
	public static final String OBJECTID = "OBJECTID";
	public static final String SHAPE = "SHAPE";
	public static final String ORGANIZATION = "ORGANIZATION";
	public static final String SAMPLER = "SAMPLER";
	public static final String CORE_DATE = "CORE_DATE";
	public static final String ROAD_DESCRIPTION = "ROAD_DESCRIPTION";
	public static final String DIRECTION = "DIRECTION";
	public static final String NO_OF_LANE = "NO_OF_LANE";
	public static final String LONGITUTE = "LONGITUTE";
	public static final String LATITUTE = "LATITUTE";
	public static final String RETRIEVED_FROM = "RETRIEVED_FROM";
	public static final String LANE_LOCATION = "LANE_LOCATION";
	public static final String MATERIAL = "MATERIAL";
	public static final String DIAMETER = "DIAMETER";
	public static final String LENGTH = "LENGTH";
	public static final String UNDERLYING_MATERIAL = "UNDERLYING_MATERIAL";
	public static final String PCCTHICKNESS = "PCCTHICKNESS";
	public static final String CRACKING_TYPE = "CRACKING_TYPE";
	public static final String CRACKING_DIRECTION = "CRACKING_DIRECTION";
	public static final String CORE_LEVEL = "CORE_LEVEL";
	public static final String MAXDEPTH = "MAXDEPTH";
	public static final String CORECONDITION = "CORECONDITION";
	public static final String COMMENTS = "COMMENTS";
	
	private String fieldid;
	private String location_id;
	private int coreid;
	private String objectid;
	private String shape;//????????????
	private String orgaization;
	private String sampler;
	private String date;
	private String road_description;
	private String direction;
	private int no_of_lane;
	private double longitute;
	private double latitute;
	private int retrieved_from;
	private int lane_location;
	private int material;
	private int diameter;
	private int length;
	private int underlying_material;
	private int pccthickness;
	private int cracking_type;
	private int cracking_direction;
	private int core_level;
	private int maxdepth;
	private String corecondition;
	private String comments;
	
	
	public String getFieldid() {
		return fieldid;
	}
	public void setFieldid(String fieldid) {
		this.fieldid = fieldid;
	}
	public String getLocation_id() {
		return location_id;
	}
	public void setLocation_id(String location_id) {
		this.location_id = location_id;
	}
	public int getCoreid() {
		return coreid;
	}
	public void setCoreid(int coreid) {
		this.coreid = coreid;
	}
	public String getObjectid() {
		return objectid;
	}
	public void setObjectid(String objectid) {
		this.objectid = objectid;
	}
	public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}
	public String getOrgaization() {
		return orgaization;
	}
	public void setOrgaization(String orgaization) {
		this.orgaization = orgaization;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getRoad_description() {
		return road_description;
	}
	public void setRoad_description(String road_description) {
		this.road_description = road_description;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public double getLongitute() {
		return longitute;
	}
	public void setLongitute(double longitute) {
		this.longitute = longitute;
	}
	public double getLatitute() {
		return latitute;
	}
	public void setLatitute(double latitute) {
		this.latitute = latitute;
	}
	public int getRetrieved_from() {
		return retrieved_from;
	}
	public void setRetrieved_from(int retrieved_from) {
		this.retrieved_from = retrieved_from;
	}
	public int getLane_location() {
		return lane_location;
	}
	public void setLane_location(int lane_location) {
		this.lane_location = lane_location;
	}
	public int getMaterial() {
		return material;
	}
	public void setMaterial(int material) {
		this.material = material;
	}
	public int getDiameter() {
		return diameter;
	}
	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getUnderlying_material() {
		return underlying_material;
	}
	public void setUnderlying_material(int underlying_material) {
		this.underlying_material = underlying_material;
	}
	public int getPccthickness() {
		return pccthickness;
	}
	public void setPccthickness(int pccthickness) {
		this.pccthickness = pccthickness;
	}
	public int getCracking_type() {
		return cracking_type;
	}
	public void setCracking_type(int cracking_type) {
		this.cracking_type = cracking_type;
	}
	public int getCracking_direction() {
		return cracking_direction;
	}
	public void setCracking_direction(int cracking_direction) {
		this.cracking_direction = cracking_direction;
	}
	public int getCore_level() {
		return core_level;
	}
	public void setCore_level(int core_level) {
		this.core_level = core_level;
	}
	public int getMaxdepth() {
		return maxdepth;
	}
	public void setMaxdepth(int maxdepth) {
		this.maxdepth = maxdepth;
	}
	public String getCorecondition() {
		return corecondition;
	}
	public void setCorecondition(String corecondition) {
		this.corecondition = corecondition;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getSampler() {
		return sampler;
	}
	public void setSampler(String sampler) {
		this.sampler = sampler;
	}
	public int getNo_of_lane() {
		return no_of_lane;
	}
	public void setNo_of_lane(int no_of_lane) {
		this.no_of_lane = no_of_lane;
	}
	

	
}
