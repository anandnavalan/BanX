package com.tis.in.BanX.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tis.in.BanX.domain.PlotQuestion;
import com.tis.in.BanX.repository.PlotQuestionRepository;

@Service
public class PlotQuestionService {
	
	@Autowired
	PlotQuestionRepository plotQuestionRepository;
	
	public PlotQuestion addOrUpdatePlotQuestion(PlotQuestion plotQuestion) {
		return plotQuestionRepository.save(plotQuestion);
	}

	public List<PlotQuestion> getAllPlotQuestion() {
		return plotQuestionRepository.findAll();
	}

	public Optional<PlotQuestion> getPlotQuestion(long plotQuestionId) {
		Optional<PlotQuestion> plotQuestion = plotQuestionRepository.findById(plotQuestionId);
		return plotQuestion;
	}

}
