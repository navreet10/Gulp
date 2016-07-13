package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the RATING database table.
 * 
 */
@Entity
@NamedQuery(name="Rating.findAll", query="SELECT r FROM Rating r")
public class Rating implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RATING_ID_GENERATOR", sequenceName="RATING_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RATING_ID_GENERATOR")
	private long id;

	@Temporal(TemporalType.DATE)
	private Date daterate;

	private BigDecimal rating;

	private String review;

	//bi-directional many-to-one association to Restaurant
	@ManyToOne
	@JoinColumn(name="RESTID")
	private Restaurant restaurant;

	//bi-directional many-to-one association to Subscriber
	@ManyToOne
	@JoinColumn(name="SUBID")
	private Subscriber subscriber;

	public Rating() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDaterate() {
		return this.daterate;
	}

	public void setDaterate(Date daterate) {
		this.daterate = daterate;
	}

	public BigDecimal getRating() {
		return this.rating;
	}

	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}

	public String getReview() {
		return this.review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Restaurant getRestaurant() {
		return this.restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Subscriber getSubscriber() {
		return this.subscriber;
	}

	public void setSubscriber(Subscriber subscriber) {
		this.subscriber = subscriber;
	}

}