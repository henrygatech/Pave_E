package com.t2cn.tab;

public class coreBean {

	public static final String FIELDID = "FIELDID";
	public static final String LOCATION_ID = "LOCATION_ID";
	public static final String COREID = "COREID";
	public static final String OBJECTID = "OBJECTID";
	public static final String SHAPE = "SHAPE";
	public static final String ROAD = "ROAD";
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
	private String road;
	public String getRoad() {
		return road;
	}
	public void setRoad(String road) {
		this.road = road;
	}
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
	private String retrieved_from;
	private String lane_location;
	private String material;
	private int diameter;
	private int length;
	private String underlying_material;
	private String pccthickness;
	private String cracking_type;
	private String cracking_direction;
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
	public String getRetrieved_from() {
		return retrieved_from;
	}
	public void setRetrieved_from(String retrievefromspinner) {
		this.retrieved_from = retrievefromspinner;
	}
	public String getLane_location() {
		return lane_location;
	}
	public void setLane_location(String lanelocationspinner) {
		this.lane_location = lanelocationspinner;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
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
	public String getUnderlying_material() {
		return underlying_material;
	}
	public void setUnderlying_material(String underlying_material) {
		this.underlying_material = underlying_material;
	}
	public String getPccthickness() {
		return pccthickness;
	}
	public void setPccthickness(String pccthickness) {
		this.pccthickness = pccthickness;
	}
	public String getCracking_type() {
		return cracking_type;
	}
	public void setCracking_type(String cracking_type) {
		this.cracking_type = cracking_type;
	}
	public String getCracking_direction() {
		return cracking_direction;
	}
	public void setCracking_direction(String cracking_direction) {
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
