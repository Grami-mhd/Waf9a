package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: DoneJob
 *
 */
@Entity

public class DoneJob implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int doneJobId;
	private int delay;
	private int price;
	private Date date=Calendar.getInstance().getTime();
	private String text;
	@OneToOne
	private Adress adress;

	@ManyToMany(mappedBy="offers",fetch=FetchType.EAGER)
	private List<Expertise> expertises;
	
	@ManyToOne
	private User worker;
	@ManyToOne(cascade=CascadeType.ALL)
	private User boss;
	
	@OneToMany(mappedBy="job",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Claim> ClaimedAbout;
	
	
	private static final long serialVersionUID = 1L;

	public DoneJob() {
		super();
	}   
	public int getDoneJobId() {
		return this.doneJobId;
	}

	public void setDoneJobId(int doneJobId) {
		this.doneJobId = doneJobId;
	}   
	public int getDelay() {
		return this.delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}   
	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}   
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}   
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}
	public List<Expertise> getExpertises() {
		return expertises;
	}
	public void setExpertises(List<Expertise> expertises) {
		this.expertises = expertises;
	}
	public User getWorker() {
		return worker;
	}
	public void setWorker(User worker) {
		this.worker = worker;
	}
	public User getBoss() {
		return boss;
	}
	public void setBoss(User boss) {
		this.boss = boss;
	}
	public Adress getAdress() {
		return adress;
	}
	public void setAdress(Adress adress) {
		this.adress = adress;
	}
	public List<Claim> getClaimedAbout() {
		return ClaimedAbout;
	}
	public void setClaimedAbout(List<Claim> claimedAbout) {
		ClaimedAbout = claimedAbout;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + doneJobId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DoneJob other = (DoneJob) obj;
		if (doneJobId != other.doneJobId)
			return false;
		return true;
	}
   
}
