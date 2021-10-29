package com.tis.in.BanX.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="plot_question")
public class PlotQuestion {
	
	public long getPlotQuestionId() {
		return plotQuestionId;
	}

	public void setPlotQuestionId(long plotQuestionId) {
		this.plotQuestionId = plotQuestionId;
	}

	public String getPlotQuestionTitle() {
		return plotQuestionTitle;
	}

	public void setPlotQuestionTitle(String plotQuestionTitle) {
		this.plotQuestionTitle = plotQuestionTitle;
	}

	public String getPlotQuestionDescription() {
		return plotQuestionDescription;
	}

	public void setPlotQuestionDescription(String plotQuestionDescription) {
		this.plotQuestionDescription = plotQuestionDescription;
	}

	@Id
	@Column(name="plot_question_id")
	
	private long plotQuestionId;
	
	@Column(name="plot_question_title")
	@NotBlank(message="PlotQuestionTitle is mandatory")
	private String plotQuestionTitle;
	
	@Column(name="plot_question_description")
	@NotBlank(message="PlotQuestionDescription is mandatory")
	private String plotQuestionDescription;

}
