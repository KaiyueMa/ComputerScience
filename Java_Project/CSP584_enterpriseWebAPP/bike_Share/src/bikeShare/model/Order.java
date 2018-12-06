package bikeShare.model;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class Order {
    private Integer orderid;

    private Integer userid;

    private Date ordertime;

    private Float oldprice;

    private Float newprice;

    private Boolean ispay;

    private Boolean issend;

    private Boolean isreceive;

    private Boolean iscomplete;


    
    private Integer totalcash;
    
    private String  repaytime;
    
    private Integer goodid;
    
    private Goods good;
    
    private int hour;
    
    private Time time;
    
   private User user; //购买者

    
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

    
    public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public Goods getGood() {
		return good;
	}

	public void setGood(Goods good) {
		this.good = good;
	}

	public Integer getGoodid() {
		return goodid;
	}

	public void setGoodid(Integer goodid) {
		this.goodid = goodid;
	}

	public Integer getTotalcash() {
		return totalcash;
	}

	public void setTotalcash(Integer totalcash) {
		this.totalcash = totalcash;
	}

	
	public String getRepaytime() {
		return repaytime;
	}

	public void setRepaytime(String repaytime) {
		this.repaytime = repaytime;
	}


	private List<Goods> goodsInfo;

    

  
   

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public Float getOldprice() {
        return oldprice;
    }

    public void setOldprice(Float oldprice) {
        this.oldprice = oldprice;
    }

    public Float getNewprice() {
        return newprice;
    }

    public void setNewprice(Float newprice) {
        this.newprice = newprice;
    }

    public Boolean getIspay() {
        return ispay;
    }

    public void setIspay(Boolean ispay) {
        this.ispay = ispay;
    }

    public Boolean getIssend() {
        return issend;
    }

    public void setIssend(Boolean issend) {
        this.issend = issend;
    }

    public Boolean getIsreceive() {
        return isreceive;
    }

    public void setIsreceive(Boolean isreceive) {
        this.isreceive = isreceive;
    }

    public Boolean getIscomplete() {
        return iscomplete;
    }

    public void setIscomplete(Boolean iscomplete) {
        this.iscomplete = iscomplete;
    }

 

    public List<Goods> getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(List<Goods> goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

  
}